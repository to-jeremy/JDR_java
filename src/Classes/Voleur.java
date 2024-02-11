package Classes;

import Jeu.Mecanismes.Carte;
import Personnages.Personnage;

public class Voleur extends Personnage {
    public Voleur(String nomPerso, int maxHp, int maxMana, int attaque, int defense, Carte carte) {
        super(nomPerso, maxHp, maxMana, attaque, defense, carte);
    }
}