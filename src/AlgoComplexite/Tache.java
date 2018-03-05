package AlgoComplexite;

import java.util.ArrayList;

public class Tache {
    protected String ressource;
    protected int nbOperation;
    protected ArrayList<Tache> dependances = new ArrayList<>();
    protected int num; // n° de 1 a 10 (ordre dans le job)
    protected boolean b_fini=false;
    protected int priorite; // méthode 1


    public Tache(String serv, int val){
        this.ressource = serv;
        this.nbOperation = val;
    }

    //Calcule la durée d'une tache en fonction d'un serveur donné.
    public float dureeTache(Serveur serv) {

        //On vérifie si le serveur est du bon type.
        if ( this.ressource == serv.nom ) {
            return nbOperation/serv.getFlops();
        }
        else {
            //TODO : Raise erreur
            //Pour l'instant on retourne juste un int très grand.
            return 1000000000;
        }

    }

    //region GETTERS/SETTERS
    public String getRessource() {
        return ressource;
    }
    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public int getNbOperation() {
        return nbOperation;
    }
    public void setNbOperation(int nbOperation) {
        this.nbOperation = nbOperation;
    }

    public ArrayList<Tache> getDependances() {
        return dependances;
    }
    public String getNomDependances() {
        String r = "[ ";
        for(int i=0;i<dependances.size();i++){
            r += "T"+dependances.get(i).getNum();
            r += (i==dependances.size()-1)?"":" ; ";
        }
        r+= " ]";


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
