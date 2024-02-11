package Classes;

import Jeu.Mecanismes.Carte;
import Personnages.Personnage;

public class Assassin extends Personnage {
    public Assassin(String nomPerso, int maxHp, int maxMana, int attaque, int defense, Carte carte) {
        super(nomPerso, maxHp, maxMana, attaque, defense, carte);
    }
}
