package AlgoComplexite;


public class CPU extends Serveur {


    public CPU() {
        this.valeur = random(min, max);
        min = 50;
        max = 100;
    }


    public CPU(int val){
        this.valeur = val;
    }
}
