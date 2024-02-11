package Classes;

import Jeu.Mecanismes.Carte;
import Personnages.Personnage;

public class Archer extends Personnage {
    public Archer(String nomPerso, int maxHp, int maxMana, int attaque, int defense, Carte carte) {
        super(nomPerso, maxHp, maxMana, attaque, defense, carte);
    }
}
