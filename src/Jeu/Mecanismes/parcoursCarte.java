package Jeu.Mecanismes;

import java.util.Scanner;

import Design.Design;
import Personnages.Personnage;

public class parcoursCarte {
    public static void afficherCarte(Carte carte) {
        Design.titreAffichage_2("Voici la carte du jeu :");
        carte.afficherCarte();
    }

    public static void demanderNouvellesCoordonnees(Scanner scanner, Carte carte, Personnage personnage) {
        do {
            afficherCarte(carte);

            Design.titreAffichage_1("Entrez les coordonnées de la nouvelle position : ");
            Design.titreAffichage_4("Position en X : ");
            int nouvellePosX = scanner.nextInt();
            Design.titreAffichage_2("Position en Y : ");
            int nouvellePosY = scanner.nextInt();

            // Vérifiez d'abord si les nouvelles coordonnées sont valides
            if (carte.coordValides(nouvellePosX, nouvellePosY)) {
                // Déplacez le joueur sur la carte
                carte.deplacerJoueur(nouvellePosX, nouvellePosY);
                Design.titreAffichage_6("Le joueur a été déplacé avec succès à la position : (" + nouvellePosX + ", " + nouvellePosY + ")");

                scanner.nextLine();

                // Vérifiez si le joueur est à l'entrée du donjon
                if (carte.getCase(personnage.getPositionX(), personnage.getPositionY()) == 'D') {
                    // Si oui, sortez de la boucle
                    break;
                }
            } else {
                Design.titreAffichage_6("Les coordonnées spécifiées ne sont pas valides.");

                scanner.nextLine();
            }

            // Nettoyer le scanner pour éviter les problèmes de saisie
            scanner.nextLine();
            Design.effacerConsole();

            // Demander à l'utilisateur s'il veut entrer de nouvelles coordonnées
            Design.titreAffichage_1("Voulez-vous entrer de nouvelles coordonnées ? (Oui/Non) : ");
            String choix = scanner.nextLine().trim().toLowerCase();
            Design.effacerConsole();

            // Si l'utilisateur répond "Non", sortir de la boucle
            if (!choix.equals("oui")) {
                break;
            }

        } while (true);
    }

}