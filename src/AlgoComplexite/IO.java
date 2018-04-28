package AlgoComplexite;


public class IO extends Serveur {

    public IO(){
        super(3, 9);
        this.nom = "IO";
    }

    public IO(Calcul vitesseCalcul) {
        super(vitesseCalcul);
        this.nom = "IO";
    }

}
