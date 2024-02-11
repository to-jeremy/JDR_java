package Personnages;

import Design.Design;
import Equipements.Arme;
import Equipements.Equipement;
import Inventaire.Inventaire;
import Objets.Objet;
import Objets.Potions.Potion;
import Objets.Potions.PotionType;

import java.util.ArrayList;

public class Personnage {
    private String nomPerso;
    private int maxHp;
    private int hp;
    private int attaque;
    private int defense;
    private int maxMana;
    private int mana;
    private Arme arme;
    private Inventaire inventaire;

    public Personnage(String nomPerso, int maxHp, int maxMana, int attaque, int defense) {
        this.nomPerso = nomPerso;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.attaque = attaque;
        this.defense = defense;
        this.arme = null;
        this.inventaire = new Inventaire();
    }

    public String getNomPerso() {
        return nomPerso;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    private int calculerBonusAttaque() {
        int attaqueBonus = 0;
        if (arme != null) {
            attaqueBonus += arme.getAttaqueBonus();
        }
        for (Equipement equipement : inventaire.getEquipements()) {
            if (equipement instanceof Arme){
                attaqueBonus += ((Arme) equipement).getAttaqueBonus();
            }
        }
        return attaqueBonus;
    }

    public int getAttaque() {
        int attaqueBonus = calculerBonusAttaque();
        return attaque + attaqueBonus;
    }

    public int getDefense() {
        int defenseBonus = 0;
        if (arme != null) {
            defenseBonus += arme.getDefenseBonus();
        }
        for (Equipement equipement : inventaire.getEquipements()) {
            if (equipement instanceof Arme) {
                defenseBonus += ((Arme) equipement).getDefenseBonus();
            }
        }
        return defense + defenseBonus;
    }

    public Arme getArme() {
        return arme;
    }

    public void equipArme(Arme arme) {
        this.arme = arme;
    }

    public void retireArme() {
        this.arme = null;
    }

    public ArrayList<Equipement> getEquipements() {
        return inventaire.getEquipements();
    }

    public ArrayList<Objet> getObjets() {
        return inventaire.getObjets();
    }

    public void ajouterEquipement(Equipement equipement) {
        inventaire.ajouterEquipement(equipement);
    }

    public void ajouterObjet(Objet objet) {
        inventaire.ajouterObjet(objet);
    }

    public void subirDommage(int dommage) {
        hp -= dommage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void perdreMana(int manaPerdu) {
        int manaActuelle = getMana();
        int nouveauMana = manaActuelle - manaPerdu;
        if (nouveauMana < 0) {
            nouveauMana = 0;
        }
        setMana(nouveauMana);
        Design.titreAffichage_4(getNomPerso() + " a perdu " + manaPerdu + " points de mana !");
    }

    public boolean estMort() {
        return hp <= 0;
    }

    public void utilisePotion(Potion potion) {
        if (potion.getType() == PotionType.SOIN) {
            int amount = potion.getGuerison();
            int soinActuel = getHp();
            int maxSoin = getMaxHp();
            int nouveauSoin = soinActuel + amount;
            if (nouveauSoin > maxSoin) {
                nouveauSoin = maxSoin;
            }

            if (nouveauSoin == soinActuel) {
                Design.titreAffichage_2(getNomPerso() + " est déjà à sa vie maximale !");
                return;
            } else {
                setHp(nouveauSoin);
                Design.titreAffichage_2(getNomPerso() + " a utilisé une potion de vie et a récupéré " + amount + " points de vie !");
            }

        } else if (potion.getType() == PotionType.MANA) {
            int amount = potion.getGuerison();
            int manaActuelle = getMana();
            int maxMana = getMaxMana();
            int nouveauMana = manaActuelle + amount;
            if (nouveauMana > maxMana) {
                nouveauMana = maxMana;
            }

            if (nouveauMana == manaActuelle) {
                Design.titreAffichage_2(getNomPerso() + " a déjà la mana maximale !");
                return;
            } else {
                setMana(nouveauMana);
                Design.titreAffichage_2(getNomPerso() + " a utilisé une potion de mana et a récupéré " + amount + " points de mana !");
            }
        }
        getObjets().remove(potion);
    }

    public void afficherInfosPersonnage() {
        Design.titreAffichage_2("Informations sur " + getNomPerso());
        System.out.println("Classe : " + getClass().getSimpleName());
        System.out.println("HP : " + getHp() + "/" + getMaxHp());
        System.out.println("Nombre de potions restantes : " + getObjets().size());
        System.out.println("Équipements :");

        if (getArme() != null) {
            System.out.println("- Arme équipée : " + getArme().getNomArme());
        } else {
            System.out.println("- Aucune arme équipée");
        }

        for (Equipement equipement : getEquipements()) {
            if (equipement instanceof Arme) {
                System.out.println("- Arme dans l'inventaire : " + equipement.getNomEquipement());
            } else {
                System.out.println("- Autre équipement : " + equipement.getNomEquipement());
            }
        }
        Design.separateurAffichage(30);
    }
}
