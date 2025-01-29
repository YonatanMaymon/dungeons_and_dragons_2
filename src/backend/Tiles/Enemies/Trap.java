package backend.Tiles.Enemies;

import backend.Tiles.Player;
import backend.Tiles.Visitor;
import backend.gameLogic.Position;

public class Trap extends Enemy{
    private int _visibilityTime;
    private int _invisibilityTime;
    private int _tickCount;
    private boolean visible;
    public Trap(char tile,String name, int healthPool, int attackPoints, int defencePoints, int exp_value, int visibilityTime,int invisibilityTime) {
        super( tile, name,  healthPool, attackPoints, defencePoints, exp_value);
        _visibilityTime = visibilityTime;
        _invisibilityTime = invisibilityTime;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_trap(this);
    }

    @Override
    public void update() {
        super.update();
        visible = _tickCount<_visibilityTime;
        if(_tickCount == _visibilityTime +_invisibilityTime)
            _tickCount =0;
        else _tickCount++;

    }

    @Override
    public Position get_next_position(Player player) {return null;}
}
