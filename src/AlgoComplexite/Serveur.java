package AlgoComplexite;

import java.util.Random;

public class Serveur {

    protected int valeur;
    //Bornes des valeurs que peuvent prendre les serveurs pour leur puissance.
    protected int min;
    protected int max;

    //Renvoie un entier entre a et b inclus.
    public int random(int a, int b) {
        Random rand = new Random();
        return a + rand.nextInt(b-a + 1);
    }

    //region GETTERS/SETTERS
    public int getValeur() {
        return valeur;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    //endregion
}
