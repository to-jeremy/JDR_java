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

public class Main {

    public static void creerNouveauPersonnage(Scanner scanner, ArrayList<Personnage> personnages) {
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

            if (!input.isEmpty()) {
                // Si l'entrée n'est pas vide, on essaie de la convertir en entier
                try {
                    choixClasse = Integer.parseInt(input);
                    if (choixClasse < 1 || choixClasse > classes.length) {
                        System.out.println("Choix invalide. Veuillez choisir un numéro entre 1 et " + classes.length + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numéro valide.");
                }
            } else {
                Design.effacerConsole();
                Design.titreAffichage_2("Vous devez entrer un numéro de classe.");
                scanner.nextLine();
            }
        }

        String nomClasse = classes[choixClasse - 1];

        Personnage personnage = null;
        switch (choixClasse) {
            case 1:
                personnage = new Guerrier(nomPerso, 200, 10, 20, 12);
                break;
            case 2:
                personnage = new Mage(nomPerso, 200, 10, 20, 12);
                break;
            case 3:
                personnage = new Voleur(nomPerso, 200, 10, 20, 12);
                break;
            default:
                System.out.println("Classe invalide. Le personnage sera un guerrier par défaut.");
                personnage = new Guerrier(nomPerso, 200, 10, 20, 12);
                break;
        }
        personnages.add(personnage);

        Design.effacerConsole();
        Design.titreAffichage_1("Vous avez choisi la classe " + nomClasse + " pour le personnage " + nomPerso + ".");

        scanner.nextLine();
        Design.effacerConsole();
    }

    public static void choisirPersonnage(Scanner scanner, ArrayList<Personnage> personnages) {
        Design.titreAffichage_1("Choisir un personnage existant");

        if (personnages.isEmpty()) {
            System.out.println("Aucun personnage disponible.");
            return;
        }

        System.out.println("Liste des personnages :");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + ". " + personnages.get(i).getNomPerso());
        }

        System.out.print("Choisissez un personnage : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la fin de ligne après nextInt()

        if (choix >= 1 && choix <= personnages.size()) {
            Personnage personnageChoisi = personnages.get(choix - 1);
            System.out.println("Vous avez choisi " + personnageChoisi.getNomPerso() + " !");
            // Maintenant vous pouvez faire quelque chose avec ce personnage, comme jouer avec lui.
        } else {
            System.out.println("Choix invalide.");
        }
    }

    public static void supprimerPersonnage(Scanner scanner, ArrayList<Personnage> personnages) {
        Design.titreAffichage_1("Supprimer un personnage existant");

        if (personnages.isEmpty()) {
            System.out.println("Aucun personnage disponible.");
            return;
        }

        System.out.println("Liste des personnages :");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + ". " + personnages.get(i).getNomPerso());
        }

        System.out.print("Choisissez un personnage à supprimer : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la fin de ligne après nextInt()

        if (choix >= 1 && choix <= personnages.size()) {
            Personnage personnageSupprime = personnages.remove(choix - 1);
            System.out.println(personnageSupprime.getNomPerso() + " a été supprimé avec succès !");
        } else {
            System.out.println("Choix invalide.");
        }
    }

    public static void menuPrincipal(Scanner scanner, ArrayList<Personnage> personnages) {
        boolean continuer = true;
        while (continuer) {
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
                    // Créer un nouveau personnage
                    Design.effacerConsole();

                    creerNouveauPersonnage(scanner, personnages);
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
                    // Quitter le menu principal
                    Design.titreAffichage_2("Merci d'avoir joué au jeu de rôle du donjon !");

                    System.exit(0);
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
                    break;
            }
        }
    }


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            //Présentation du jeu
            Design.titreAffichage_1("Jeu de rôle || Jérémy TO");

            System.out.println("\nCliquer sur entrée pour continuer...");
            scanner.nextLine();
            Design.effacerConsole();

            // Créer le donjon
            Donjon donjon = DonjonDesTenebres.creerDonjon();
            Arme reward = (Arme) donjon.getRecompense();

            // Créer l'équipe de personnages
            ArrayList<Personnage> personnages = new ArrayList<>();
            menuPrincipal(scanner, personnages);
            /*Design.titreAffichage_5("Création de l'équipe de personnages !");

            for (int i = 1; i <= 3; i++) {
                Design.titreAffichage_2("Création du personnage " + i + " :");
                System.out.print("Nom : ");
                String nomPerso = scanner.nextLine();
                while (nomPerso.trim().isEmpty()) {
                    Design.effacerConsole();

                    Design.titreAffichage_2("Le nom ne peut pas être vide !");
                    scanner.nextLine();
                    Design.effacerConsole();

                    Design.titreAffichage_2("Création du personnage " + i + " :");
                    System.out.print("Nom : ");
                    nomPerso = scanner.nextLine();
                }
                Design.separateurAffichage(30);
                System.out.print("Classe (Guerrier, Mage, Voleur) : ");
                String nomClasse = scanner.nextLine();
                Design.separateurAffichage(30);
                Design.effacerConsole();
                Personnage personnage = null;
                switch (nomClasse.toLowerCase()) {
                    case "guerrier":
                        personnage = new Guerrier(nomPerso, 200, 10, 20, 12);
                        Design.titreAffichage_1("Vous avez choisi la classe " + nomClasse + " pour le personnage " + i + ".");

                        scanner.nextLine();
                        Design.effacerConsole();

                        break;
                    case "mage":
                        personnage = new Mage(nomPerso, 200, 10, 20, 12);
                        Design.titreAffichage_1("Vous avez choisi la classe " + nomClasse + " pour le personnage " + i + ".");

                        scanner.nextLine();
                        Design.effacerConsole();

                        break;
                    case "voleur":
                        personnage = new Voleur(nomPerso, 200, 10, 20, 12);
                        Design.titreAffichage_1("Vous avez choisi la classe " + nomClasse + " pour le personnage " + i + ".");

                        scanner.nextLine();
                        Design.effacerConsole();

                        break;
                    default:
                        Design.titreAffichage_1("Classe invalide, le personnage " + i + " sera un guerrier par défaut.");
                        personnage = new Guerrier(nomPerso, 200, 10, 20, 12);

                        scanner.nextLine();
                        Design.effacerConsole();

                        break;
                }
                personnages.add(personnage);
            }*/

            //Equipements des personnages
            Arme epee = new Arme("Épée basique", 15, 0);
            Potion potionMagique = new Potion("Soin", 0, 15, PotionType.SOIN);

            personnages.get(0).equipArme(epee);
            //personnages.get(0).ajouterEquipement(potionMagique);
            personnages.get(0).ajouterObjet(potionMagique);
            //personnages.get(0).ajouterEquipement(epee);

            personnages.get(1).equipArme(epee);
            personnages.get(1).ajouterObjet(potionMagique);

            //personnages.get(2).equipArme(epee);
            personnages.get(2).ajouterObjet(potionMagique);

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
            }
            //scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}