package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class HealthPotion implements Treasure {
    // Constructor //

    public HealthPotion(int healingPoints) {
        this.healingPoints = healingPoints;
    }

    // Treasure stuff //

    @Override
    public String collect(Hero hero) {
        hero.takeHealing(healingPoints);
        return "Health potion found! " + healingPoints + " health points added to your hero!";
    }

    // Specific HealthPotion stuff //

    public int heal(){
        return healingPoints;
    }

    // Member variables //
    private int healingPoints;
}
