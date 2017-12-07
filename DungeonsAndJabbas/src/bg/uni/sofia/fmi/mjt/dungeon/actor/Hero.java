package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Hero implements Actor {

    // Constructor //
    public Hero(String name, int health, int mana, Position position) {
        this.name = name;

        this.startingHealth = health;
        currentHealth = startingHealth;

        this.startingMana = mana;
        currentMana = startingMana;

        this.position = position;
        weapon = new Weapon("NoWeapon", 0);
        spell = new Spell("NoSpell", 0, 0);
    }

    // Actor stuff //
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return currentHealth;
    }

    @Override
    public int getMana() {
        return currentMana;
    }

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
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
        currentHealth -= damagePoints;
        if(0 > currentHealth) {
            currentHealth = 0;
        }
    }

     @Override
    public int attack() {
        if(weapon.getDamage() >= spell.getDamage() || currentMana < spell.getManaCost()){
            return weapon.getDamage();
        }
        else{
            currentMana -= spell.getManaCost();
            return spell.getDamage();
        }
    }

    // Specific Hero stuff //

    public void takeHealing(int healingPoints){
        if(isAlive()) {
            currentHealth += healingPoints;
            if(currentHealth > startingHealth) {
                currentHealth = startingHealth;
            }
        }
    }

    public void takeMana(int manaPoints){
        currentMana += manaPoints;
        if(currentMana > startingMana) {
            currentMana = startingMana;
        }
    }

    public void equip(Weapon weapon){
        if(this.weapon.getDamage() < weapon.getDamage()) {
            this.weapon = weapon;
        }
    }

    public void learn(Spell spell){
        if(this.spell.getDamage() <= spell.getDamage()) {
            this.spell = spell;
        }
    }

    public Position getPosition(){
        return position;
    }

    public void move(Position newPos){
        position = newPos;
    }

    // Member variables //

    private String name;
    private int startingHealth;
    private int currentHealth;
    private int startingMana;
    private int currentMana;
    private Position position;
    private Weapon weapon;
    private Spell spell;
}
