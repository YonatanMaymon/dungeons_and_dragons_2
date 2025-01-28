package frontend;

import backend.Tiles.Tile;
import data_records.BattleData;

public class InterfaceManager {

    static void sleep(){
        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    static void println(String string){System.out.println(string);}
    static void printSeparatingLine(){println("--------------------------------------------------------------------------");}

    public static void print_map(char [][] map){
        for (char[] chars : map) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            println("");
        }
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
