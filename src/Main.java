import frontend.InterfaceManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameManager gameManager = new GameManager();
        gameManager.game_loop();
    }
}