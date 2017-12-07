package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class ManaPotion implements Treasure{
    // Constructor //

    public ManaPotion(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    // Treasure stuff //

    @Override
    public String collect(Hero hero) {
        hero.takeMana(manaPoints);
        return "Mana potion found! " + manaPoints + " mana points added to your hero!";
    }

    // Specific ManaPotion stuff //

    public int heal(){
        return manaPoints;
    }

    // Member variables //
    private int manaPoints;
}
