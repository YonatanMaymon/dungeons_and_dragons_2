package backend.Tiles;

import backend.Util;
import backend.gameLogic.Position;
import enums.DIRECTION;

public class Player extends Unit{
    int _xp;
    int _lvl;
    public Player(String name, int healthPool, int attackPoints, int defencePoints) {
        super(name, '@', healthPool, attackPoints, defencePoints);
        this._lvl = 1;
        this._xp = 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_player(this);
    }

    @Override
    void on_death() {
        super.on_death();
        set_tile('X');
    }

    public boolean has_resources_for_ability(){
        return false;
    }

    public void on_ability_cast(){}

    public Position get_next_position(char input){
        DIRECTION direction = Util.input_interpreter(input);
        if (direction == null) return null;
        return get_next_position(direction);
    }

    public void cast_ability(){
        if (has_resources_for_ability()){
            on_ability_cast();
            return;
        }
        System.out.println("you dont have the resources to cast this ability");
    }

    public void gain_xp(int amount){
        this._xp += amount;
        int xp_threshold = 50 * this._lvl;
        if (this._xp >=xp_threshold){
            this._xp -= xp_threshold;
            lvl_up();
        }
    }
    void lvl_up(){
        this._lvl ++;
        on_lvl_up();
    }
    public void on_lvl_up(){
        this.health.increase_health_pool(10*this._lvl);
        this.add_attackPoints(4*this._lvl);
        this.add_defencePoints( this._lvl);
    }

    public int get_lvl() {
        return _lvl;
    }

}
