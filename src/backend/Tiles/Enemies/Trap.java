package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Visitor;
import backend.gameLogic.Position;

public class Trap extends Enemy{
    private final int visibilityTime;
    private final int invisibilityTime;
    private final char originTile;
    private int tickCount;
    public Trap(char tile,String name, int healthPool, int attackPoints, int defencePoints, int exp_value, int visibilityTime,int invisibilityTime) {
        super( tile, name,  healthPool, attackPoints, defencePoints, exp_value);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        originTile = tile;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_trap(this);
    }

    @Override
    public void update() {
        super.update();
        boolean visible = tickCount < visibilityTime;
        if(visible) set_tile(originTile);
        else set_tile('.');
        if(tickCount == visibilityTime + invisibilityTime)
            tickCount =0;
        else tickCount++;

    }

    @Override
    public Position get_next_position(Player player) {return null;}
}
