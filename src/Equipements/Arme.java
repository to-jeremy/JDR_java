package Equipements;

public class Arme extends Equipement {
    public Arme(String nomArme, int attaqueBonus, int defenseBonus) {
        super(nomArme, attaqueBonus, defenseBonus);
    }

    public String getNomArme() {
        return super.getNomEquipement();
    }
}
