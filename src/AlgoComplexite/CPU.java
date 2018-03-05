package AlgoComplexite;

import java.util.Random;

public class CPU extends Serveur {

    int min = 50;
    int max = 100;

    public CPU(){
        Random rand = new Random();
        this.valeur = random(min, max);
    }

    public CPU(int val){
        this.valeur = val;
    }
}
