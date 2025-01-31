import backend.Util;
import frontend.InterfaceManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Util.get_max_lvl());
        GameManager gameManager = new GameManager();
        gameManager.game_loop();
    }
}