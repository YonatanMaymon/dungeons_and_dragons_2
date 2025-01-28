package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Unit;
import backend.Tiles.Visitor;
import backend.gameLogic.Position;
import interfaces.OnDeathCallBack;

public abstract class Enemy extends Unit {
    public Enemy(String name, String description,  Position position, char tile, int healthPool, int attackPoints, int defencePoints) {
        super(name,description, position, tile, healthPool, attackPoints, defencePoints);
    }
    public abstract Position get_next_position(Player player);
}
