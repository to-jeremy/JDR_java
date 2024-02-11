package Jeu.Mecanismes;

import Personnages.Personnage;

public class Carte {
    public char[][] carte;
    public int joueurX;
    public int joueurY;

    private int largeur;
    private int hauteur;

    public Carte(int largeur, int hauteur) {
        this.largeur = largeur; // Initialise la largeur
        this.hauteur = hauteur; // Initialise la hauteur
        carte = new char[largeur][hauteur];

        // Initialise la carte avec des zones par défaut
        // Exemple simplifié :
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                carte[i][j] = 'P'; // Plaine par défaut
            }
        }

        // Position initiale du joueur
        joueurX = largeur / 2;
        joueurY = hauteur / 2;
    }

    public void afficherCarte() {
        for (int j = 0; j < carte[0].length; j++) {
            for (int i = 0; i < carte.length; i++) {
                if (i == joueurX && j == joueurY) {
                    System.out.print("X "); // Affiche le joueur
                } else {
                    System.out.print(carte[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void deplacerJoueur(int deltaX, int deltaY) {
        int nouveauX = joueurX + deltaX;
        int nouveauY = joueurY + deltaY;
        // Vérifie si le déplacement est valide
        if (nouveauX >= 0 && nouveauX < carte.length && nouveauY >= 0 && nouveauY < carte[0].length) {
            joueurX = nouveauX;
            joueurY = nouveauY;
        }
    }

    public boolean coordValides(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    public char getCase(int x, int y) {
        return carte[x][y];
    }

    public int getPositionX(Personnage personnage) {
        return joueurX;
    }

    public int getPositionY(Personnage personnage) {
        return joueurY;
    }
}
