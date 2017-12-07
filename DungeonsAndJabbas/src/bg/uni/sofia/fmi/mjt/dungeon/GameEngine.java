package bg.uni.sofia.fmi.mjt.dungeon;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Treasure;

public class GameEngine {
    // Constructor //
    public GameEngine(char[][] map, Hero hero, Enemy[] enemies, Treasure[] treasures) {
        Map = map;
        this.hero = hero;
        this.enemies = enemies;
        this.treasures = treasures;

        for(int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                if(Map[i][j] == 'S'){
                    hero.move(new Position(i, j));
                    break;
                }
            }
        }
    }

    // Methods //
    public char[][] getMap(){
        return Map;
    }

    public String makeMove(int command){
        Position futurePos;
        switch(command){
            case 0:
                futurePos = new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
                break;
            case 1:
                futurePos = new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
                break;
            case 2:
                futurePos = new Position(hero.getPosition().getX() + 1, hero.getPosition().getY());
                break;
            case 3:
                futurePos = new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
                break;
            default:
                return "Unknown command entered.";
        }
        try {
            switch (Map[futurePos.getY()][futurePos.getX()]) {
                case '.':
                    moveHero(futurePos);
                    return "You moved successfully to the next position.";
                case '#':
                    return "Wrong move. There is an obstacle and you cannot bypass it.";
                case 'T':
                    moveHero(futurePos);
                    try {
                        return treasures[treasID++].collect(hero);
                    } catch(ArrayIndexOutOfBoundsException oob){
                        return "Sorry, no more treasures left";
                    }
                case 'E':
                    // Get enemy //
                    Enemy enemy;
                    try {
                        enemy = enemies[enemID++];
                    } catch(ArrayIndexOutOfBoundsException oob){
                        moveHero(futurePos);
                        return "All enemies are already dead";
                    }
                    // Combat phase //
                    return simulateBattle(enemy, futurePos);
                case 'G':
                    return "You have successfully passed through the dungeon. Congrats!";
                default:
                    return "Square type not supported";
            }
        } catch(ArrayIndexOutOfBoundsException oob){
            return "You can't move outside of the map";
        }
    }

    public void printMap(){
        for(int i = 0; i < Map.length; i++){
            for(int j = 0; j < Map[0].length; j++){
                System.out.print(Map[i][j]);
            }
            System.out.println();
        }
    }

    private void moveHero(Position futurePos){
        Map[hero.getPosition().getY()][hero.getPosition().getX()] = '.';
        hero.move(futurePos);
        Map[hero.getPosition().getY()][hero.getPosition().getX()] = 'H';
    }

    private String simulateBattle(Enemy enemy, Position futurePos){
        while(true) {
            enemy.takeDamage(hero.attack());
            if (!enemy.isAlive()) {
                moveHero(futurePos);
                return "Enemy died.";
            }
            hero.takeDamage(enemy.attack());
            if (!hero.isAlive()) {
                return "Hero is dead! Game over!";
            }
        }
    }
    // Member variables //
    private char[][] Map;
    private Hero hero;
    private Enemy[] enemies;
    private int enemID = 0;
    private Treasure[] treasures;
    private int treasID = 0;
}
