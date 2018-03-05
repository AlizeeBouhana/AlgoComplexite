package AlgoComplexite;


public class IO extends Serveur {

    public IO(){
        this.nom = "IO";
        this.valeur = random(1, 100);
        //Renvoie une puissance (pour 10) parmi 3, 6, 9, 12.
        this.puissance = 3 * random(1, 4);

        this.flops = valeur*(long)Math.pow(10,puissance);
    }

    public IO(int val){
        this.flops = val;
    }
}
