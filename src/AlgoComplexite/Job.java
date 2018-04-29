package AlgoComplexite;

import java.util.ArrayList;

public class Job {
    protected ArrayList<Tache> taches = new ArrayList<>();

    public Job(ArrayList<Tache> taches){
        this.taches = taches;
    }

    public Job() { }


    public void addTache(Tache tache) {
        taches.add(tache);
    }

    /**
     * Récupère un String (issue du fichier généré) et en déduit une liste de dépendances.
     * @param str
     * @return
     */
    public ArrayList<Tache> stringToDependance(String str) {

        ArrayList<Tache> dependance = new ArrayList();

        int numTache;

        //Si on obtient "[]", il n'y a pas de dépendances.
        if ( str.isEmpty() )
            return dependance;

        //On sépare tout les charactères restants en un tableau de chiffres.
        String[] strTable = str.split("");


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
     * Complexité O(n*Log(n)) avec n le nombre de tache dans le job.
     * n car on parcours chaque tache du job
     * Log(n) car on parcours chaque dépendance de ses taches, qui sont <<< n.
     */
    public void updateDependances() {
        for (Tache t : taches) {
            t.updateWhenAvailable();
        }
    }


    //region GETTERS/SETTERS

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    //endregion
}
