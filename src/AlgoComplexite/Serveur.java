package AlgoComplexite;

import java.util.Random;

public class Serveur {

    protected int flops;
    /*
        1K  = 10^3 flops;
        1M = 10^6 flops;
        1G = 10^9 flops;
        1T = 10^12 flops;
     */
    protected String nom;

    //Bornes des valeurs que peuvent prendre les serveurs pour leur puissance.
    protected int min;
    protected int max;

    //Renvoie un entier entre a et b inclus.
    public int random(int a, int b) {
        Random rand = new Random();
        return a + rand.nextInt(b-a + 1);
    }

    //Renvoie une écriture plus lisible pour les flops : 50T, 60G, ect..
    public String flopsToString() {
        return "Oui";
    }

    //region GETTERS/SETTERS
    public int getFlops() {
        return flops;
    }
    public void setFlops(int flops) {
        this.flops = flops;
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
