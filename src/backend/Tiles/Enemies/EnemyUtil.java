package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Unit;
import backend.gameLogic.Position;
import enums.DIRECTION;

public class EnemyUtil {
    public static boolean is_player_in_range(Unit self, Player player, int visionRange){
        Position position = self.get_position();
        Position player_position = player.get_position();
        double distance_from_player = position.distance_from(player_position);
        return distance_from_player <= visionRange;
    }

    public static DIRECTION get_direction_based_on_player(Unit self, Player player){
        Position position = self.get_position();
        Position player_position = player.get_position();
        int dx = position.get_x() - player_position.get_x();
        int dy = position.get_y() - player_position.get_y();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                return DIRECTION.LEFT;
            else
                return DIRECTION.RIGHT;
        }
        else {
            if (dy > 0)
                return DIRECTION.UP;
            else
                return DIRECTION.DOWN;
        }
    }
}
