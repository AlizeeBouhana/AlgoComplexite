package AlgoComplexite;

import java.util.ArrayList;

public class Tache {

    private String ressource;
    private Calcul nbOp;

    private ArrayList<Tache> dependances = new ArrayList<>();

    private boolean isAssigned = false;
    private double whenAvailable = 0d;
    private double whenDone = Double.MAX_VALUE;

    private int num; // n° de 1 a 10 (ordre dans le job)
    private int numJob;
    private Job job;


    //Tache avec un nombre d'opérations aléatoire et le numéro du job associé.
    public Tache(String serv, int numJob){
        this.ressource = serv;
        this.numJob = numJob;
        this.nbOp = new Calcul(3, 14);
    }

    /*  Puissance de l'unité de calcul = flops * 10^puissance
1K  = 10^3 flops;
1M = 10^6 flops;
1G = 10^9 flops;
1T = 10^12 flops;
*/


    //Tache avec un nombre d'opérations aléatoire
    public Tache(String serv){
        this.ressource = serv;

            /*
            Quantité de calcul des taches en fonction du type de serveur :
            Les taches CPU entre 10M et 10T
            Les taches GPU entre 10M et 1000T
            Les taches IO entre 1K et 10G
            */
        switch (ressource) {
            case "CPU":
                this.nbOp = new Calcul(7, 13);
                break;
            case "GPU":
                this.nbOp = new Calcul(7, 14);
                break;
            case "I/O":
            case "IO":
                this.nbOp = new Calcul(3, 10);
                break;
            default:
                System.out.println("Constructeur tache mauvaise entrée ! --> + " + serv);
                this.nbOp = new Calcul(3, 14);
                break;
        }
        //this.nbOp = new Calcul(3, 14);
    }

    /* Crée une tache avec tout ses attributs donné en argument (pour lecture fichier) */
    public Tache(Job job, int numJob, int num, String ressource, Calcul nbOp, ArrayList<Tache> dependances){
        this.job = job;
        this.numJob = numJob;
        this.num = num;
        this.ressource = ressource;
        this.nbOp = nbOp;
        this.dependances = dependances;

        if ( !this.dependances.isEmpty() )
            whenAvailable = Double.MAX_VALUE;
    }


    /**
     * Calcule la durée d'une tache en fonction d'un serveur donné.
     * @param serv
     * @return
     */
    public double dureeTache(Serveur serv) {

        //On vérifie si le serveur est du bon type.
        if ( this.ressource.equals(serv.nom) ) {

            /*
                Pour éviter de consommer trop de mémoire et de puissance de calcul en manipulant des valeurs échelles 10^12,
                on soustrait d'abord leur puissances pour diviser ça à une échelle plus raisonnable.

                On cast les nos Flops en double pour avoir une valeur décimale et non un entier arrondi.
             */
            return ( (double)getFlops()/(double)serv.getFlops() ) * Math.pow(10, getPuissance() - serv.getPuissance() );
        }
        else {
            //On retourne juste un double très grand.
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calcule à quel moment le serveur finira la tache si il commence à essayer de la remplir dès que possible.
     */
    public double tempsFinTache(Serveur serv) {

        //Si la tache a des dépendances non résolues, on renvoie la valeur max.
        //if ( !isAvailable() ) return Double.MAX_VALUE;

        //Temps de fin de la tache = TempsDebutTraitementTache + DuréeTache

        //Le tempsDeDebut est le plus grand entre le temps de disponibilité du serveur et le temps de
        //disponibilité de la tache.
        double tempsDebut = Math.max(serv.getNextTimeAvailable(), whenAvailable);

        return tempsDebut + dureeTache(serv);
    }

    /**
     * Renvoie le serveur de la liste donnée qui finira la tache le tôt possible en prenant en compte sa propre disponibilité.
     * Complexite 0(n), n le nb de serveur dans la liste.
     * @param l_serv
     * @return
     */
    public Serveur serveurQuiFiniraLePlusTot(ArrayList<? extends Serveur> l_serv) {

        Serveur plusRapide = null;

        for ( Serveur serv : l_serv ) {
            //Si le serveur est du meme type que la tache. Pas nécessaire de vérifier (normalement.)
            if ( serv.nom.equals(ressource) ) {

                if ( plusRapide == null || tempsFinTache(serv) < tempsFinTache(plusRapide))
                    plusRapide = serv;

            }
        }
        return plusRapide;
    }


    /**
     * Met à jour la disponibilité d'une tache (savoir si on peut commencer à l'exécuter).
     * Sert à mettre à jour les disponibilités lorsqu'il y a dépendances.
     */
    public void updateWhenAvailable() {

        //Si une date de disponibilité lui a déjà été assigné, on skip.
        // (Double.MAX_VALUE est la valeur par défaut si la tache a des dépendances).
        if ( whenAvailable != Double.MAX_VALUE )
            return;

        double maxTimeAvailable = 0d;
        //On check si les taches parents sont assignés, si oui, on regarde leur temps de résolution maximale.
        for (Tache tache : dependances) {

            //Si une des taches dont elle dépend n'a meme pas été assigné, on abandonne, cette tache n'est pas encore démarrable car aucune des dépendances n'est résolue.
            if ( !tache.isAssigned() )
                return;

            //Sinon on sauvegarde le max timeavailable, pour savoir quand sera résolu la dernière tache résolvable des dépendances.
            if ( maxTimeAvailable < tache.getWhenDone() )
                maxTimeAvailable = tache.getWhenDone();
        }
        whenAvailable = maxTimeAvailable;
    }

    //Méthodes statiques (travail sur les listes)

    /**
     * Filtre la liste des taches en fonction d'un type de serveur, et renvoie la liste obtenue.
     * @param listTaches
     * @param ressource
     * @return
     */
    public static ArrayList<Tache> tachesParRessource(ArrayList<Tache> listTaches, String ressource) {

        ArrayList<Tache> listTacheFiltree = new ArrayList<>();

        for ( Tache t : listTaches ) {
            if ( t.getRessource().equals(ressource))
                listTacheFiltree.add(t);
        }

        return listTacheFiltree;
    }

    /**
     * Renvoie la tâche qui pourra être exécutée le plus tôt possible dans la liste, renvoie null si aucune disponible.
     * Complexité O(n), n le nombre de tache dans la liste.
     * @param listTaches
     * @return
     */
    public static Tache premiereDisponible(ArrayList<Tache> listTaches) {

        for ( Tache t : listTaches ) {
            //Si la tache n'est pas assigné et que ses dépendances sont OK.
            if ( !t.isAssigned() && t.getWhenAvailable() != Double.MAX_VALUE ) {
                return t;
            }
        }

        return null;
    }

    public String flopsToString() {
        return nbOp.flopsToString();
    }

    public String toString() {
        return "[J" + numJob + ",T" + num + "]";
    }

    //region GETTERS/SETTERS

    public boolean isAssigned() {
        return isAssigned;
    }
    public void setAssigned(boolean assigned) {
        this.isAssigned = assigned;
    }

    public String getRessource() {
        return ressource;
    }

    public int getPuissance() {
        return nbOp.getPuissance();
    }
    public int getFlops() {
        return nbOp.getFlops();
    }

    public String getNomDependances() {
        String r = "[";
        for(int i=0;i<dependances.size();i++){
            r += "T"+dependances.get(i).getNum();
            r += (i==dependances.size()-1)?"":", ";
        }
        r+= "]";


        return r;
    }
    public void setDependances(ArrayList<Tache> dependances) {
        this.dependances = dependances;
    }

    public double getWhenAvailable() {
        return whenAvailable;
    }

    public double getWhenDone() {
        return whenDone;
    }

    public void setWhenDone(double whenDone) {
        this.whenDone = whenDone;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getNumJob() {
        return numJob;
    }

    //endregion
}
