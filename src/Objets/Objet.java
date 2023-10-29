package Objets;

public abstract class Objet{
    private String nomObjet;
    private int or;
    public Objet(String nomObjet, int or) {
        this.nomObjet = nomObjet;
        this.or = or;
    }

    public String getNomObjet() {
        return nomObjet;
    }
}
