package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class Spell implements Treasure {
    // Constructor //

    public Spell(String name, int damage, int manaCost) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    // Treasure stuff //
    @Override
    public String collect(Hero hero) {
        hero.learn(this);
        return "Spell found! Damage points: " + damage + " Mana cost: " + manaCost;
    }

    // Specific Spell stuff //

    public String getName() {
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public int getManaCost(){
        return manaCost;
    }

    // Member variables //
    private String name;
    private int damage;
    private int manaCost;
}
