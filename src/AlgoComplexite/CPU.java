package AlgoComplexite;


public class CPU extends Serveur {


    public CPU() {
        this.nom = "CPU";
        this.flops = random(min, max);
        min = 50;
        max = 100;

    }

    public CPU(int val){
        this.flops = val;
    }


}
