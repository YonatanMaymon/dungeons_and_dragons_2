package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Unit;
import backend.gameLogic.Position;

public abstract class Enemy extends Unit {
    public final int exp_value;
    public Enemy(char tile ,String name, int healthPool, int attackPoints, int defencePoints, int exp_value) {
        super(name, tile, healthPool, attackPoints, defencePoints);
        this.exp_value = exp_value;
    }
    public abstract Position get_next_position(Player player);
}
