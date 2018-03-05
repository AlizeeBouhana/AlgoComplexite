package AlgoComplexite;


import java.util.ArrayList;

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


    //Renvoie une écriture plus lisible pour les flops : 50T, 60G, ect..
    public String flopsToString() {
        return vitesseCalcul.flopsToString();
    }

    public void add(Tache t) {
        ordreDesTaches.add(t);
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
