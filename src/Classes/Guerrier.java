package Classes;

import Jeu.Mecanismes.Carte;
import Personnages.Personnage;

public class Guerrier extends Personnage {
    public Guerrier(String nomPerso, int maxHp, int maxMana, int attaque, int defense, Carte carte) {
        super(nomPerso, maxHp, maxMana, attaque, defense, carte);
    }
}
