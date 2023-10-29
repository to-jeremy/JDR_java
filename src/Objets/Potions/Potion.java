package Objets.Potions;

import Objets.Objet;

public class Potion extends Objet {
    private int guerison;
    private PotionType type;

    public Potion(String nomPotion, int price, int guerison, PotionType type) {
        super(nomPotion, price);
        this.guerison = guerison;
        this.type = type;
    }

    public int getGuerison() {
        return this.guerison;
    }

    public PotionType getType() {
        return type;
    }
}
