package Donjon.CreationDonjon;

import Donjon.Donjon;
import Ennemis.Boss;
import Ennemis.Ennemi;
import Equipements.Arme;
import Salles.Salle;

import java.util.ArrayList;

public class DonjonDesTenebres {
    private static Salle creerSalle(String nomSalle, Object[] ennemis, String nomBoss, int hpBoss, int attaqueBoss, int defenseBoss) {
        ArrayList<Ennemi> ennemiList = new ArrayList<>();

        // Ajout des ennemis
        for (int i = 0; i < ennemis.length; i += 4) {
            String nomEnnemi = (String) ennemis[i];
            int hp = (int) ennemis[i + 1];
            int attaque = (int) ennemis[i + 2];
            int defense = (int) ennemis[i + 3];
            ennemiList.add(new Ennemi(nomEnnemi, hp, attaque, defense));
        }

        // Ajout du Doss s'il est spécifié
        if (nomBoss != null && hpBoss > 0 && attaqueBoss > 0 && defenseBoss > 0) {
            Boss boss = new Boss(nomBoss, hpBoss, attaqueBoss, defenseBoss);
            ennemiList.add(boss);
        }

        return new Salle(nomSalle, ennemiList);
    }

    public static Donjon creerDonjon() {
        ArrayList<Salle> donjonSalles = new ArrayList<>();

        // Création des salles
        donjonSalles.add(creerSalle("Salle 1", new Object[]{"Gobelin", 70, 23, 15, "Tofu", 80, 25, 14}, null, 0, 0, 0));
        donjonSalles.add(creerSalle("Salle 2", new Object[]{"Bouftou", 90, 27, 13, "Sanglier", 100, 30, 11}, null, 0, 0, 0));
        donjonSalles.add(creerSalle("Salle 3", new Object[]{"Ours", 110, 32, 9, "Troll", 130, 35, 10}, null, 0, 0, 0));
        donjonSalles.add(creerSalle("Salle 4", new Object[]{"Dragon", 140, 37, 8}, null, 0, 0, 0));
        donjonSalles.add(creerSalle("Salle Boss", new Object[]{"Hydra", 80, 20, 10, "Elfe noir", 80, 20, 10}, "Dieu Déchu", 180, 40, 7));

        Arme reward = new Arme("Epée légendaire", 50, 0);
        return new Donjon("Donjon des Ténèbres", donjonSalles, reward);
    }
}