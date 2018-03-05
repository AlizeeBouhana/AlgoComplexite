package AlgoComplexite;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Algo {

    public static void main(String[] args) {

        /* TODO LISTE ?

        TODO : Méthode pour load un fichier
        TODO : Méthodes de calcul de meilleur chemins
            TODO : Format de réponse : Chaque Serveur a une liste de tache et on leur assigne ces taches.

        TODO : Enrichir les constructeurs des Serveur pour mettre des bornes de valeurs.
        TODO : Constructeur pour les serveurs, tache et job pour en définir des précise pour des tests.


         */

        //region - variables
        ArrayList<CPU> l_CPU = new ArrayList<>();
        ArrayList<GPU> l_GPU = new ArrayList<>();
        ArrayList<IO> l_IO = new ArrayList<>();
        ArrayList<Tache> l_Taches= new ArrayList<>();
        ArrayList<Job> l_Jobs= new ArrayList<>();

        int minserv = 5;
        int maxserveur = 100;

        int mintache = 30;
        int maxtache = 200;
        //endregion

        //region - Creation des serveurs
        int nbServeur = (int)(minserv+(Math.random()*((maxserveur-3)+1-minserv))); // entre 17+3 et 97+3 serveurs

        // minimum 1 serveur de chaque type
        l_CPU.add(new CPU()); // val CPU [50G;1000000G]
        l_GPU.add(new GPU()); // val CPU [100G;1000000G]
        l_IO.add(new IO()); // val CPU [500G;1000000G]

        // max 97 autres serveurs
        for(int i=0;i<nbServeur;i++){
            int serv = (int)(1+(Math.random()*(3+1-1)));
            if(serv==1){ // CPU
                CPU cpu = new CPU();
                l_CPU.add(cpu);
            }
            else if(serv==2){ // GPU
                GPU gpu = new GPU();
                l_GPU.add(gpu);
            }
            else{ // IO
                IO io = new IO();
                l_IO.add(io);
            }
        }
        //endregion

        //region - Creation des jobs/taches

        //region - Tache
        int nbTaches = (int)(mintache+(Math.random()*(maxtache+1-mintache)));

        for(int i=0;i<nbTaches;i++){ // création des taches sans dépendances
            int s= (int)(1+(Math.random()*(3+1-1)));
            //int nbop=(int)(5+(Math.random()*(10000+1-5)));
            String serv="";
            if(s==1){
                serv="CPU";
            }
            else if(s==2){
                serv="GPU";
            }
            else {
                serv="IO";
            }
            l_Taches.add(new Tache(serv));
        }
        //endregion

        //region - Job
        int cpt = 0;
        while(cpt != l_Taches.size()){
            int nbtachejob=(cpt>=l_Taches.size()-10) ? l_Taches.size()-cpt : (int)(1+(Math.random()*(10+1-1)));
            ArrayList<Tache> l_tachesjob = new ArrayList<>();
            int numtache=1;

            for(int i=cpt;i<cpt+nbtachejob;i++){
                Tache t= l_Taches.get(i);
                t.setNum(numtache);
                l_tachesjob.add(t);
                numtache+=1;
            }

            l_Jobs.add(new Job(l_tachesjob));
            cpt+=nbtachejob;
        }
        //endregion

        //region - Dépendances
        for(int i=0;i<l_Jobs.size();i++){ // boucle pour chaque job
            Job jb = l_Jobs.get(i);
            if(jb.getTaches().size()>1){ // si le job contient plus d'une tache on peut creer des dépendances

                for(int k=1;k<jb.getTaches().size();k++){ // on commence par la 2e tache de la liste : k=1

                    int b_dep = (int)(0+(Math.random()*(1+1-0))); // 0 ou 1

                    if(b_dep==0){ // création d'une dépendance
                        Tache t = jb.getTaches().get(k); // debut 2e tache du job
                        int nbdep = (int)(1+(Math.random()*((t.getNum()-1)+1-1))); // min 1 dep, max n°dep-1

                        ArrayList<Tache> listtache = new ArrayList<>();

                        for(int l=1;l<nbdep;l++){
                            listtache.add(jb.getTaches().get(t.getNum()-l-1)); // N°tache-1, -2, -3, ..., -nbdep
                        }

                        t.setDependances(listtache);

                    }

                }
            }
        }
        //endregion


        //endregion

        //region - Création fichier txt
        save(l_CPU,l_GPU,l_IO,l_Jobs);

        //endregion

        //region - Méthode1 RANDOM : Tout aléatoire


        //endregion

    }

    public static boolean save(ArrayList<CPU> l_cpu, ArrayList<GPU> l_gpu, ArrayList<IO> l_io, ArrayList<Job> l_jobs){
        String textResult="Serveurs :\r\n";
        String textCPU="CPU = [";
        String textGPU = "GPU = [";
        String textIO = "I/O = [";
        String textJ = "";

        // Text += (condition)? <<do si vrai>> : <<do si false>>;
        for(int i=0;i<l_cpu.size()-1;i++)
            textCPU+=l_cpu.get(i).flopsToString()+", ";
        textCPU += l_cpu.get(l_cpu.size()-1).flopsToString()+"]\r\n";

        for(int i=0;i<l_gpu.size()-1;i++)
            textGPU+=l_gpu.get(i).flopsToString()+", ";
        textGPU += l_gpu.get(l_gpu.size()-1).flopsToString()+"]\r\n";

        for(int i=0;i<l_io.size()-1;i++)
            textIO+=l_io.get(i).flopsToString()+", ";
        textIO += l_io.get(l_io.size()-1).flopsToString()+"]\r\n";

        for(int i=0;i<l_jobs.size();i++){
            textJ+="\r\nJob "+(i+1)+" = [";
            for(int k=1;k<=l_jobs.get(i).getTaches().size();k++){
                textJ += "T"+l_jobs.get(i).getTaches().get(k-1).getNum();
                textJ += (k==l_jobs.get(i).getTaches().size())?"]\r\n":", ";
            }
            for(int k=1;k<=l_jobs.get(i).getTaches().size();k++){
                textJ+="T"+l_jobs.get(i).getTaches().get(k-1).getNum() +" = ";
                textJ+=l_jobs.get(i).getTaches().get(k-1).getRessource()+", "+l_jobs.get(i).getTaches().get(k-1).flopsToString()+", ";
                textJ+=l_jobs.get(i).getTaches().get(k-1).getNomDependances()+" \r\n";
            }
        }


        textResult+=textCPU+textGPU+textIO+textJ;
        try(PrintStream ps = new PrintStream("Sauvegarde.txt")) {
            ps.println(textResult);
        } catch (FileNotFoundException fnfe) {
            return false;
        }
        return true;
    }

    //TODO : Méthodes load(f file) qui peut lire un fichier et remplir les variables en conséquences !

    public static int methode1(ArrayList<CPU> l_cpu, ArrayList<GPU> l_gpu, ArrayList<IO> l_io, ArrayList<Job> l_jobs){

        int compteur = 0; // temps d'execution de toutes les taches
        // pour savoir si la tache choisie au hasard peut etre executée
        boolean b_validchoixcpu = false;
        boolean b_validchoixgpu = false;
        boolean b_validchoixio = false;

        //region - Création des listes de Taches pour les 3 différents serveurs
        ArrayList<Tache> lt_cpu = new ArrayList<>();
        ArrayList<Tache> lt_gpu = new ArrayList<>();
        ArrayList<Tache> lt_io = new ArrayList<>();

        for(int i =0; i<l_jobs.size();i++){
            for(int j = 0;j<l_jobs.get(i).getTaches().size();j++){
                if(l_jobs.get(i).getTaches().get(j).getRessource().equals("CPU")){
                    lt_cpu.add(l_jobs.get(i).getTaches().get(j));
                }
                else if(l_jobs.get(i).getTaches().get(j).getRessource().equals("GPU")){
                    lt_gpu.add(l_jobs.get(i).getTaches().get(j));
                }
                else{
                    lt_io.add(l_jobs.get(i).getTaches().get(j));
                }
            }
        }
        //endregion

        int icpu = (int)((Math.random()*((lt_cpu.size()-1)+1))); // choix de la tache au hasard dans la liste CPU
        Tache choix_cpu = lt_cpu.get(icpu);
        int igpu = (int)((Math.random()*((lt_gpu.size()-1)+1))); // choix de la tache au hasard dans la liste GPU
        Tache choix_gpu = lt_gpu.get(igpu);
        int iio = (int)((Math.random()*((lt_io.size()-1)+1))); // choix de la tache au hasard dans la liste IO
        Tache choix_io = lt_io.get(iio);

        while(!b_validchoixcpu){

        }
        if(choix_cpu.getDependances().size()==0){ // pas de dépendances
            b_validchoixcpu=true;
        }
        else{
            b_validchoixcpu = true;
            for(int i = 0;i<choix_cpu.getDependances().size();i++){
                if(!choix_cpu.getDependances().get(i).getB_fini()){ // si une de dépendance n'as pas finie d'etre executée
                    b_validchoixcpu=false;
                }
            }
        }

        return compteur;
    }
}
