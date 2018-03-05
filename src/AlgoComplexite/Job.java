package AlgoComplexite;

import java.util.ArrayList;

public class Job {
    protected ArrayList<Tache> taches = new ArrayList<>();

    public Job(ArrayList<Tache> taches){
        this.taches=taches;
    }


    //region GETTERS/SETTERS
    public ArrayList<Tache> getTaches() {
        return taches;
    }
    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
    //endregion
}
