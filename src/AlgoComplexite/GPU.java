package AlgoComplexite;


public class GPU extends Serveur {

    public GPU(){
        //Un CPU a une puissance allant de 1T à 1000T
        super(12, 14);
        this.nom = "GPU";
    }

    public GPU(Calcul vitesseCalcul) {
        super(vitesseCalcul);
        this.nom = "GPU";
    }

}
