package backend.Tiles.PlayerTypes;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Util;
import backend.gameLogic.resources.Energy;
import data_records.AbilityUseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rogue extends Player {
    private final int cost;
    private final int RANGE = 2;
    Energy energy;
    public Rogue(String name,  int healthPool, int attackPoints, int defencePoints, int cost) {
        super(name,  healthPool, attackPoints, defencePoints);
        this.cost = cost;
        energy = new Energy();
    }

    @Override
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_rogue(this);
    }

    @Override
    public void on_lvl_up() {
        super.on_lvl_up();
        energy.fill_resource();
        add_attack_points(3* getLvl());
    }

    @Override
    public void update() {
        super.update();
        energy.replenish_resource(10);
    }

    @Override
    public boolean has_resources_for_ability() {
        return energy.get_resource_amount() - cost >=0;
    }

    @Override
    public void on_ability_cast() {
        ArrayList<Enemy> hitList = get_hit_list(RANGE);
        Map<String,Integer> damageMap = new HashMap<>();
        if (!hitList.isEmpty())
            for(Enemy enemy :hitList){
                int damage = get_attack_points() - Util.roll(enemy.get_defence_points());
                damage = enemy.take_damage(damage);
                damageMap.put(enemy.get_name(),damage);
            }
        onAbilityUse.accept(new AbilityUseData("fan of knives",damageMap));
        energy.supplement_resource(cost);
    }

    public int get_cost() {
        return cost;
    }

    public int get_range() {
        return RANGE;
    }
}
