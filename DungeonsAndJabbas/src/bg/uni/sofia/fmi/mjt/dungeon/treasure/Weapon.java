package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class Weapon implements  Treasure{

    // Constructor //

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // Treasure stuff//

    @Override
    public String collect(Hero hero) {
        hero.equip(this);
        return "Weapon found! Damage points: " + damage;
    }

    // Weapon stuff //

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    // Member variables //

    private String name;
    private int damage;
}
