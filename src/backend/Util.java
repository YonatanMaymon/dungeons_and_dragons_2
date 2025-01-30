package backend;

import enums.DIRECTION;

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
}
