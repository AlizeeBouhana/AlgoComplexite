package AlgoComplexite;
// comm test
import java.util.Random;

public abstract class Serveur {

    protected int valeur;
    //Bornes des valeurs que peuvent prendre les serveurs pour leur puissance.
    protected int min;
    protected int max;

    //region CONSTRUCTEURS
    public Serveur() {
        Random rand = new Random();
        this.valeur = random(min, max);
    }
    //endregion

    //region METHODES
    //Renvoie un entier entre a et b inclus.
    public int random(int a, int b) {
        Random rand = new Random();
        return a + rand.nextInt(b-a + 1);
    }
    //endregion

    //region GETTERS/SETTERS
    public int getValeur() {
        return valeur;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    //endregion
}
