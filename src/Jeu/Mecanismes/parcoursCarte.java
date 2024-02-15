package Jeu.Mecanismes;

import java.util.Scanner;

import Design.Design;
import Donjon.CreationDonjon.DonjonDesTenebres;
import Donjon.CreationDonjon.DonjonDesTrolls;
import Personnages.Personnage;

public class parcoursCarte {
    public static void afficherCarte(Carte carte) {
        Design.titreAffichage_2("Voici la carte du jeu :");
        carte.afficherCarte();
    }

    public static void demanderCoordonnees(Scanner scanner, Carte carte, Personnage personnage) {
        do {
            afficherCarte(carte);

            Design.titreAffichage_1("Entrez les coordonnées de la nouvelle position : ");
            Design.titreAffichage_4("Position en X : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un numéro valide pour la position en X.");
                scanner.next();
            }
            int nouvellePosX = scanner.nextInt();

            Design.titreAffichage_2("Position en Y : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un numéro valide pour la position en Y.");
                scanner.next();
            }
            int nouvellePosY = scanner.nextInt();

            // Déplacez le joueur sur la carte
            carte.deplacerJoueur(nouvellePosX, nouvellePosY);

            Design.titreAffichage_6("Le joueur a été déplacé avec succès à la position : (" + nouvellePosX + ", " + nouvellePosY + ")");
            scanner.nextLine();

            // Vérifiez si le joueur est à l'entrée d'un donjon
            if (carte.estEntreeDonjon(personnage.getPositionX(), personnage.getPositionY(), DonjonDesTenebres.getEntreeX(), DonjonDesTenebres.getEntreeY()) ||
                    carte.estEntreeDonjon(personnage.getPositionX(), personnage.getPositionY(), DonjonDesTrolls.getEntreeX(), DonjonDesTrolls.getEntreeY())) {
                // Si oui, sortez de la boucle
                scanner.nextLine();
                Design.effacerConsole();
                break;
            }


            // Nettoyer le scanner pour éviter les problèmes de saisie
            scanner.nextLine();
            Design.effacerConsole();

            // Demander à l'utilisateur s'il veut entrer de nouvelles coordonnées
            Design.titreAffichage_1("Voulez-vous entrer de nouvelles coordonnées ? (Oui/Non) : ");
            String choix = scanner.nextLine().trim().toLowerCase();
            Design.effacerConsole();

            // Si l'utilisateur appuie simplement sur Entrée, considérez-le comme "oui"
            if (choix.isEmpty()) {
                choix = "oui";
            }

            // Si l'utilisateur répond "Non", sortir de la boucle
            if (!choix.equals("oui")) {
                break;
            }

        } while (true);
    }
}