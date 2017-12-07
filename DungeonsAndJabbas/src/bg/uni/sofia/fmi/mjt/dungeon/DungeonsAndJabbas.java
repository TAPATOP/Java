package bg.uni.sofia.fmi.mjt.dungeon;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.*;
import bg.uni.sofia.fmi.mjt.dungeon.actor.*;

import java.util.Scanner;

public class DungeonsAndJabbas {
    public static void main(String args[]){
        Weapon excalibur = new Weapon("Excalibur", 20);
        ManaPotion mpo = new ManaPotion(100);
        HealthPotion hpo = new HealthPotion( 50);

        Treasure[] treasurearr = new Treasure[]{excalibur, mpo, hpo};

        Position pos = new Position(69, 69);
        Spell spe = new Spell("Diariq", 50, 50);
        Hero itsko = new Hero("Itsko", 120, 100, pos);
        excalibur.collect(itsko);
        spe.collect(itsko);

        Spell spell2 = new Spell("spell 2", 51, 55);
        Spell spell3 = new Spell("spell 3", 1, 10);

        System.out.println(itsko.getSpell().getName());
        spell2.collect(itsko);
        System.out.println(itsko.getSpell().getName());
        spell3.collect(itsko);
        System.out.println(itsko.getSpell().getName());

        Weapon botWep = new Weapon("Weaksauce",15);
        Spell botSpell = new Spell("Weaksauce", 20, 20);
        Enemy enem1 = new Enemy("Conscript", 20, 20, botWep, spe);
        Enemy enem2 = new Enemy("Lenin", 40, 40, botWep, botSpell);
        Enemy enem3 = new Enemy("Stalin", 100, 100, botWep, botSpell);

        Enemy[] enarr = new Enemy[]{enem1, enem2, enem3};

        char[][] map = new char[][]{
                {'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
                {'#', 'T', '#', '#', '.', '.', '#', '#', '#', '.'},
                {'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
                {'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
                {'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
        };
        GameEngine geng = new GameEngine(map, itsko, enarr, treasurearr);

        Scanner scanner = new Scanner(System.in);
        String message;

        while(true){
            try {
                message = geng.makeMove(scanner.nextInt());
                System.out.println(message);

                if(message == "You have successfully passed through the dungeon. Congrats!" ||
                        message == "Hero is dead! Game over!"){
                    break;
                }
            } catch(java.util.InputMismatchException mismatch){
                System.out.println("Unknown command entered.");
                scanner.nextLine();
            }
        }
    }
}
