package AlgoComplexite;

import java.util.ArrayList;
import java.util.Random;

public class Tache {

    private String ressource;
    private Calcul nbOp;

    //Numéro du Job dans laquelle la tache se trouve.
    private int numJob;

    private ArrayList<Tache> dependances = new ArrayList<>();
    private int num; // n° de 1 a 10 (ordre dans le job)
    private boolean b_fini=false;
    private int priorite; // méthode 1


    //Tache avec un nombre d'opérations aléatoire et le numéro du job associé.
    public Tache(String serv, int numJob){
        this.ressource = serv;
        this.numJob = numJob;
        this.nbOp = new Calcul();
    }

    //Tache avec un nombre d'opérations aléatoire
    public Tache(String serv){
        this.ressource = serv;
        this.nbOp = new Calcul();
    }

    public Tache(int numJob, int num, String ressource, Calcul nbOp, ArrayList<Tache> dependances){
        this.numJob = numJob;
        this.num = num;
        this.ressource = ressource;
        this.nbOp = nbOp;
        this.dependances = dependances;
    }


    //Calcule la durée d'une tache en fonction d'un serveur donné.
    public double dureeTache(Serveur serv) {

        //On vérifie si le serveur est du bon type.
        if ( this.ressource.equals(serv.nom) ) {

            /*
                Pour éviter de consommer trop de mémoire et de puissance de calcul en manipulant des valeurs échelles 10^12,
                on soustrait d'abord leur puissances pour diviser ça à une échelle plus raisonnable.

                On cast les nos Flops en double pour avoir une valeur décimale et non un entier arrondi.
             */
            //System.out.println("Valeur = " + (double)getFlops()/(double)serv.getFlops() + "   Puissance = " + (getPuissance() - serv.getPuissance()) );
            return ( (double)getFlops()/(double)serv.getFlops() ) * Math.pow(10, getPuissance() - serv.getPuissance() );
        }
        else {
            //TODO : Raise erreur
            //Pour l'instant on retourne juste un double très grand.
            return Double.MAX_VALUE;
        }
    }

    //OUTDATED
    public double dureeTache2(Serveur serv) {

        //On vérifie si le serveur est du bon type.
        if ( this.ressource.equals(serv.nom) ) {

            return ( getFlops()*Math.pow(10,getPuissance()) ) / (serv.getFlops()*Math.pow(10, serv.getPuissance() ) );
        }
        else {
            //TODO : Raise erreur
            //Pour l'instant on retourne juste un double très grand.
            return 1000000000;
        }
    }

    public String flopsToString() {
        return nbOp.flopsToString();
    }


    //region GETTERS/SETTERS
    public String getRessource() {
        return ressource;
    }
    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public int getPuissance() {
        return nbOp.getPuissance();
    }
    public int getFlops() {
        return nbOp.getFlops();
    }

    public ArrayList<Tache> getDependances() {
        return dependances;
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


    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getPriorite() {
        return priorite;
    }
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public boolean getB_fini() {
        return b_fini;
    }
    public void setB_fini(boolean b_fini) {
        this.b_fini = b_fini;
    }
    //endregion
}
