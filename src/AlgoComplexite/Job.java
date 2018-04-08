package AlgoComplexite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Job {

    private int numJob;
    private int nbTaches;

    protected ArrayList<Tache> taches = new ArrayList<>();

    public Job(ArrayList<Tache> taches){
        this.taches=taches;
        this.nbTaches = taches.size();
    }

    public Job(int numJob) {
        this.numJob = numJob;
        this.nbTaches = 0;
    }


    public void addTache(Tache tache) {

        if ( taches.add(tache) )
            nbTaches++;

    }

    /**
     * Récupère un String (issue du fichier généré) et en déduit une liste de dépendances.
     * @param str
     * @return
     */
    public ArrayList<Tache> stringToDependance(String str) {

        ArrayList<Tache> dependance = new ArrayList();

        System.out.println("stD : " + str);

        //On retire tout les characters non-digit de la chaine de charactères
        str = str.replaceAll("\\D+","");

        //Si on obtient "[]", il n'y a pas de dépendances.
        if ( str.isEmpty() )
            return dependance;

        //On sépare tout les charactères restants en un tableau de chiffres.
        String[] tableDigit = str.split("");

        for (int i = 0; i < tableDigit.length; i++) {
            Tache tache = getTache( Integer.parseInt(tableDigit[i]));
            if ( tache != null )
                dependance.add(tache);
            else
                System.out.println("Erreur tache non-valide !");
        }

        return dependance;

    }

    public Tache getTache(int num) {

        for ( Tache tache : taches) {
            if ( tache.getNum() == num )
                return tache;
        }

        return null;
    }

    /**
     * Pour chaque taches dans le job, update sa liste de dépendances.
     */
    public void updateDependances() {
        for (Tache t : taches) {
            t.updateWhenAvailable();
        }
    }

    //Fonction statiques (travail sur les listes)

    /**
     * Trouve et renvoie le job dans la liste donnée 'listJob' avec le numéro de job 'num' donné.
     */
    //TODO : Pour rendre cet algo plus rapide on pourrait donner en attribut à Job son numéro d'index dans la liste de job, avis ? a faire?
    public static Job getJob(ArrayList<Job> listJob, int num) {
        for (Job job : listJob) {
            if ( job.getNumJob() == num )
                return job;
        }
        //Cas job non trouvé.
        return null;
    }


    //region GETTERS/SETTERS

    public ArrayList<Tache> getTaches() {
        return taches;
    }
    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }

    public int getNumJob() {
        return numJob;
    }

    public void setNumJob(int numJob) {
        this.numJob = numJob;
    }

    //endregion
}
