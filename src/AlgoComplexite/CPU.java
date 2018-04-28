package AlgoComplexite;


public class CPU extends Serveur {


    public CPU() {
        //Un CPU a une puissance allant de 1G à 500G
        super(10, 12);
        this.nom = "CPU";
    }

    public CPU(Calcul vitesseCalcul) {
        super(vitesseCalcul);
        this.nom = "CPU";
    }


}
