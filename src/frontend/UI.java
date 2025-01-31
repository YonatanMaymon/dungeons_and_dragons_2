package frontend;


import java.util.Map;
import java.util.Scanner;

public class UI {

    public static char get_player_input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter move");
        if (!scanner.hasNext()) return ' ';
        return scanner.next().toLowerCase().charAt(0);
    }
    public static void print_stats(Map<String,Integer> stats){
        for (String stat : stats.keySet()){
            System.out.print(stat + ": " + stats.get(stat) + ", ");
        }
        System.out.println();
    }
}
