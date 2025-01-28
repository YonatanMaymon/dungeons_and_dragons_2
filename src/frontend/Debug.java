package frontend;

import backend.gameLogic.Position;

public class Debug {
    public static void print_position(Position position){
        System.out.println(position.get_x() + " " + position.get_y());
    }
}
