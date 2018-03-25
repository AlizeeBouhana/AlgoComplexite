package AlgoComplexite;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Serveur {


    protected String nom;
    private Calcul vitesseCalcul;

    // ordreDesTaches contiennent la solution du problème : Chaque Serveur a la liste ordonné des taches qu'il doit effectuer dès qu'il peut.
    private ArrayList<Tache> ordreDesTaches; //A renommer ?

    //Génère un serveur avec une puissance de calcul aléatoire.
    public Serveur() {
        this.vitesseCalcul = new Calcul();
    }

    //Génère un serveur avec une puissance de calcul aléatoire bornée
    //TODO : Seul l'échelle 10^ est bornée, modifier ou faire + de constructeurs pour créer des bornes absolues ?
    public Serveur(int min, int max) {
        this.vitesseCalcul = new Calcul(min, max);
    }

    public Serveur(Calcul vitesseCalcul) {
        this.vitesseCalcul = vitesseCalcul;
    }


    //Renvoie une écriture plus lisible pour les flops : 50T, 60G, ect..
    public String flopsToString() {
        return vitesseCalcul.flopsToString();
    }

    public void add(Tache t) {
        ordreDesTaches.add(t);
    }

    /*
    public static <T extends Serveur> ArrayList<T> readListeServeur(String str_servs) {

        Object serveur;

        ArrayList<T> listeServs = new ArrayList<>();
        String serveurType;
        Scanner sc = new Scanner(str_servs);

        listeServs.add(new CPU());

        //On définit le type de serveur
        if (sc.hasNext()) {
            serveurType = sc.next();
            switch (serveurType) {
                case "CPU":
                case "GPU":
                case "I/O":
                    break;
                default:
                    System.out.println("Erreur : '"+serveurType+"'");
                    return null;
            }
        }

        //On lit la liste

        ArrayList<CPU> list = new ArrayList();

        return listeServs;
    }
    */

    public boolean isNull() {
        return vitesseCalcul.isNull();
    }
    //region GETTERS/SETTERS
    public int getPuissance() {
        return vitesseCalcul.getPuissance();
    }
    public int getFlops() {
        return vitesseCalcul.getFlops();
    }

    public String getNom() {
        return nom;
    }

    //endregion
}
