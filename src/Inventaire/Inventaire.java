package Inventaire;

import Equipements.Equipement;
import Objets.Objet;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Equipement> equipements;
    private ArrayList<Objet> objets;

    public Inventaire() {
        equipements = new ArrayList<>();
        objets = new ArrayList<>();
    }

    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }

    public void ajouterObjet(Objet objet) {
        objets.add(objet);
    }

    public ArrayList<Equipement> getEquipements() {
        return equipements;
    }

    public ArrayList<Objet> getObjets() {
        return objets;
    }
}
