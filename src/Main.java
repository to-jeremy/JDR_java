import Classes.*;
import Design.*;
import Donjon.*;
import Donjon.CreationDonjon.DonjonDesTenebres;
import Ennemis.*;
import Equipements.*;
import Objets.*;
import Objets.Potions.*;
import Personnages.*;
import Salles.*;

import java.util.ArrayList;
import java.util.Scanner;

import static Jeu.MenuPrincipal.menuPrincipal;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            //Présentation du jeu
            Design.titreAffichage_1("Jeu de rôle || Jérémy TO");

            System.out.println("\nCliquer sur entrée pour continuer...");
            scanner.nextLine();
            Design.effacerConsole();

            // Créer l'équipe de personnages
            ArrayList<Personnage> personnages = new ArrayList<>();
            menuPrincipal(scanner, personnages);

            //Equipements des personnages
            Arme epee = new Arme("Épée basique", 15, 0);
            Potion potionMagique = new Potion("Soin", 0, 15, PotionType.SOIN);

            personnages.get(0).equipArme(epee);
            //personnages.get(0).ajouterEquipement(potionMagique);
            personnages.get(0).ajouterObjet(potionMagique);
            //personnages.get(0).ajouterEquipement(epee);

            // Quitter le jeu si aucun personnage n'a été créé
            if (personnages.isEmpty()) {
                Design.titreAffichage_2("Vous n'avez créé aucun personnage. Fin du jeu.");
            }

            // Créer le donjon
            /*Donjon donjon = DonjonDesTenebres.creerDonjon();
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
                    for (Personnage personnage : personnages) {
                        if (ennemis.isEmpty()) {
                            break;
                        }
                        if (!personnage.estMort()) {
                            Ennemi ennemi = ennemis.get(0);

                            boolean actionEffectuee = false;

                            while (!actionEffectuee) {

                                Design.titreAffichage_3(ennemi.getNomEnnemi() + "\nHP : " + ennemi.getHp() + "/" + ennemi.getMaxHp());
                                Design.titreAffichage_3("C'est au tour de " + personnage.getNomPerso() + " :");
                                Design.titreAffichage_2(personnage.getNomPerso() + "\nHP : " + personnage.getHp() + "/" + personnage.getMaxHp());
                                Design.titreAffichage_4("Que voulez-vous faire ?");
                                System.out.println("1. Afficher les informations du personnage");
                                System.out.println("2. Attaquer");
                                System.out.println("3. Utiliser un objet");
                                System.out.println("4. Quitter le donjon");
                                int choice = scanner.nextInt();
                                scanner.nextLine();
                                Design.effacerConsole();
                                if (choice == 1) {
                                    personnage.afficherInfosPersonnage();

                                    scanner.nextLine();
                                    Design.effacerConsole();
                                } else if (choice == 2) {

                                    int dommage = personnage.getAttaque() - ennemi.getDefense();
                                    if (dommage < 0) {
                                        dommage = 0;
                                    }
                                    Design.titreAffichage_2(personnage.getNomPerso() + " attaque " + ennemi.getNomEnnemi() + " et lui inflige " + dommage + " points de dégâts");
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
                                } else if (choice == 3) {
                                    if (personnage.getObjets().isEmpty()) {
                                        Design.effacerConsole();
                                        Design.titreAffichage_2("L'inventaire de " + personnage.getNomPerso() + " est vide !");
                                        scanner.nextLine();
                                        Design.effacerConsole();
                                        break;
                                    } else {
                                        Design.titreAffichage_2("Quel objet voulez-vous utiliser ?");
                                        for (int i = 0; i < personnage.getObjets().size(); i++) {
                                            System.out.println((i + 1) + ". " + personnage.getObjets().get(i).getNomObjet());
                                        }
                                    }
                                    int objetChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    Design.effacerConsole();
                                    Objet objet = personnage.getObjets().get(objetChoice - 1);

                                    actionEffectuee = true;

                                    if (objet instanceof Potion) {
                                        personnage.utilisePotion((Potion) objet);

                                        scanner.nextLine();
                                        Design.effacerConsole();
                                    } else {
                                        Design.titreAffichage_2("Cet objet ne peut pas être utilisé en combat !");

                                        scanner.nextLine();
                                        Design.effacerConsole();
                                    }
                                } else if (choice == 4) {
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

            for (Personnage personnage : personnages) {
                personnage.ajouterEquipement(reward);
                System.out.println(personnage.getNomPerso() + " :");
                if (personnage.getArme() == null) {
                    Design.titreAffichage_4("Arme : aucune");
                } else {
                    Design.titreAffichage_4("Arme : " + personnage.getArme().getNomArme());
                }
                for (int i = 0; i < personnage.getEquipements().size(); i++) {
                    Equipement equipement = personnage.getEquipements().get(i);
                    if (equipement instanceof Arme) {
                        Design.titreAffichage_4("Arme " + (i + 1) + " : " + personnage.getEquipements().get(i).getNomEquipement());
                    } else {
                        Design.titreAffichage_4("Equipement " + (i + 1) + " : " + personnage.getEquipements().get(i).getNomEquipement());
                    }
                }

                for (int i = 0; i < personnage.getObjets().size(); i++) {
                    Objet objet = personnage.getObjets().get(i);
                    if (objet instanceof Potion) {
                        Design.titreAffichage_4("Potion " + (i + 1) + " : " + personnage.getObjets().get(i).getNomObjet());
                    } else {
                        Design.titreAffichage_4("Objet " + (i + 1) + " : " + personnage.getObjets().get(i).getNomObjet());
                    }
                }
            }*/
            //scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}