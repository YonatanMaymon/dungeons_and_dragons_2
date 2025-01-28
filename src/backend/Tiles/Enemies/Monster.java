package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Visitor;
import backend.Util;
import backend.gameLogic.Position;
import enums.DIRECTION;
import interfaces.OnDeathCallBack;

public class Monster extends Enemy {
    int _vision_range;
    public Monster(String name, String description,  int _vision_range, Position position, char tile, int healthPool, int attackPoints, int defencePoints) {
        super(name, description,  position, tile, healthPool, attackPoints, defencePoints);
        this._vision_range = _vision_range;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_monster(this);
    }

    public Position get_next_position(Player player) {
        return get_next_position(get_direction(player));
    }
    DIRECTION get_direction(Player player){
        Position position = get_position();
        Position player_position = player.get_position();
        double distance_from_player = position.distance_from(player_position);
        if (distance_from_player <= _vision_range) {
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
        else return Util.get_random_direction();
    }
}
