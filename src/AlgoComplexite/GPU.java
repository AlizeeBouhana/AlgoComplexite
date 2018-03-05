package AlgoComplexite;


public class GPU extends Serveur {

    //Constantes
    // final int GPU_MIN = ... ;
    // On peut faire ça si on veut définir les minmax en constantes et pas seulement dans le constructeur.

    public GPU(){
        this.nom = "GPU";
        this.valeur = random(1, 100);
        //Renvoie une puissance (pour 10) parmi 3, 6, 9, 12.
        this.puissance = 3 * random(1, 4);

        this.flops = valeur*(long)Math.pow(10,puissance);
    }

    public GPU(int val){
        this.flops = val;
    }
}
