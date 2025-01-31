package backend;

import backend.gameLogic.MapManager;
import enums.DIRECTION;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Util {
    public static DIRECTION input_interpreter(char input){
        return switch (input) {
            case 'a' -> DIRECTION.LEFT;
            case 'd' -> DIRECTION.RIGHT;
            case 's' -> DIRECTION.DOWN;
            case 'w' -> DIRECTION.UP;
            case 'q' -> DIRECTION.STAY;
            case 'e' -> DIRECTION.CAST_ABILITY;
            default -> null;
        };
    }
    public static DIRECTION get_random_direction(){
        Random random = new Random();
        int randomNum = random.nextInt(0,4);
        return DIRECTION.class.getEnumConstants()[randomNum];
    }
    public static int roll(int rollStat){
        Random random = new Random();
        return random.nextInt(rollStat);
    }
    public static int get_max_lvl(){
        File dir = new File(MapManager.LEVEL_FILE_DIR);
        File [] listOfFiles = dir.listFiles();
        int max_lvl = 0;
        for (File file : listOfFiles){
            if(file.isFile()&& file.getName().contains("level")) {
                max_lvl++;
            }
        }
        return max_lvl;
    }
}
