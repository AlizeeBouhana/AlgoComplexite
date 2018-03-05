package AlgoComplexite;


public class GPU extends Serveur {

    //Constantes
    // final int GPU_MIN = ... ;
    // On peut faire ça si on veut définir les minmax en constantes et pas seulement dans le constructeur.

    public GPU(){
        this.valeur = random(min, max);
        min = 10;
        max = 80;
    }

    public GPU(int val){
        this.valeur = val;
    }
}
