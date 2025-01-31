package frontend;

import backend.Tiles.Player;
import backend.Tiles.Tile;
import data_records.AbilityUseData;
import data_records.BattleData;
import data_records.MapAndStats;

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
    static void println_dashed(String string){System.out.println("--"+string+"--");}
    static void printSeparatingLine(){println("--------------------------------------------------------------------------");}

    public static int choose_character(List<Player> playerList){
        println_dashed("CHARACTER SELECTION");
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

    public static void print_map_and_stats(MapAndStats mapAndStats){
        println(mapAndStats.map());
        UI.print_stats(mapAndStats.stats());
    }

    public static void print_ability_use_log(AbilityUseData data){
        printSeparatingLine();
        println("you casted " + data.name());
        for(String name: data.damageMap().keySet()){
            println_dashed(name+" got hit for " + data.damageMap().get(name));
        }
        sleep();
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
    }

    public static void kill_msg(String name){
        printSeparatingLine();
        println(name + " has been slayed");
        printSeparatingLine();
        sleep();
    }
}
