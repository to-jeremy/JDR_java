package Equipements;

public abstract class Equipement {
    private String nomEquipement;
    private int attaqueBonus;
    private int defenseBonus;

    public Equipement(String nomEquipement, int attaqueBonus, int defenseBonus) {
        this.nomEquipement = nomEquipement;
        this.attaqueBonus = attaqueBonus;
        this.defenseBonus = defenseBonus;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public int getAttaqueBonus() {
        return attaqueBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

}
