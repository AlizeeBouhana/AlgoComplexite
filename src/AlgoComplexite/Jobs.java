package AlgoComplexite;

import java.util.ArrayList;

public class Jobs {
    protected ArrayList<Taches> taches;

    public Jobs(ArrayList<Taches> taches){
        this.taches=taches;
    }


    //region GETTERS/SETTERS
    public ArrayList<Taches> getTaches() {
        return taches;
    }
    public void setTaches(ArrayList<Taches> taches) {
        this.taches = taches;
    }
    //endregion
}
