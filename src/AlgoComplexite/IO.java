package AlgoComplexite;


public class IO extends Serveur {

    public IO(){
        this.valeur = random(min, max);
        min = 50;
        max = 100;
    }

    public IO(int val){
        this.valeur = val;
    }
}
