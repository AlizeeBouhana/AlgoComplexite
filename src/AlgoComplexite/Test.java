package AlgoComplexite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Serveur cpu;
        Tache t;

        /*
        for (int i = 0; i <= 10; i++) {

            cpu = new CPU();
            t = new Tache("CPU");
            System.out.println("Puissance CPU : " + cpu.flopsToString());
            System.out.println("Duree de la Tache : " + t.flopsToString());
            System.out.println("    DureeTache = "+ t.dureeTache(cpu));
            System.out.println("    DureeTache2 = "+ t.dureeTache2(cpu));
            System.out.println("  --------  ");

        } */

        //readFile("Test.txt");
        testInt("[T3 ,T2]");
        testInt("[T3,T2]");
        testInt("[]");

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

}
