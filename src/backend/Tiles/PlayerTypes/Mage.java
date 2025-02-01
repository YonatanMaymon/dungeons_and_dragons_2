package backend.Tiles.PlayerTypes;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Util;
import backend.gameLogic.resources.Mana;
import data_records.AbilityUseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_mage(this);
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
        int hits = 0;
        ArrayList<Enemy> hitList = getHitList(_ability_range);
        Map<String,Integer> damageMap = new HashMap<>();
        while(hits<_hits_count && !hitList.isEmpty()){
            Enemy enemyToAttack = get_random_enemy(hitList);

            int damage = _spell_power - Util.roll(enemyToAttack.get_defencePoints());
            enemyToAttack.take_damage(damage);
            if (!enemyToAttack.isAlive) hitList.remove(enemyToAttack);
            damageMap.put(enemyToAttack.get_name(),damage);
            hits++;
        }
        onAbilityUse.accept(new AbilityUseData("Blizzard",damageMap));
        mana.supplement_resource(_mana_cost);
    }
}
