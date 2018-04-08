package AlgoComplexite;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Serveur {


    protected String nom;
    private Calcul vitesseCalcul;

    // ordreDesTaches contiennent la solution du problème : Chaque Serveur a la liste ordonné des taches qu'il doit effectuer dès qu'il peut.
    private ArrayList<Tache> ordreDesTaches = new ArrayList<>(); //A renommer ?

    //la prochaine fois sur la timeline d'exécution où le serveur sera disponible.
    private float nextTimeAvailable = 0f;

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

    /**
     * Ajoute la tache t à la liste des taches à faire du Serveur. Modifie sa prochaine disponibilité.
     * @param t
     */
    public void add(Tache t) {

        //Si le type de ressource ne correspond pas au serveur, on n'ajoute pas la tache. Incompatibilité !
        //On n'ajoute aussi pas la tache si elle est déjà dans la liste.
        if ( !t.getRessource().equals(nom) || ordreDesTaches.contains(t))
            return;

        //On met à jour les attributs du serveur
        nextTimeAvailable += t.dureeTache(this);

        //On met à jour les attributs de la tache
        t.setAssigned(true);
        t.setWhenDone(nextTimeAvailable);
        t.getJob().updateDependances();

        ordreDesTaches.add(t);
    }
    
    public void afficherOrdreDesTaches() {

        System.out.print(nom + " " + vitesseCalcul.flopsToString() + " : [ ");
        for (Tache tache : ordreDesTaches) {
            System.out.print("[J"+tache.getNumJob() + ",T"+ tache.getNum() +"] ");
        }
        System.out.println("]");
    }


    //Méthodes statiques

    /**
     * Renvoie le serveur de la liste avec la valeur nextTimeAvailable la plus faible.
     * @param listServ
     * @return
     */
    public static Serveur minNextTimeAvailable(ArrayList<Serveur> listServ) {

        Serveur servTimeMin = null;
        for ( Serveur serv : listServ ) {
            if ( servTimeMin == null || serv.getNextTimeAvailable() < servTimeMin.getNextTimeAvailable() )
                servTimeMin = serv;
        }

        return servTimeMin;
    }


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

    public float getNextTimeAvailable() {
        return nextTimeAvailable;
    }

    public void setNextTimeAvailable(float nextTimeAvailable) {
        this.nextTimeAvailable = nextTimeAvailable;
    }

    public String getNom() {
        return nom;
    }

    //endregion
}
