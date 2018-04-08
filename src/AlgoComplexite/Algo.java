package AlgoComplexite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Algo {


    private static ArrayList<CPU> l_CPU = new ArrayList<>();
    private static ArrayList<GPU> l_GPU = new ArrayList<>();
    private static ArrayList<IO> l_IO = new ArrayList<>();
    private static ArrayList<Job> l_Jobs = new ArrayList<>();
    private static ArrayList<Tache> l_Taches = new ArrayList<>();

    public static void main(String[] args) {

        //TODO : Enrichir les constructeurs des Serveur pour mettre des bornes de valeurs.
        
        /*
        genererFichier("Sauvegarde.txt");
        readFile("Sauvegarde.txt");
        save("Sauvegarde2.txt");
        */


        /*
        System.out.println("Lecture fichier :");
        readFile("Test.txt");
        */

        genererFichier("Test_Glouton.txt");
        readFile("Test_Glouton.txt");
        System.out.println("Solution glouton :");
        methodeGlouton(l_CPU, l_GPU, l_IO, l_Jobs);

        //Test Ecriture
        /*
        System.out.println("Lecture fichier :");
        readFile("Test.txt");
        System.out.println("Ecriture fichier :");
        save("Test2.txt");
        */


    }

    public static void genererFichier(String filename) {


        l_CPU = new ArrayList<>();
        l_GPU = new ArrayList<>();
        l_IO = new ArrayList<>();
        l_Taches = new ArrayList<>();
        l_Jobs = new ArrayList<>();

        int minserv = 5;
        int maxserveur = 15;

        int mintache = 5;
        int maxtache = 25;
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
        save(l_CPU,l_GPU,l_IO,l_Jobs, filename);

        //endregion

    }

    public static boolean save(String filename) {
        return save(l_CPU, l_GPU, l_IO, l_Jobs, filename);
    }

    public static boolean save(ArrayList<CPU> l_cpu, ArrayList<GPU> l_gpu, ArrayList<IO> l_io, ArrayList<Job> l_jobs, String filename){
        String textResult="Servers\r\n";
        String textCPU="CPU = [";
        String textGPU = "GPU = [";
        String textIO = "I/O = [";
        String textJ = "";

        // Text += (condition)? <<do si vrai>> : <<do si false>>;
        for(int i=0;i < l_cpu.size()-1;i++)
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
        try(PrintStream ps = new PrintStream(filename)) {
            ps.println(textResult);
        } catch (FileNotFoundException fnfe) {
            return false;
        }
        return true;
    }

    //TODO : Sauvegarder la solution dans un fichier texte.
    public static boolean saveSolution(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, String fname) {

        return true;
    }



    public static boolean readFile(String fname) {


        Scanner sc;
        Scanner scLine;

        String line;


        l_CPU = new ArrayList<>();
        l_GPU = new ArrayList<>();
        l_IO = new ArrayList<>();
        l_Jobs = new ArrayList<>();

        //On essaye d'ouvrir le fichier à lire
        try {
            sc = new Scanner(new File(fname));
        }
        catch (FileNotFoundException fnfe) {
            return false;
        }

        //Tant que le fichier n'est pas fini
        while ( sc.hasNextLine() ) {

            //On le lit ligne par ligne
            line = sc.nextLine();
            System.out.println(line);

            //On ignore les lignes vides.
            if ( !line.isEmpty() )
            {
                scLine = new Scanner(line);

                /*String debugString = scLine.next();
                System.out.println("debugString : " + debugString); */
                switch (scLine.next()) {
                    case "CPU":
                        System.out.println("OuiCPU");
                        while ( scLine.hasNext() ) {
                            CPU cpu = new CPU(new Calcul(scLine.next()));
                            if ( !cpu.isNull() )
                                l_CPU.add(cpu);
                        }
                        //scLine.close();
                        break;
                    case "GPU":
                        System.out.println("OuiGPU");
                        while ( scLine.hasNext() ) {
                            GPU gpu = new GPU(new Calcul(scLine.next()));
                            if ( !gpu.isNull() )
                                l_GPU.add(gpu);
                        }
                        //scLine.close();
                        break;
                    case "I/O":
                    case "IO":
                        System.out.println("OuiIO");
                        while ( scLine.hasNext() ) {
                            IO io = new IO(new Calcul(scLine.next()));
                            if ( !io.isNull() ) {
                                System.out.println(io.flopsToString());
                                l_IO.add(io);

                            }
                        }
                        //scLine.close();
                        break;
                    case "Job":
                        int numJob = scLine.nextInt();
                        int nbTaches = 0;

                        scLine.next(); //On ignore le "=".

                        //On compte le nombre de taches ( et donc de lignes à lire).
                        while ( scLine.hasNext() ) {
                            scLine.next(); nbTaches++;
                        }

                        Job job = new Job(numJob);

                        //On lit une nouvelle ligne par tache, qu'on ajoutera au Job
                        for (int i = 1; i <= nbTaches; i++) {

                            //On scan une nouvelle ligne
                            line = sc.nextLine();
                            scLine = new Scanner(line);

                            System.out.println(line);

                            //On ignore les deux premiers mots, " Tx " et " = ".
                            scLine.next(); scLine.next();

                                //On lit le type de Serveur
                            String typeServ = scLine.next();
                                //On lit la puissance du calculteur
                            Calcul nbOp = new Calcul(scLine.next());
                                //On lit les dépendances.
                            //On lit tout le reste de la ligne pour obtenir les dépendances.
                            String str_dependance = "";
                            while ( scLine.hasNext() )
                                str_dependance += scLine.next();
                            ArrayList<Tache> dependances = job.stringToDependance(str_dependance);

                            Tache tache;
                            switch (typeServ) {
                                case "GPU,":
                                    tache = new Tache(job, numJob, i, "GPU", nbOp, dependances);
                                    job.addTache(tache);
                                    break;
                                case "CPU,":
                                    tache = new Tache(job, numJob, i, "CPU", nbOp, dependances);
                                    job.addTache(tache);
                                    break;
                                case "I/O,":
                                case "IO,":
                                    tache = new Tache(job, numJob, i, "IO", nbOp, dependances);
                                    job.addTache(tache);
                                    break;
                                default:
                            }

                        }
                        l_Jobs.add(job);

                    default:
                }

                scLine.close();
            }
        }

        sc.close();

        return true;
    }


    public static void methodeGlouton(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, ArrayList<Job> listJob) {


        boolean tachesCPUfini = false;
        boolean tachesGPUfini = false;
        boolean tachesIOfini = false;

        Tache tacheActuelle;

        CPU meilleurCPU;
        GPU meilleurGPU;
        IO meilleurIO;

        ArrayList<Tache> listTachesCPU = new ArrayList<>();
        ArrayList<Tache> listTachesGPU = new ArrayList<>();
        ArrayList<Tache> listTachesIO = new ArrayList<>();
        //On ajoute toutes les taches de touts les jobs à la liste des taches. On a pas besoin de différencier les jobs pour nos calculs.
        listJob.forEach(job -> {
            listTachesCPU.addAll(Tache.tachesParRessource(job.getTaches(), "CPU"));
            listTachesGPU.addAll(Tache.tachesParRessource(job.getTaches(), "GPU"));
            listTachesIO.addAll(Tache.tachesParRessource(job.getTaches(), "IO"));
        });

        //Tant que tout les jobs ne sont pas fini, on continue d'assigner des taches aux serveurs
        while (!tachesCPUfini || !tachesGPUfini || !tachesIOfini) {

            /* Assignation des tâches CPU */
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesCPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesCPU);

                //Pas de tache, soit elle dépendent toutes d'un autre type de tache, soit on a fini.
                if (tacheActuelle == null) {

                    //Si il n'y a plus de tache à faire, on a fini cette liste.
                    if (Tache.taskAreAllAssigned(listTachesCPU))
                        tachesCPUfini = true;
                }
                //Choix du meilleur serveur : On choisit le serveur le plus rapide.
                else {
                    meilleurCPU = (CPU) tacheActuelle.serveurQuiFiniraLePlusVite(listCpu);
                    if (meilleurCPU == null)
                        System.out.println("CPU NULL !");
                    meilleurCPU.add(tacheActuelle);
                }
            }


            /* Assignation des tâches GPU */
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesGPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesGPU);

                //Pas de tache, soit elle dépendent toutes d'un autre type de tache, soit on a fini.
                if (tacheActuelle == null) {

                    //Si il n'y a plus de tache à faire, on a fini cette liste.
                    if (Tache.taskAreAllAssigned(listTachesGPU))
                        tachesGPUfini = true;
                }
                //Choix du meilleur serveur : On choisit le serveur le plus rapide.
                else {
                    meilleurGPU = (GPU) tacheActuelle.serveurQuiFiniraLePlusVite(listGpu);
                    meilleurGPU.add(tacheActuelle);
                }
            }

            /* Assignation des tâches IO */
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesIOfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesIO);

                //Pas de tache, soit elle dépendent toutes d'un autre type de tache, soit on a fini.
                if (tacheActuelle == null) {

                    //Si il n'y a plus de tache à faire, on a fini cette liste.
                    if (Tache.taskAreAllAssigned(listTachesIO))
                        tachesIOfini = true;
                }
                //Choix du meilleur serveur : On choisit le serveur le plus rapide.
                else {
                    meilleurIO = (IO) tacheActuelle.serveurQuiFiniraLePlusVite(listIo);
                    meilleurIO.add(tacheActuelle);
                }

            }
        }

        System.out.println("Ordre des taches des CPU :");
        for (CPU cpu : listCpu) {
            cpu.afficherOrdreDesTaches();
        }
        System.out.println("Ordre des taches des GPU :");
        for (GPU gpu : listGpu) {
            gpu.afficherOrdreDesTaches();
        }
        System.out.println("Ordre des taches des IO :");
        for (IO io : listIo) {
            io.afficherOrdreDesTaches();
        }

    }

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
                if(!choix_cpu.getDependances().get(i).isAssigned()){ // si une de dépendance n'as pas finie d'etre executée
                    b_validchoixcpu=false;
                }
            }
        }

        return compteur;
    }
}
