package AlgoComplexite;

import java.util.Random;

public class Serveur {

    protected long flops;

    protected long valeur;
    protected int puissance;
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
        String unit = "";
        if ( puissance == 12 )
            unit = "T";
        else if ( puissance == 9 )
            unit = "G";
        else if ( puissance == 6 )
            unit = "M";
        else if ( puissance == 3 )
            unit = "K";
        return String.valueOf(valeur) + unit;
    }

    //region GETTERS/SETTERS
    public double getFlops() {
        return flops;
    }
    public void setFlops(int flops) { this.flops = flops; }

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
