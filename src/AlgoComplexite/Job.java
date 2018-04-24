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

        //TODO : Très moche car fait en Offline sans doc.

        ArrayList<Tache> dependance = new ArrayList();

        int numTache;

        //Si on obtient "[]", il n'y a pas de dépendances.
        if ( str.isEmpty() )
            return dependance;

        //On sépare tout les charactères restants en un tableau de chiffres.
        String[] strTable = str.split("");

        /*String[] strTest = str.split(",");
        for ( int i = 0; i < strTest.length; i++) {
            System.out.print("'"+ strTest[i] + "'");
        }
        System.out.println(); */

        for (int i = 0; i < strTable.length; i++) {

            //Si le prochain caractère est un T, le numéro de tache d'une dépendance va suivre.
            if ( strTable[i].equals("T") ) {
                numTache = 0;
                i++;
                /*Integer.parseInt renvoie l'int correspondant au string donnée en argument, si le string ne
                représente pas un int, il renvoie une erreur.
                On fait boucler dans un 'try' cette fonction pour construire l'int en entier ( pour les nombres 10 et + )
                et lorsque son erreur est attrapé, on traite le numéro en allant chercher la tache correspondante
                dans le catch
                */

                try {
                    while ( numTache >= 0 ) {
                        numTache = numTache*10 + Integer.parseInt(strTable[i]);
                        i++;
                    }
                }
                catch (NumberFormatException err) {
                    Tache tache = getTache(numTache);
                    if (tache != null)
                        dependance.add(tache);
                    else
                        System.out.println("Erreur tache non-valide !");}
            }
        }
        /*
        ArrayList<Tache> dependance = new ArrayList();

        //System.out.println("stD : " + str);

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
            else {
                System.out.println("Erreur tache non-valide !");
                System.out.println("stD : " + str);
                System.out.println("tableDigit : " );
                for ( String st : tableDigit )
                    System.out.print(st + " ");
                System.out.println();
                System.out.println("parseInt : " + Integer.parseInt(tableDigit[i]) );
            }
        }*/

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
