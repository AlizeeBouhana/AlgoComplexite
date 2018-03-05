package AlgoComplexite;


public class IO extends Serveur {

    public IO(){
        this.nom = "IO";
        this.flops = random(min, max);
        min = 50;
        max = 100;
    }

    public IO(int val){
        this.flops = val;
    }
}
