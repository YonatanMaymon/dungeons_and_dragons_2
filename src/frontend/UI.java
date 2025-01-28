package frontend;

import event_handler.EventManager;

import java.util.Scanner;

public class UI {
    EventManager eventManager;
    public UI(){
        eventManager = EventManager.getInstance();
    }

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
