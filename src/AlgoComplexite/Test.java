package AlgoComplexite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        /*  Puissance de l'unité de calcul = flops * 10^puissance
        1K  = 10^3 flops;
        1M = 10^6 flops;
        1G = 10^9 flops;
        1T = 10^12 flops;
     */

        Serveur serv;


        /*
        for ( int i = 0; i < 10; i++) {
            serv = new Serveur(10, 14);
            System.out.println("calcul = " + serv.flopsToString());
        } */



        int[] puiss = new int[15];
        for ( int i = 0; i < puiss.length; i++) {
            puiss[i] = 0;
        }
        for ( int i = 0; i < 3000; i++) {
            serv = new Serveur();
            long puissance = (long)serv.getFlops()* (long)Math.pow(10, serv.getPuissance());
            int ordre = (int)Math.log10(puissance);
            //System.out.println("ordre=" + ordre);
            //System.out.println("Puissance = " + puissance);
            //System.out.println("ordre grandeur = " + ordre);
            puiss[ordre]++;
        }

        for (int i = 3; i < puiss.length; i++) {
            System.out.println("puissance de 10^" + i + " : " + puiss[i]);
        }


        /*
        for ( int i = 0; i < 5; i++) {
            serv = new Serveur(5, 7);
            System.out.println("-------------");
        } */
    }

    public static boolean readFile(String fname) {

        Scanner sc;
        Scanner scLine;

        String line;
        boolean isJob;


        //On essaye d'ouvrir le fichier à lire
        try {
            sc = new Scanner(new File(fname));
        } catch (FileNotFoundException fnfe) {
            return false;
        }

        //Tant que le fichier n'est pas fini
        while (sc.hasNext()) {

            //On le lit ligne par ligne
            System.out.println(sc.next());

        }

        return true;
    }

    public static boolean testInt(String line) {


        System.out.println("line = " + line);
        line = line.replaceAll("\\D+","");
        System.out.println("cropped line = " + line);
        String[] tableDigit = line.split("");
        //System.out.println("Array : " + Arrays.toString(tableDigit));

        System.out.print("Array :");
        for (int i = 0; i < tableDigit.length; i++) {
            //System.out.println("" + Integer.parseInt(tableDigit[i]));
            System.out.print(" " + tableDigit[i]);
        }
        System.out.println();

        return true;
    }

    public static int roundDown(double number, double place) {
        double result = number / place;
        result = Math.floor(result);
        result *= place;
        return (int)result;
    }

}
