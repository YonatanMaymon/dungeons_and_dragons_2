package frontend;


import java.util.Scanner;

public class UI {

    public static void print_ui(){
        System.out.println("ui");
    }
    public static char get_player_input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter move");
        if (!scanner.hasNext()) return ' ';
        return scanner.next().toLowerCase().charAt(0);
    }
}
