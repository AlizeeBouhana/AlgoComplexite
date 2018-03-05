package AlgoComplexite;

public class Test {

    public static void main(String[] args) {

        Serveur cpu;
        Tache t;

        for (int i = 0; i <= 10; i++) {

            cpu = new CPU();
            t = new Tache("CPU");
            System.out.println("Puissance CPU : " + cpu.flopsToString());
            System.out.println("Duree de la Tache : " + t.flopsToString());
            System.out.println("    DureeTache = "+ t.dureeTache(cpu));
            System.out.println("    DureeTache2 = "+ t.dureeTache2(cpu));
            System.out.println("  --------  ");

        }

    }
}
