
package AlgoComplexite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

@SuppressWarnings("Duplicates")

public class Algo {

    private static ArrayList<CPU> l_CPU = new ArrayList<>();
    private static ArrayList<GPU> l_GPU = new ArrayList<>();
    private static ArrayList<IO> l_IO = new ArrayList<>();
    private static ArrayList<Job> l_Jobs = new ArrayList<>();
    private static ArrayList<Tache> l_Taches = new ArrayList<>();

    private static String openedFname;

    private static long[] executionTotal = new long[3];
    private static double[] tempsResolutionTotal = new double[3];

    public static void main(String[] args) {

        //-- Variables modifiables pour tester différente configurations et benchmarks.

        //petite config
        int nbConfig_petit = 0;
        int nbServ_petit = 10;
        int nbTache_petit = 25;
        //moy config
        int nbConfig_moy = 0;
        int nbServ_moy = 50;
        int nbTache_moy = 4000;
        //config max ( 100 Serv, 10000 taches )
        int nbConfig_max = 30;

        //set sur True si on veut sauvegarder tout les fichiers + leur fichiers réponses, false sinon.
        //Une fois sur true pensez à baisser le nombre de config généré pour s'y retrouver.
        boolean save = false;

        //------

        String filename;
        String petite_config_bench;
        String moy_config_bench;
        String max_config_bench;

        int num = 0;
        //Génère 5 fichiers différent de tailles petites et les résouts tous.
        for ( int i = 0; i < nbConfig_petit; i++) {


            //Nom fichier
            if (save)
                //Version on génère tout les fichiers
                filename = "config_petite_"+ (num+1) +".txt";
            else
                //Version benchmark only, on conserve pas les configs.
                filename = "config_petite.txt";

            System.out.println("Calcul fichier " + filename + " #"+(num+1)+" !");

            //Generation
            genererFichier(filename, nbServ_petit, nbTache_petit);

            //Résolutions
            readFile(filename);
            methodeAleatoire(save);
            readFile(filename);
            methodeGlouton(save);
            readFile(filename);
            methodeNaive(save);
            num++;
        }

        //Temps execution moyens ecriture
        petite_config_bench = "\r\nPour " + num + " fichiers avec " + nbServ_petit + " serveurs et " + nbTache_petit + " taches :\r\n";
        petite_config_bench += "Temps d'executions moyens : \r\n";
        petite_config_bench += "Methode Aleatoire : " + (float)executionTotal[0] / (float)num + "ms\r\n";
        petite_config_bench += "Methode Glouton : " + (float)executionTotal[1] / (float)num + "ms\r\n";
        petite_config_bench += "Methode Naive : " + (float)executionTotal[2] / (float)num + "ms\r\n";
        petite_config_bench += "Temps résolutions des taches moyens : \r\n";
        petite_config_bench += "Methode Aleatoire :  : " + String.format("%.3f", tempsResolutionTotal[0] / (float)num ) + "s\r\n";
        petite_config_bench += "Methode Glouton :  : " + String.format("%.3f", tempsResolutionTotal[1] / (float)num ) + "s\r\n";
        petite_config_bench += "Methode Naive :  : " + String.format("%.3f", tempsResolutionTotal[2] / (float)num ) + "s\r\n";

        executionTotal = new long[3];
        tempsResolutionTotal = new double[3];
        num = 0;
        //Génère 10 fichiers différent de tailles moyennes et les résouts tous.
        for ( int i = 0; i < nbConfig_moy; i++) {

            if (save)
                //Version on génère tout les fichiers
                filename = "config_moy_"+ (num+1) +".txt";
            else
                //Version benchmark only, on conserve pas les configs.
                filename = "config_moy.txt";

            System.out.println("Calcul fichier " + filename + " #"+(num+1)+" !");

            genererFichier(filename, nbServ_moy, nbTache_moy);
            readFile(filename);
            methodeAleatoire(save);
            readFile(filename);
            methodeGlouton(save);
            readFile(filename);
            methodeNaive(save);
            num++;
        }
        //Temps execution moyens ecriture
        moy_config_bench = "\r\nPour " + num + " fichiers avec " + nbServ_moy + " serveurs et " + nbTache_moy + " taches :\r\n";
        moy_config_bench += "Temps d'executions moyens : \r\n";
        moy_config_bench += "Methode Aleatoire : " + (float)executionTotal[0] / (float)num + "ms\r\n";
        moy_config_bench += "Methode Glouton : " + (float)executionTotal[1] / (float)num + "ms\r\n";
        moy_config_bench += "Methode Naive : " + (float)executionTotal[2] / (float)num + "ms\r\n";
        moy_config_bench += "Temps résolutions des taches moyens : \r\n";
        moy_config_bench += "Methode Aleatoire :  : " + String.format("%.3f", tempsResolutionTotal[0] / (float)num ) + "s\r\n";
        moy_config_bench += "Methode Glouton :  : " + String.format("%.3f", tempsResolutionTotal[1] / (float)num ) + "s\r\n";
        moy_config_bench += "Methode Naive :  : " + String.format("%.3f", tempsResolutionTotal[2] / (float)num ) + "s\r\n";



        executionTotal = new long[3];
        tempsResolutionTotal = new double[3];
        num = 0;
        //Génère 10 fichiers différent de tailles maximum et les résouts tous.
        for ( int i = 0; i < nbConfig_max; i++) {

            if (save)
                //Version on génère tout les fichiers
                filename = "config_max_"+num+".txt";
            else
                //Version benchmark only, on conserve pas les configs.
                filename = "config_max.txt";
            System.out.println("Calcul fichier " + filename + " #"+(num+1)+" !");

            genererFichier(filename, 100, 10000);
            readFile(filename);
            methodeAleatoire(save);
            readFile(filename);
            methodeGlouton(save);
            readFile(filename);
            methodeNaive(save);
            num++;
        }
        //Temps execution moyens ecriture
        max_config_bench = "\r\nPour " + num + " fichiers avec 100 serveurs et 10 000 taches :\r\n";
        max_config_bench += "Temps d'executions moyens : \r\n";
        max_config_bench += "Methode Aleatoire : " + (float)executionTotal[0] / (float)num + "ms\r\n";
        max_config_bench += "Methode Glouton : " + (float)executionTotal[1] / (float)num + "ms\r\n";
        max_config_bench += "Methode Naive : " + (float)executionTotal[2] / (float)num + "ms\r\n";
        max_config_bench += "Temps résolutions des taches moyens : \r\n";
        max_config_bench += "Methode Aleatoire :  : " + String.format("%.3f", tempsResolutionTotal[0] / (float)num ) + "s\r\n";
        max_config_bench += "Methode Glouton :  : " + String.format("%.3f", tempsResolutionTotal[1] / (float)num ) + "s\r\n";
        max_config_bench += "Methode Naive :  : " + String.format("%.3f", tempsResolutionTotal[2] / (float)num ) + "s\r\n";


        String textBenchmark = petite_config_bench + moy_config_bench + max_config_bench;
        try (PrintStream ps = new PrintStream("benchmarkMoyennes.txt")) {
            ps.println(textBenchmark);
        } catch (FileNotFoundException fnfe) {
        }
        
    }

    //Génère un fichier config avec le nombre exacte de serveur et tache données.
    public static void genererFichier(String filename, int nbserv, int nbtache) {

        genererFichier(filename, nbserv, nbserv, nbtache, nbtache);
    }

    //Génère un fichier config avec un nombre de serveurs et taches comprises dans les bornes données.
    public static void genererFichier(String filename, int minserv, int maxserv, int mintache, int maxtache) {


        l_CPU = new ArrayList<>();
        l_GPU = new ArrayList<>();
        l_IO = new ArrayList<>();
        l_Taches = new ArrayList<>();
        l_Jobs = new ArrayList<>();

        //VAR A CHANGER POUR MODIFIER LE NOMBRE DE TACHE MAX D'UN JOB
        int nbTacheMaxParJob = 40;

        //region - Creation des serveurs
        int nbServeur = (int) (minserv + (Math.random() * ((maxserv - 3) + 1 - minserv))); // entre 17+3 et 97+3 serveurs

        // minimum 1 serveur de chaque type
        l_CPU.add(new CPU()); // val CPU [50G;1000000G]
        l_GPU.add(new GPU()); // val CPU [100G;1000000G]
        l_IO.add(new IO()); // val CPU [500G;1000000G]

        // max 97 autres serveurs
        for (int i = 0; i < nbServeur; i++) {
            int serv = (int) (1 + (Math.random() * (3 + 1 - 1)));
            if (serv == 1) { // CPU
                CPU cpu = new CPU();
                l_CPU.add(cpu);
            } else if (serv == 2) { // GPU
                GPU gpu = new GPU();
                l_GPU.add(gpu);
            } else { // IO
                IO io = new IO();
                l_IO.add(io);
            }
        }
        //endregion

        //region - Creation des jobs/taches

        //region - Tache
        int nbTaches = (int) (mintache + (Math.random() * (maxtache + 1 - mintache)));

        for (int i = 0; i < nbTaches; i++) { // création des taches sans dépendances
            int s = (int) (1 + (Math.random() * (3 + 1 - 1)));
            //int nbop=(int)(5+(Math.random()*(10000+1-5)));
            String serv = "";
            if (s == 1) {
                serv = "CPU";
            } else if (s == 2) {
                serv = "GPU";
            } else {
                serv = "IO";
            }
            l_Taches.add(new Tache(serv));
        }
        //endregion

        //region - Job
        int cpt = 0;
        while (cpt != l_Taches.size()) {
            int nbtachejob = (cpt >= l_Taches.size() - nbTacheMaxParJob) ? l_Taches.size() - cpt : (int) (1 + (Math.random() * (nbTacheMaxParJob)));
            ArrayList<Tache> l_tachesjob = new ArrayList<>();
            int numtache = 1;

            for (int i = cpt; i < cpt + nbtachejob; i++) {
                Tache t = l_Taches.get(i);
                t.setNum(numtache);
                //AJOUT JOB
                /*t.setJob();*/
                l_tachesjob.add(t);
                numtache += 1;
            }

            l_Jobs.add(new Job(l_tachesjob));
            cpt += nbtachejob;
        }
        //endregion

        //region - Dépendances
        for (int i = 0; i < l_Jobs.size(); i++) { // boucle pour chaque job
            Job jb = l_Jobs.get(i);
            if (jb.getTaches().size() > 1) { // si le job contient plus d'une tache on peut creer des dépendances

                for (int k = 1; k < jb.getTaches().size(); k++) { // on commence par la 2e tache de la liste : k=1

                    int b_dep = (int) (0 + (Math.random() * (1 + 1 - 0))); // 0 ou 1

                    if (b_dep == 0) { // création d'une dépendance
                        Tache t = jb.getTaches().get(k); // debut 2e tache du job
                        int nbdep = (int) (1 + (Math.random() * ((t.getNum() - 1) + 1 - 1))); // min 1 dep, max n°dep-1

                        ArrayList<Tache> listtache = new ArrayList<>();

                        for (int l = 1; l < nbdep; l++) {
                            listtache.add(jb.getTaches().get(t.getNum() - l - 1)); // N°tache-1, -2, -3, ..., -nbdep
                        }

                        t.setDependances(listtache);

                    }

                }
            }
        }
        //endregion


        //endregion

        //region - Création fichier txt
        save(l_CPU, l_GPU, l_IO, l_Jobs, filename);

        //endregion

    }

    //Sauvegarde la config actuellement "chargé" dans le programme dans un fichier.
    public static boolean save(String filename) {
        return save(l_CPU, l_GPU, l_IO, l_Jobs, filename);
    }

    //Sauvegarde la config donné en argument dans un fichier.
    public static boolean save(ArrayList<CPU> l_cpu, ArrayList<GPU> l_gpu, ArrayList<IO> l_io, ArrayList<Job> l_jobs, String filename) {
        String textResult = "Servers\r\n";
        String textCPU = "CPU = [";
        String textGPU = "GPU = [";
        String textIO = "IO = [";
        String textJ = "";

        // Text += (condition)? <<do si vrai>> : <<do si false>>;
        for (int i = 0; i < l_cpu.size() - 1; i++)
            textCPU += l_cpu.get(i).flopsToString() + ", ";
        textCPU += l_cpu.get(l_cpu.size() - 1).flopsToString() + "]\r\n";

        for (int i = 0; i < l_gpu.size() - 1; i++)
            textGPU += l_gpu.get(i).flopsToString() + ", ";
        textGPU += l_gpu.get(l_gpu.size() - 1).flopsToString() + "]\r\n";

        for (int i = 0; i < l_io.size() - 1; i++)
            textIO += l_io.get(i).flopsToString() + ", ";
        textIO += l_io.get(l_io.size() - 1).flopsToString() + "]\r\n";

        for (int i = 0; i < l_jobs.size(); i++) {
            textJ += "\r\nJob " + (i + 1) + " = [";
            for (int k = 1; k <= l_jobs.get(i).getTaches().size(); k++) {
                textJ += "T" + l_jobs.get(i).getTaches().get(k - 1).getNum();
                textJ += (k == l_jobs.get(i).getTaches().size()) ? "]\r\n" : ", ";
            }
            for (int k = 1; k <= l_jobs.get(i).getTaches().size(); k++) {
                textJ += "T" + l_jobs.get(i).getTaches().get(k - 1).getNum() + " = ";
                textJ += l_jobs.get(i).getTaches().get(k - 1).getRessource() + ", " + l_jobs.get(i).getTaches().get(k - 1).flopsToString() + ", ";
                textJ += l_jobs.get(i).getTaches().get(k - 1).getNomDependances() + " \r\n";
            }
        }


        textResult += textCPU + textGPU + textIO + textJ;
        try (PrintStream ps = new PrintStream(filename)) {
            ps.println(textResult);
        } catch (FileNotFoundException fnfe) {
            return false;
        }
        return true;
    }

    /**
     * Sauvegarde la solution dans un fichier texte
     */
    public static boolean saveSolution(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, String fname, long tempsExecution) {

        double tempsCalcul = Serveur.tempsTotalCalculDesTaches(listCpu, listGpu, listIo);
        String textDuree = "Temps de résolution de toutes les tâches : " + String.format("%.3f", tempsCalcul) + "s \r\n";
        String textExecutionTime = "Durée d'exécution de la méthode : " + (tempsExecution/ 1000000) + "ms \r\n \r\n";

        CPU cpu;
        String textCPUs ="Assignation des tâches aux CPUs :\r\n";
        for (int i = 0; i < listCpu.size(); i++) {
            cpu = listCpu.get(i);
            textCPUs += "CPU #" + i + ", "+cpu.flopsToString() + " : ";
            for ( Tache tache : cpu.getOrdreDesTaches() ) {
                textCPUs += tache.toString() + " ";
            }
            textCPUs += "\r\n";
        }
        textCPUs += "\r\n";

        GPU gpu;
        String textGPUs ="Assignation des tâches aux GPUs :\r\n";
        for (int i = 0; i < listGpu.size(); i++) {
            gpu = listGpu.get(i);
            textGPUs += "GPU #" + i + ", "+gpu.flopsToString() + " : ";
            for ( Tache tache : gpu.getOrdreDesTaches() ) {
                textGPUs += tache.toString() + " ";
            }
            textGPUs += "\r\n";
        }
        textGPUs += "\r\n";

        IO io;
        String textIOs ="Assignation des tâches aux IOs :\r\n";
        for (int i = 0; i < listIo.size(); i++) {
            io = listIo.get(i);
            textIOs += "IO #" + i + ", "+io.flopsToString() + " : ";
            for ( Tache tache : io.getOrdreDesTaches() ) {
                textIOs += tache.toString() + " ";
            }
            textIOs += "\r\n";
        }
        textIOs += "\r\n";

        String textFinal = textDuree + textExecutionTime + textCPUs + textGPUs + textIOs;

        try (PrintStream ps = new PrintStream(fname)) {
            ps.println(textFinal);
        } catch (FileNotFoundException fnfe) {
            return false;
        }
        return true;

    }


    //Lit le fichier donné en argument et charge sa config.
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
        } catch (FileNotFoundException fnfe) {
            return false;
        }

        //Tant que le fichier n'est pas fini
        while (sc.hasNextLine()) {

            //On le lit ligne par ligne
            line = sc.nextLine();
            //System.out.println(line);

            //On ignore les lignes vides.
            if (!line.isEmpty()) {
                scLine = new Scanner(line);

                /*String debugString = scLine.next();
                System.out.println("debugString : " + debugString); */
                switch (scLine.next()) {
                    case "CPU":
                        //System.out.println("OuiCPU");
                        while (scLine.hasNext()) {
                            CPU cpu = new CPU(new Calcul(scLine.next()));
                            if (!cpu.isNull())
                                l_CPU.add(cpu);
                        }
                        //scLine.close();
                        break;
                    case "GPU":
                        //System.out.println("OuiGPU");
                        while (scLine.hasNext()) {
                            GPU gpu = new GPU(new Calcul(scLine.next()));
                            if (!gpu.isNull())
                                l_GPU.add(gpu);
                        }
                        //scLine.close();
                        break;
                    case "I/O":
                    case "IO":
                        //System.out.println("OuiIO");
                        while (scLine.hasNext()) {
                            IO io = new IO(new Calcul(scLine.next()));
                            if (!io.isNull()) {
                                //System.out.println(io.flopsToString());
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
                        while (scLine.hasNext()) {
                            scLine.next();
                            nbTaches++;
                        }

                        Job job = new Job();

                        //On lit une nouvelle ligne par tache, qu'on ajoutera au Job
                        for (int i = 1; i <= nbTaches; i++) {

                            //On scan une nouvelle ligne
                            line = sc.nextLine();
                            scLine = new Scanner(line);

                            //System.out.println(line);

                            //On ignore les deux premiers mots, " Tx " et " = ".
                            scLine.next();
                            scLine.next();

                            //On lit le type de Serveur
                            String typeServ = scLine.next();
                            //On lit la puissance du calculteur
                            Calcul nbOp = new Calcul(scLine.next());
                            //On lit les dépendances.
                            //On lit tout le reste de la ligne pour obtenir les dépendances.
                            String str_dependance = "";
                            while (scLine.hasNext()) {
                                //System.out.println(str_dependance);
                                str_dependance += scLine.next();
                            }
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

        //Le nom est égale au nom du fichier -4 caractères pour virer le .txt
        openedFname = fname.substring(0, fname.length()-4);

        return true;
    }

    public static void methodeGlouton(boolean save) {

        methodeGlouton(l_CPU, l_GPU, l_IO, l_Jobs, save);
    }

    public static void methodeGlouton(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, ArrayList<Job> listJob, boolean save) {


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

        // Pour tester l'efficacité des algo sur des listes mélangées.
        /*
        Collections.shuffle(listTachesCPU);
        Collections.shuffle(listTachesGPU);
        Collections.shuffle(listTachesIO);
        */

        //On commence à chronométrer la durée de la méthode :
        long startTime = System.nanoTime();

        //Tant que tout les jobs ne sont pas fini, on continue d'assigner des taches aux serveurs
        while (!tachesCPUfini || !tachesGPUfini || !tachesIOfini) {

            /* Assignation des tâches CPU */
            //region CPU
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesCPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesCPU);

                //Si tacheActuelle == null : Pas de tache, elle dépendent toute d'un autre type de tache
                if ( tacheActuelle != null ) {

                    //On cherche le serveur qui finira la tache le plus tôt.
                    meilleurCPU = (CPU) tacheActuelle.serveurQuiFiniraLePlusTot(listCpu);
                    //On l'ajoute à sa liste de tache
                    meilleurCPU.add(tacheActuelle);
                    //On retire la tache de la liste des taches à faire
                    listTachesCPU.remove(tacheActuelle);

                    //Si il n'y a plus de tache dans la liste, on a fini d'assigner toutes les taches.
                    if (listTachesCPU.size() == 0)
                        tachesCPUfini = true;
                }
            }
            //endregion


            /* Assignation des tâches GPU */
            //region GPU
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesGPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesGPU);

                //Si tacheActuelle == null : Pas de tache, elle dépendent toute d'un autre type de tache
                if (tacheActuelle != null) {
                    meilleurGPU = (GPU) tacheActuelle.serveurQuiFiniraLePlusTot(listGpu);
                    meilleurGPU.add(tacheActuelle);
                    listTachesGPU.remove(tacheActuelle);

                    //Si il n'y a plus de tache dans la liste, on a fini d'assigner toutes les taches.
                    if (listTachesGPU.size() == 0)
                        tachesGPUfini = true;
                }
            }
            //endregion

            /* Assignation des tâches IO */
            //region IO
            //Tant qu'on a pas fini toutes les taches, on en cherche une à assigner.
            if (!tachesIOfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                tacheActuelle = Tache.premiereDisponible(listTachesIO);

                //Si tacheActuelle == null : Pas de tache, elle dépendent toute d'un autre type de tache
                if (tacheActuelle != null)  {
                    //On cherche le serveur qui finira la tache le plus tôt.
                    meilleurIO = (IO) tacheActuelle.serveurQuiFiniraLePlusTot(listIo);
                    //On l'ajoute à sa liste de tache
                    meilleurIO.add(tacheActuelle);
                    //On retire la tache de la liste des taches à faire
                    listTachesIO.remove(tacheActuelle);

                    //Si il n'y a plus de tache dans la liste, on a fini d'assigner toutes les taches.
                    if (listTachesIO.size() == 0)
                        tachesIOfini = true;
                }

            }
            //endregion
        }


        //System.out.println("repeat = " + repeat);
        //On calcul le temps total de l'exécution
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        executionTotal[1] += executionTime / 1000000;
        tempsResolutionTotal[1] += Serveur.tempsTotalCalculDesTaches(listCpu, listGpu, listIo);

        //On sauvegarde la solution dans un fichier
        if (save)
            saveSolution(listCpu, listGpu, listIo, openedFname+"_soluGlout.txt", executionTime);

    }

    public static void methodeNaive(boolean save) {
        methodeNaive(l_CPU, l_GPU, l_IO, l_Jobs, save);
    }

    public static void methodeNaive(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, ArrayList<Job> listJob, boolean save){

        boolean tachesCPUfini = false;
        boolean tachesGPUfini = false;
        boolean tachesIOfini = false;

        CPU bestCPU;
        GPU bestGPU;
        IO bestIO;

        ArrayList<Tache> listTachesCPU = new ArrayList<>();
        ArrayList<Tache> listTachesGPU = new ArrayList<>();
        ArrayList<Tache> listTachesIO = new ArrayList<>();

        listJob.forEach(job -> {
            listTachesCPU.addAll(Tache.tachesParRessource(job.getTaches(), "CPU"));
            listTachesGPU.addAll(Tache.tachesParRessource(job.getTaches(), "GPU"));
            listTachesIO.addAll(Tache.tachesParRessource(job.getTaches(), "IO"));
        });

        // Pour tester l'efficacité des algo sur des listes mélangées.
        /*
        Collections.shuffle(listTachesCPU);
        Collections.shuffle(listTachesGPU);
        Collections.shuffle(listTachesIO);
        */

        int num = 0; // numero des taches dans les jobs

        long startTime = System.nanoTime(); // timer

        while (!tachesCPUfini || !tachesGPUfini || !tachesIOfini) {

            /* Numero de tache */
            //region numero tache
            num++;

            //region CPU
            if (!tachesCPUfini) {

                //On récupère toutes les taches de la couche i : càd les taches numéro i d'un job.
                ArrayList<Tache> listTachesCPUi = new ArrayList<>();
                for (Tache taski : listTachesCPU) {
                    if (taski.getNum() == num) {
                        listTachesCPUi.add(taski);
                    }
                }

                //On assigne toutes les taches de la couche i ( garantie sans dépendances )
                for (Tache taskCPU : listTachesCPUi) {

                    //On lui assigne le serveur qui finira la tache le plus tôt.
                    bestCPU = (CPU) taskCPU.serveurQuiFiniraLePlusTot(listCpu);
                    bestCPU.add(taskCPU);

                    //On retire la tache assigné à la liste des tâches à faire de ce type de serveur.
                    listTachesCPU.remove(taskCPU);
                }
                //Si la liste des taches à faire est vide, alors on arrêtera d'assigner des taches CPU
                if (listTachesCPU.size() == 0)
                    tachesCPUfini = true;

            }
            //endregion

            //region GPU
            if (!tachesGPUfini) {

                //On récupère toutes les taches de la couche i : càd les taches numéro i d'un job.
                ArrayList<Tache> listTachesGPUi = new ArrayList<>();
                for (Tache taski : listTachesGPU) {
                    if (taski.getNum() == num) {
                        listTachesGPUi.add(taski);
                    }
                }

                //On assigne toutes les taches de la couche i ( garantie sans dépendances )
                for (Tache taskGPU : listTachesGPUi) {
                    //On lui assigne le serveur qui finira la tache le plus tôt.
                    bestGPU = (GPU) taskGPU.serveurQuiFiniraLePlusTot(listGpu);
                    bestGPU.add(taskGPU);

                    //On retire la tache assigné à la liste des tâches à faire de ce type de serveur.
                    listTachesGPU.remove(taskGPU);
                }

                //Si la liste des taches à faire est vide, alors on arrêtera d'assigner des taches GPU
                if (listTachesGPU.size() == 0)
                    tachesGPUfini = true;


            }
            //end region

            //region IO
            if (!tachesIOfini) {

                //On récupère toutes les taches de la couche i : càd les taches numéro i d'un job.
                ArrayList<Tache> listTachesIOi = new ArrayList<>();
                for (Tache taski : listTachesIO) {
                    if (taski.getNum() == num) {
                        listTachesIOi.add(taski);
                    }
                }

                //On assigne toutes les taches de la couche i ( garantie sans dépendances )
                for (Tache taskIO : listTachesIOi) {

                    //On lui assigne le serveur qui finira la tache le plus tôt.
                    bestIO = (IO) taskIO.serveurQuiFiniraLePlusTot(listIo);
                    bestIO.add(taskIO);

                    //On retire la tache assigné à la liste des tâches à faire de ce type de serveur.
                    listTachesIO.remove(taskIO);
                }
                //Si la liste des taches à faire est vide, alors on arrêtera d'assigner des taches IO
                if (listTachesIO.size() == 0)
                    tachesIOfini = true;

            }
            //endregion

        }


        //On calcul le temps total de l'exécution
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;


        executionTotal[2] += executionTime / 1000000;
        tempsResolutionTotal[2] += Serveur.tempsTotalCalculDesTaches(listCpu, listGpu, listIo);

        //On sauvegarde la solution dans un fichier
        if (save)
            saveSolution(listCpu, listGpu, listIo, openedFname+"_soluNaive.txt", executionTime);
    }

    public static void methodeAleatoire(boolean save) {
        methodeAleatoire(l_CPU, l_GPU, l_IO, l_Jobs, save);
    }

    //Méthode qui assigne aléatoirement des taches aux serveurs
    public static void methodeAleatoire(ArrayList<CPU> listCpu, ArrayList<GPU> listGpu, ArrayList<IO> listIo, ArrayList<Job> listJob, boolean save) {
        //region - INITIALISATION
        boolean tachesCPUfini = false;
        boolean tachesGPUfini = false;
        boolean tachesIOfini = false;

        CPU choixCPU;
        GPU choixGPU;
        IO choixIO;

        Tache choixtacheCPU;
        Tache choixtacheGPU;
        Tache choixtacheIO;


        ArrayList<Tache> listTachesCPU = new ArrayList<>();
        ArrayList<Tache> listTachesGPU = new ArrayList<>();
        ArrayList<Tache> listTachesIO = new ArrayList<>();


        //On ajoute toutes les taches de touts les jobs à la liste des taches. On a pas besoin de différencier les jobs pour nos calculs.

        listJob.forEach(job -> {
            listTachesCPU.addAll(Tache.tachesParRessource(job.getTaches(), "CPU"));
            listTachesGPU.addAll(Tache.tachesParRessource(job.getTaches(), "GPU"));
            listTachesIO.addAll(Tache.tachesParRessource(job.getTaches(), "IO"));
        });
        //endregion

        // Pour tester l'efficacité des algo sur des listes mélangées.
        /*
        Collections.shuffle(listTachesCPU);
        Collections.shuffle(listTachesGPU);
        Collections.shuffle(listTachesIO);
        */

        //On commence à chronométrer la durée de la méthode :
        long startTime = System.nanoTime();

        while (!tachesCPUfini || !tachesGPUfini || !tachesIOfini) {
            //region - CPU
            if (!tachesCPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                choixtacheCPU = Tache.premiereDisponible(listTachesCPU);

                //Si tache == null, toute les taches de la liste dépendent de dépendances encore non-résolus.
                if (choixtacheCPU != null) {
                    int index_choixCPU = Calcul.random(0, listCpu.size() - 1);
                    choixCPU = listCpu.get(index_choixCPU);
                    choixCPU.add(choixtacheCPU);

                    //On retire la tache de la liste des tache à faire et on vérifie si c'était la dernière.
                    listTachesCPU.remove(choixtacheCPU);
                    if ( listTachesCPU.size() == 0 )
                        tachesCPUfini = true;
                }
            }
            //endregion

            //region - GPU
            if (!tachesGPUfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                choixtacheGPU = Tache.premiereDisponible(listTachesGPU);

                //Si tache == null, toute les taches de la liste dépendent de dépendances encore non-résolus.
                if (choixtacheGPU != null)  {
                    //int index_choixGPU = 0 + (int)(Math.random() * ((listGpu.size() - 0)+1));
                    int index_choixGPU = Calcul.random(0, listGpu.size() - 1);
                    choixGPU = listGpu.get(index_choixGPU);
                    choixGPU.add(choixtacheGPU);

                    //On retire la tache de la liste des tache à faire et on vérifie si c'était la dernière.
                    listTachesGPU.remove(choixtacheGPU);
                    if ( listTachesGPU.size() == 0 )
                        tachesGPUfini = true;
                }
            }
            //endregion

            //region - IO
            if (!tachesIOfini) {

                //Préselection de la tache, on prend la première à faire qui viens.
                choixtacheIO = Tache.premiereDisponible(listTachesIO);

                //Si tache == null, toute les taches de la liste dépendent de dépendances encore non-résolus.
                if (choixtacheIO != null) {
                    //int index_choixIO = 0 + (int)(Math.random() * ((listIo.size() - 0)+1));
                    int index_choixIO = Calcul.random(0, listIo.size() - 1);
                    choixIO = listIo.get(index_choixIO);
                    choixIO.add(choixtacheIO);

                    //On retire la tache de la liste des tache à faire et on vérifie si c'était la dernière.
                    listTachesIO.remove(choixtacheIO);
                    if ( listTachesIO.size() == 0 )
                        tachesIOfini = true;
                }
            }
            //endregion
        }


        //On calcul le temps total de l'exécution
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        //On sauvegarde les résultats pour les calculs de temps moyens
        executionTotal[0] += executionTime / 1000000;
        tempsResolutionTotal[0] += Serveur.tempsTotalCalculDesTaches(listCpu, listGpu, listIo);

        //On sauvegarde la solution
        if (save)
            saveSolution(listCpu, listGpu, listIo, openedFname+"_soluAlea.txt", executionTime);

    }


}

