package AlgoComplexite;

public class Test {

    public static void main(String[] args) {

        Serveur cpu = new CPU(2);

        for (int i = 0; i <= 20; i++)
            System.out.println("Valeur entre "+4+" et "+10+" : "+cpu.random(4, 10)+ "\n");

    }
}
