package Donjon;

import Equipements.Equipement;
import Salles.Salle;

import java.util.ArrayList;

public class Donjon {
    private String nomDonjon;
    private ArrayList<Salle> salles;
    private Equipement reward;

    public Donjon(String nomDonjon, ArrayList<Salle> salles, Equipement reward) {
        this.nomDonjon = nomDonjon;
        this.salles = salles;
        //this.boss = boss;
        this.reward = reward;
    }

    public String getNomDonjon() {
        return nomDonjon;
    }

    public ArrayList<Salle> getSalles() {
        return salles;
    }

    public Equipement getRecompense() {
        return reward;
    }
}
