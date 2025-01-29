package backend.Tiles.PlayerTypes;

import backend.Tiles.Player;
import backend.gameLogic.resources.Mana;

public class Mage extends Player {
    Mana mana;
    int _mana_cost;
    int _spell_power;
    int _hits_count;
    int _ability_range;
    public Mage(String name, int healthPool, int attackPoints, int defencePoints, int mana_pool, int _mana_cost, int _hits_count, int _spell_power, int _ability_range) {
        super(name, healthPool, attackPoints, defencePoints);
        this.mana = new Mana(mana_pool);
        this._mana_cost = _mana_cost;
        this._spell_power = _spell_power;
        this._hits_count=_hits_count;
        this._ability_range = _ability_range;
    }

    @Override
    public void on_lvl_up(){
        super.on_lvl_up();
        mana.increase_mana_pool(25*get_lvl());
        _spell_power += 10*get_lvl();
    }

    @Override
    public void update() {
        super.update();
        mana.replenish_resource(get_lvl());
    }

    @Override
    public boolean has_resources_for_ability() {
        return mana.get_resource_amount()>=_mana_cost;
    }

    @Override
    public void on_ability_cast() {
        mana.supplement_resource(_mana_cost);
        /*(hits < hits count) ∧ (∃ living enemy s.t. range(enemy, player) < ability range) do
        - Select random enemy within ability range.
        - Deal damage (reduce health value) to the chosen enemy for an amount equal to spell power
        (each enemy may try to defend itself).
        - hits ← hits + 1
        */

    }
}
