import Design.Design;
import Equipements.Arme;
import Jeu.Mecanismes.Carte;
import Objets.Potions.Potion;
import Objets.Potions.PotionType;
import Personnages.Personnage;

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
            //scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}