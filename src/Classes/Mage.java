package Classes;

import Jeu.Mecanismes.Carte;
import Personnages.Personnage;

public class Mage extends Personnage {
    public Mage(String nomPerso, int maxHp, int maxMana, int attaque, int defense, Carte carte) {
        super(nomPerso, maxHp, maxMana, attaque, defense, carte);
    }
}
