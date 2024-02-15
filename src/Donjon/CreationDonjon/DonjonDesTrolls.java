package Donjon.CreationDonjon;

import Donjon.Donjon;
import Ennemis.Boss;
import Ennemis.Ennemi;
import Equipements.Arme;
import Jeu.Mecanismes.Carte;
import Salles.Salle;

import java.util.ArrayList;

public class DonjonDesTrolls {
    private static int entreeX = 7;
    private static int entreeY = 5;

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
        donjonSalles.add(creerSalle("Salle Boss", new Object[]{}, "Dieu Déchu", 180, 40, 7));

        Arme reward = new Arme("Epée légendaire", 50, 0);
        return new Donjon("Donjon des Trolls", donjonSalles, reward);
    }

    public static void entreeDonjon(Carte carte) {
        int posXDonjon = entreeX; // Position en X de l'entrée du donjon
        int posYDonjon = entreeY; // Position en Y de l'entrée du donjon

        // Vérifiez d'abord si les nouvelles coordonnées sont valides
        if (carte.coordValides(posXDonjon, posYDonjon)) {
            // Mettez à jour la carte pour placer l'entrée du donjon à la nouvelle position
            carte.carte[posXDonjon][posYDonjon] = 'E'; // 'E' représente l'entrée du donjon
        } else {
            System.out.println("Les coordonnées spécifiées pour l'entrée du donjon ne sont pas valides.");
        }
    }

    public static int getEntreeX() {
        return entreeX;
    }

    public static int getEntreeY() {
        return entreeY;
    }
}
