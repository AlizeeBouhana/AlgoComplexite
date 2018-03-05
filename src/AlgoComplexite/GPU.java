package AlgoComplexite;

import java.util.Random;

public class GPU extends Serveur {

    int min = 10;
    int max = 80;

    public GPU(){
        Random rand = new Random();
        this.valeur = random(min, max);
    }

    public GPU(int val){
        this.valeur = val;
    }
}
