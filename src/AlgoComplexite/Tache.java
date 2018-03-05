package AlgoComplexite;

import java.util.ArrayList;
import java.util.Random;

public class Tache {
    protected String ressource;

    protected long nbOperation;
    protected int valeur;
    protected int puissance;

    protected ArrayList<Tache> dependances = new ArrayList<>();
    protected int num; // n° de 1 a 10 (ordre dans le job)
    protected boolean b_fini=false;
    protected int priorite; // méthode 1


    public Tache(String serv){
        this.ressource = serv;
        //Renvoie une puissance (pour 10) parmi 3, 6, 9, 12.
        this.valeur = random(1, 100);
        this.puissance = 3 * random(1, 4);
        this.nbOperation = valeur*(long)Math.pow(10,puissance);
    }

    //Calcule la durée d'une tache en fonction d'un serveur donné.
    public float dureeTache(Serveur serv) {

        //On vérifie si le serveur est du bon type.
        if ( this.ressource == serv.nom ) {
            return nbOperation/(float)serv.getFlops();
        }
        else {
            //TODO : Raise erreur
            //Pour l'instant on retourne juste un int très grand.
            return 1000000000;
        }

    }

    //Renvoie un entier entre a et b inclus.
    public int random(int a, int b) {
        Random rand = new Random();
        return a + rand.nextInt(b-a + 1);
    }

    public String flopsToString() {
        String unit = "";
        if ( puissance == 12 )
            unit = "T";
        else if ( puissance == 9 )
            unit = "G";
        else if ( puissance == 6 )
            unit = "M";
        else if ( puissance == 3 )
            unit = "K";
        return String.valueOf(valeur) + unit;
    }


    //region GETTERS/SETTERS
    public String getRessource() {
        return ressource;
    }
    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public long getNbOperation() {
        return nbOperation;
    }
    public void setNbOperation(int nbOperation) {
        this.nbOperation = nbOperation;
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
