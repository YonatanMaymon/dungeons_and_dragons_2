package frontend;

import backend.Tiles.Player;
import backend.Tiles.Tile;
import data_records.BattleData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceManager {

    public static void sleep(){
        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    static void println(String string){System.out.println(string);}
    static void printSeparatingLine(){println("--------------------------------------------------------------------------");}

    public static int choose_character(List<Player> playerList){
        println("--CHARACTER SELECTION--");
        printSeparatingLine();
        int i = 0;
        for (Player player : playerList){
            String sb = i + "- "+player.get_name() + "- Health: " +
                    player.health.get_resource_pool() +
                    ", Attack: " +
                    player.get_attackPoints() +
                    ", Defence: " +
                    player.get_defencePoints();
            println(sb);
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter character number");
        if (!scanner.hasNextInt())
               throw new InputMismatchException("must enter a number!");
        int num = scanner.nextInt();
        if (num >i || num<0)
            throw new IndexOutOfBoundsException("player index must be between 0 and " + i);
        return num;
    }
    public static void print_map(Tile[][] map){
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                System.out.print(tile.get_tile());
            }
            println("");
        }
    }
    public static void print_combat_log(BattleData bd){
        printSeparatingLine();
        println(bd.attacker() + " is attacking "+bd.defender());
        println(bd.attacker() + " has rolled " + bd.attackRoll());
        println(bd.defender() + " has rolled "+ bd.defenceRoll());
        println(bd.defender() + " has taken " + bd.damage_taken()+" damage");
        println(bd.defender() + " remaining health: " + bd.remainingHealth());
        printSeparatingLine();
        sleep();
        if (bd.remainingHealth() ==0)
            kill_msg(bd.defender());
    }

    public static void kill_msg(String name){
        println(name + " has been slayed");
        printSeparatingLine();
        sleep();
    }
}
