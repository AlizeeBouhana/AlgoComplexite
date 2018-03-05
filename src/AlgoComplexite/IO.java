package AlgoComplexite;

import java.util.Random;

public class IO extends Serveur {

    int min = 50;
    int max = 100;

    public IO(){
        Random rand = new Random();
        this.valeur = random(min, max);
    }

    public IO(int val){
        this.valeur = val;
    }
}
