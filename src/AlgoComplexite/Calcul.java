package AlgoComplexite;

import java.util.Random;

public class Calcul {

    //private double nbOperation/Puissance Calcul?

    private double flops;
    private int puissance;

    public Calcul() {
        this.flops = random(1, 100);
        this.puissance = 3 * random(1, 4);
    }

    //TODO : Constructeur avec bornes pour les puissances.

    //Renvoie un entier entre a et b inclus.
    public int random(int a, int b) {
        Random rand = new Random();
        return a + rand.nextInt(b-a + 1);
    }

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
        return String.valueOf(flops) + unit;
    }

    //Accesseurs
    public double getFlops() {
        return flops;
    }
    public int getPuissance() {
        return puissance;
    }
}
