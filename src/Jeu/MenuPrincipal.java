package Jeu;

import Classes.Guerrier;
import Classes.Mage;
import Classes.Voleur;
import Design.Design;
import Jeu.Mecanismes.Carte;
import Jeu.Mecanismes.parcoursCarte;
import Jeu.Mecanismes.parcoursDonjon;
import Personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {
    public static Personnage creerNouveauPersonnage(Scanner scanner, ArrayList<Personnage> personnages, Carte carte) {
        Design.titreAffichage_3("Création d'un nouveau personnage");

        String nomPerso = "";
        while (nomPerso.trim().isEmpty()) {
            Design.titreAffichage_2("Nom du personnage : ");
            nomPerso = scanner.nextLine().trim(); // Trim pour supprimer les espaces avant et après

            if (nomPerso.isEmpty()) {
                Design.effacerConsole();
                Design.titreAffichage_2("Le nom ne peut pas être vide !");
                scanner.nextLine(); // Pour attendre que l'utilisateur appuie sur entrée
                Design.effacerConsole();
            }
        }

        String[] classes = {"Guerrier", "Mage", "Voleur"};

        int choixClasse = 0;
        while (choixClasse < 1 || choixClasse > classes.length) {
            Design.effacerConsole();
            Design.titreAffichage_2("Choisissez une classe pour votre personnage :");

            for (int i = 0; i < classes.length; i++) {
                System.out.println((i + 1) + ". " + classes[i]);
            }
            Design.separateurAffichage(30);

            System.out.print("Choix : ");
            String input = scanner.nextLine().trim();
            Design.effacerConsole();

            if (!input.isEmpty()) {
                // Si l'entrée n'est pas vide, on essaie de la convertir en entier
                try {
                    choixClasse = Integer.parseInt(input);
                    if (choixClasse < 1 || choixClasse > classes.length) {
                        Design.titreAffichage_1("Choix invalide. Veuillez choisir un numéro entre 1 et " + classes.length + ".");
                        scanner.nextLine();
                        Design.effacerConsole();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numéro valide.");
                }
            } else {
                Design.effacerConsole();
                Design.titreAffichage_1("Vous devez entrer un numéro de classe.");
                scanner.nextLine();
            }
        }

        String nomClasse = classes[choixClasse - 1];

        Personnage personnage = null;
        switch (choixClasse) {
            case 1:
                personnage = new Guerrier(nomPerso, 200, 10, 20, 12, carte);
                break;
            case 2:
                personnage = new Mage(nomPerso, 200, 10, 20, 12, carte);
                break;
            case 3:
                personnage = new Voleur(nomPerso, 200, 10, 20, 12, carte);
                break;
            default:
                System.out.println("Classe invalide. Le personnage sera un guerrier par défaut.");
                personnage = new Guerrier(nomPerso, 200, 10, 20, 12, carte);
                break;
        }
        personnages.add(personnage);

        // Placer l'entrée du donjon à une position à droite du joueur sur la carte
        int nouvellePosXDonjon = carte.joueurX + 1; // Position en X de l'entrée du donjon
        int posYDonjon = carte.joueurY; // La position en Y de l'entrée du donjon est la même que celle du joueur

        // Vérifiez d'abord si les nouvelles coordonnées sont valides
        if (carte.coordValides(nouvellePosXDonjon, posYDonjon)) {
            // Mettez à jour la carte pour placer l'entrée du donjon à la nouvelle position
            carte.carte[nouvellePosXDonjon][posYDonjon] = 'D'; // 'D' représente l'entrée du donjon
        } else {
            System.out.println("Les coordonnées spécifiées ne sont pas valides.");
        }

        Design.effacerConsole();
        Design.titreAffichage_1("Vous avez choisi la classe " + nomClasse + " pour le personnage " + nomPerso + ".");

        scanner.nextLine();
        Design.effacerConsole();

        afficherCarte(carte);

        return personnage;
    }

    public static void choisirPersonnage(Scanner scanner, ArrayList<Personnage> personnages) {
        while (true) {
            Design.titreAffichage_1("Choisir un personnage existant");

            if (personnages.isEmpty()) {
                Design.titreAffichage_6("Aucun personnage disponible.");

                scanner.nextLine();
                Design.effacerConsole();

                return;
            }

            Design.titreAffichage_4("Liste des personnages :");
            for (int i = 0; i < personnages.size(); i++) {
                System.out.println((i + 1) + ". " + personnages.get(i).getNomPerso());
            }

            Design.titreAffichage_2("Choisissez un personnage (ou tapez 0 pour revenir au menu principal) : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la fin de ligne après nextInt()

            if (choix == 0) {
                Design.effacerConsole();

                menuPrincipal(scanner, personnages);
            }

            if (choix >= 1 && choix <= personnages.size()) {
                Personnage personnageChoisi = personnages.get(choix - 1);

                Design.effacerConsole();
                Design.titreAffichage_2("Vous avez choisi le personnage " + personnageChoisi.getNomPerso() + " !");
                scanner.nextLine();
                Design.effacerConsole();

                return;
            } else {
                Design.effacerConsole();
                Design.titreAffichage_2("Choix invalide.");
                scanner.nextLine();
                Design.effacerConsole();
            }
        }
    }

    public static void supprimerPersonnage(Scanner scanner, ArrayList<Personnage> personnages) {
        while (true) {
            Design.titreAffichage_1("Supprimer un personnage existant");

            if (personnages.isEmpty()) {
                Design.titreAffichage_6("Aucun personnage disponible.");

                scanner.nextLine();
                Design.effacerConsole();

                return;
            }

            Design.titreAffichage_4("Liste des personnages :");
            for (int i = 0; i < personnages.size(); i++) {
                System.out.println((i + 1) + ". " + personnages.get(i).getNomPerso());
            }

            Design.titreAffichage_2("Choisissez un personnage à supprimer (ou tapez 0 pour revenir au menu principal) : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 0) {
                Design.effacerConsole();

                return;
            }

            if (choix >= 1 && choix <= personnages.size()) {
                Personnage personnageSupprime = personnages.remove(choix - 1);

                Design.effacerConsole();
                Design.titreAffichage_2(personnageSupprime.getNomPerso() + " a été supprimé avec succès !");
                scanner.nextLine();
                Design.effacerConsole();

                return;
            } else {
                Design.effacerConsole();
                Design.titreAffichage_2("Choix invalide. Veuillez choisir un numéro valide.");
                scanner.nextLine();
                Design.effacerConsole();
            }
        }
    }

    public static void afficherCarte(Carte carte) {
        Design.titreAffichage_2("Voici la carte du jeu :");
        carte.afficherCarte();
    }

    public static void menuPrincipal(Scanner scanner, ArrayList<Personnage> personnages) {
        Carte carte = new Carte(10, 10);

        boolean continuer = true;
        while (continuer) {
            Design.titreAffichage_5("Le Village Obscur de Darkofu");

            Design.titreAffichage_1("Menu Principal");
            System.out.println("1. Créer un nouveau personnage");
            System.out.println("2. Choisir un personnage existant");
            System.out.println("3. Supprimer un personnage existant");
            System.out.println("4. Quitter");

            Design.separateurAffichage(40);

            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la fin de ligne après nextInt()

            switch (choix) {
                case 1:
                    Design.effacerConsole();

                    // Créer un nouveau personnage
                    Personnage personnage = creerNouveauPersonnage(scanner, personnages, carte);

                    // Demandez aux joueurs d'entrer les coordonnées vers lesquelles ils veulent se déplacer
                    parcoursCarte.demanderCoordonnees(scanner,carte, personnage);

                    break;
                case 2:
                    // Choisir un personnage existant
                    Design.effacerConsole();

                    choisirPersonnage(scanner, personnages);
                    break;
                case 3:
                    // Supprimer un personnage existant
                    Design.effacerConsole();

                    supprimerPersonnage(scanner, personnages);
                    break;
                case 4:
                    Design.effacerConsole();

                    // Quitter le menu principal
                    Design.titreAffichage_2("Merci d'avoir joué au jeu !");
                    Design.titreAffichage_4("© 2024 | Le Village Obscur de Darkofu");

                    System.exit(0);
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
                    break;
            }

            // Si un personnage a été créé ou choisi, appeler la fonction de parcours du donjon
            if (!personnages.isEmpty()) {
                parcoursDonjon.parcourirDonjon(scanner, personnages);
                continuer = false; // Sortir du menu principal après avoir parcouru le donjon
            }
        }
    }
}
