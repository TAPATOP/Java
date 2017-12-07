package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Enemy implements Actor{

    // Constructor(s) //

    public Enemy(String name, int health, int mana, Weapon weapon, Spell spell) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.weapon = weapon;
        this.spell = spell;

        if(weapon == null){
            this.weapon = new Weapon("NoWep", 0);
        }
        if(spell == null){
            this.spell = new Spell("NoSpell", 0, 0);
        }
    }

    // Actor stuff //
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Spell getSpell() {
        return spell;
    }

    @Override
    public void takeDamage(int damagePoints) {
        health -= damagePoints;
        if(0 > health) {
            health = 0;
        }
    }

    // TODO
     @Override
     public int attack() {
         if(weapon.getDamage() >= spell.getDamage() || mana < spell.getManaCost()){
             return weapon.getDamage();
         }
         else{
             mana -= spell.getManaCost();
             return spell.getDamage();
         }
     }

    // Member variables //

    private String name;
    private int health;
    private int mana;
    private Weapon weapon;
    private Spell spell;
}
