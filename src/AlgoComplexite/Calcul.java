package AlgoComplexite;

import java.util.Random;

public class Calcul {

    //private double nbOperation/Puissance Calcul?

    private int flops;
    private int puissance;

    /*  Puissance de l'unité de calcul = flops * 10^puissance

        1K  = 10^3 flops;
        1M = 10^6 flops;
        1G = 10^9 flops;
        1T = 10^12 flops;
     */


    //Génère une unité de calcul à la puissance aléatoire
    public Calcul() {
        this.flops = random(1, 100);
        this.puissance = 3 * random(1, 4);
    }

    public Calcul(int min, int max) {
        this.flops = random(1, 100);
        this.puissance = 3 * random(min, max);
    }

    public Calcul(String strFlop) {

        this.flops = 0;
        this.puissance = 0;

        int c_value;

        for (char c : strFlop.toCharArray()) {

            switch ( c ) {
                    case 'K':
                        puissance = 3;
                        break;
                    case 'M':
                        puissance = 6;
                        break;
                    case 'G':
                        puissance = 9;
                        break;
                    case 'T':
                        puissance = 12;
                        break;
                    default:
                        c_value = Character.getNumericValue(c);
                        if (  c_value != -1 ) {
                            flops = flops*10 + c_value;
                        }
                }
            }

    }



    //TODO : Constructeur avec bornes pour les puissances.

    //Renvoie un entier entre a et b inclus.
    //Set en static pour être utilisable par d'autres classes en tant que fonction.
    public static int random(int a, int b) {
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

    public boolean isNull() {
        return ( flops == 0 || puissance == 0 );
    }

    //Accesseurs
    public int getFlops() {
        return flops;
    }
    public int getPuissance() {
        return puissance;
    }
}
