package Ennemis;

public class Ennemi {
    private String nomEnnemi;
    private int maxHp;
    private int hp;
    private int attaque;
    private int defense;

    public Ennemi(String nomEnnemi, int maxHp, int attaque, int defense) {
        this.nomEnnemi = nomEnnemi;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attaque = attaque;
        this.defense = defense;
    }

    public String getNomEnnemi() {
        return nomEnnemi;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void subirDommage(int dommage) {
        hp -= dommage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean estMort() {
        return hp <= 0;
    }
}
