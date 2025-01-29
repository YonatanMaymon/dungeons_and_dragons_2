package backend.Tiles.PlayerTypes;

import backend.Tiles.Player;
import backend.gameLogic.resources.Energy;

public class Rogue extends Player {
    int _cost;
    Energy energy;
    public Rogue(String name,  int healthPool, int attackPoints, int defencePoints, int _cost) {
        super(name,  healthPool, attackPoints, defencePoints);
        this._cost = _cost;
        energy = new Energy();
    }

    @Override
    public void on_lvl_up() {
        super.on_lvl_up();
        energy.fill_resource();
        add_attackPoints(3*get_lvl());
    }

    @Override
    public void update() {
        super.update();
        energy.replenish_resource(10);
    }

    @Override
    public void on_ability_cast() {
        energy.supplement_resource(_cost);
        /*For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        attack points (each enemy will try to defend itself)*/
    }
}
