package Jeu.Mecanismes;

import Design.Design;
import Donjon.CreationDonjon.DonjonDesTenebres;
import Donjon.Donjon;
import Ennemis.Ennemi;
import Equipements.Arme;
import Equipements.Equipement;
import Jeu.MenuPrincipal;
import Objets.Objet;
import Objets.Potions.Potion;
import Personnages.Personnage;
import Salles.Salle;

import java.util.ArrayList;
import java.util.Scanner;

public class parcoursDonjon {
    public static void parcourirDonjon(Scanner scanner, ArrayList<Personnage> personnages) {
        // Obtenez le personnage actif
        Personnage personnage = personnages.get(personnages.size() - 1);

        // Obtenez la carte du personnage
        Carte carte = personnage.getCarte();

        // Vérifiez si le joueur est à l'entrée du donjon
        if (carte.getCase(personnage.getPositionX(), personnage.getPositionY()) == 'D') {
            Design.titreAffichage_5("Voici le donjon du jeu !");
        } else {
            // Si le joueur n'est pas à l'entrée du donjon, affichez un message
            Design.titreAffichage_1("Vous devez vous déplacer vers l'entrée du donjon pour y accéder.");

            scanner.nextLine();
            Design.effacerConsole();

            // Demandez les nouvelles coordonnées en utilisant la classe parcoursCarte
            parcoursCarte.demanderNouvellesCoordonnees(scanner, carte, personnage);
            return;
        }

        // Créer le donjon
        Donjon donjon = DonjonDesTenebres.creerDonjon();
        Arme reward = (Arme) donjon.getRecompense();

        // Parcourir le donjon
        Design.titreAffichage_1("Vous entrez dans le donjon !");

        System.out.println("\nEntrer n'importe quoi pour continuer...");
        scanner.nextLine();

        Design.effacerConsole();

        for (Salle salle : donjon.getSalles()) {
            Design.titreAffichage_2("Vous êtes dans la " + salle.getNomSalle());

            System.out.println("\nCliquer sur entrée pour continuer...");
            scanner.nextLine();
            Design.effacerConsole();

            ArrayList<Ennemi> ennemis = salle.getEnnemis();

            while (!ennemis.isEmpty()) {
                for (Personnage joueur : personnages) {
                    if (ennemis.isEmpty()) {
                        break;
                    }
                    if (!joueur.estMort()) {
                        Ennemi ennemi = ennemis.get(0);

                        boolean actionEffectuee = false;

                        while (!actionEffectuee) {

                            Design.titreAffichage_3(ennemi.getNomEnnemi() + "\nHP : " + ennemi.getHp() + "/" + ennemi.getMaxHp());
                            Design.titreAffichage_3("C'est au tour de " + joueur.getNomPerso() + " :");
                            Design.titreAffichage_2(joueur.getNomPerso() + "\nHP : " + joueur.getHp() + "/" + joueur.getMaxHp());
                            Design.titreAffichage_4("Que voulez-vous faire ?");
                            System.out.println("1. Attaquer");
                            System.out.println("2. Utiliser un objet");
                            System.out.println("3. Afficher les informations du personnage");
                            System.out.println("4. Revenir au menu principal");
                            System.out.println("5. Quitter le donjon");

                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            Design.effacerConsole();

                            if (choice == 1) {

                                int dommage = joueur.getAttaque() - ennemi.getDefense();
                                if (dommage < 0) {
                                    dommage = 0;
                                }
                                Design.titreAffichage_2(joueur.getNomPerso() + " attaque " + ennemi.getNomEnnemi() + " et lui inflige " + dommage + " points de dégâts");
                                ennemi.subirDommage(dommage);

                                scanner.nextLine();
                                Design.effacerConsole();

                                actionEffectuee = true;

                                if (ennemi.estMort()) {
                                    Design.titreAffichage_2(ennemi.getNomEnnemi() + " est mort !");
                                    ennemis.remove(0);

                                    scanner.nextLine();
                                    Design.effacerConsole();
                                }
                            } else if (choice == 2) {

                                if (joueur.getObjets().isEmpty()) {
                                    Design.effacerConsole();
                                    Design.titreAffichage_2("L'inventaire de " + joueur.getNomPerso() + " est vide !");

                                    scanner.nextLine();
                                    Design.effacerConsole();

                                    continue;
                                } else {
                                    Design.titreAffichage_2("Quel objet voulez-vous utiliser ?");

                                    for (int i = 0; i < joueur.getObjets().size(); i++) {
                                        System.out.println((i + 1) + ". " + joueur.getObjets().get(i).getNomObjet());
                                    }
                                }

                                int objetChoice = scanner.nextInt();
                                scanner.nextLine();
                                Design.effacerConsole();
                                Objet objet = joueur.getObjets().get(objetChoice - 1);

                                actionEffectuee = true;

                                if (objet instanceof Potion) {
                                    joueur.utilisePotion((Potion) objet);

                                    scanner.nextLine();
                                    Design.effacerConsole();
                                } else {
                                    Design.titreAffichage_2("Cet objet ne peut pas être utilisé en combat !");

                                    scanner.nextLine();
                                    Design.effacerConsole();
                                }

                            } else if (choice == 3) {

                                joueur.afficherInfosPersonnage();

                                scanner.nextLine();
                                Design.effacerConsole();

                            } else if (choice == 4){

                                MenuPrincipal.menuPrincipal(scanner, personnages);

                            } else if (choice == 5) {
                                Design.titreAffichage_2("Merci d'avoir joué au jeu de rôle du donjon !");

                                System.exit(0);
                            } else {
                                Design.titreAffichage_2("Choix invalide, passez votre tour !");

                                scanner.nextLine();
                                Design.effacerConsole();
                            }
                        }
                    }
                }

                if (!personnages.isEmpty()) {
                    for (Ennemi ennemi : ennemis) {
                        if (!ennemi.estMort()) {
                            Personnage target = personnages.get((int) (Math.random() * personnages.size()));
                            int dommage = ennemi.getAttaque() - target.getDefense();
                            if (dommage < 0) {
                                dommage = 0;
                            }
                            Design.titreAffichage_3(ennemi.getNomEnnemi() + " attaque " + target.getNomPerso() + " et lui inflige " + dommage + " points de dégâts");
                            target.subirDommage(dommage);
                            if (target.estMort()) {
                                Design.titreAffichage_4(target.getNomPerso() + " est mort !");
                                personnages.remove(target);
                            }
                            if (personnages.isEmpty()) {
                                target.perdreMana(10);
                                Design.titreAffichage_4("Malheureusement, les ennemis vont ont vaincu ! Revenez tenter votre chance une prochaine fois.");

                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
        Design.titreAffichage_5("Félicitations, vous avez vaincu le boss final !");
        Design.titreAffichage_2("Vous obtenez " + reward.getNomEquipement() + " !");

        for (Personnage joueur : personnages) {
            joueur.ajouterEquipement(reward);
            System.out.println(joueur.getNomPerso() + " :");

            if (joueur.getArme() == null) {
                Design.titreAffichage_4("Arme : aucune");
            } else {
                Design.titreAffichage_4("Arme : " + joueur.getArme().getNomArme());
            }

            for (int i = 0; i < joueur.getEquipements().size(); i++) {
                Equipement equipement = joueur.getEquipements().get(i);
                if (equipement instanceof Arme) {
                    Design.titreAffichage_4("Arme " + (i + 1) + " : " + joueur.getEquipements().get(i).getNomEquipement());
                } else {
                    Design.titreAffichage_4("Equipement " + (i + 1) + " : " + joueur.getEquipements().get(i).getNomEquipement());
                }
            }

            for (int i = 0; i < joueur.getObjets().size(); i++) {
                Objet objet = joueur.getObjets().get(i);
                if (objet instanceof Potion) {
                    Design.titreAffichage_4("Potion " + (i + 1) + " : " + joueur.getObjets().get(i).getNomObjet());
                } else {
                    Design.titreAffichage_4("Objet " + (i + 1) + " : " + joueur.getObjets().get(i).getNomObjet());
                }
            }
        }
    }
}
