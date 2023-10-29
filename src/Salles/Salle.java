package Salles;

import Ennemis.Ennemi;

import java.util.ArrayList;

public class Salle {
    private String nomSalle;
    private ArrayList<Ennemi> ennemis;

    public Salle(String nomSalle, ArrayList<Ennemi> ennemis) {
        this.nomSalle = nomSalle;
        this.ennemis = ennemis;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public String getNomSalle() {
        return this.nomSalle;
    }
}
