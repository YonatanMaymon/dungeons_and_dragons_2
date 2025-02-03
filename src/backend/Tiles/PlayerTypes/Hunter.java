package backend.Tiles.PlayerTypes;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Util;
import data_records.AbilityUseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hunter extends Player {
    private final int range;
    private int arrowCount = 10;
    private int tickCount = 0;
    public Hunter(String name, int healthPool, int attackPoints, int defencePoints, int range) {
        super(name, healthPool, attackPoints, defencePoints);
        this.range = range;
    }

    @Override
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_hunter(this);
    }

    @Override
    public boolean has_resources_for_ability() {return arrowCount>0;}

    @Override
    public void on_ability_cast() {
        ArrayList<Enemy> hitList = getHitList(range);
        if(!hitList.isEmpty()) {
            arrowCount -= 1;
            Enemy enemyToAttack = hitList.getFirst();
            for(Enemy enemy: hitList){
                if (enemy.get_position().distance_from(get_position())
                        < enemyToAttack.get_position().distance_from(get_position()))
                    enemyToAttack = enemy;
            }
            int damage = get_attackPoints() - Util.roll(enemyToAttack.get_defencePoints());
            damage = enemyToAttack.take_damage(damage);
            Map<String,Integer> damageMap = new HashMap<>();
            damageMap.put(enemyToAttack.get_name(),damage);
            onAbilityUse.accept(new AbilityUseData("fan of knives",damageMap));
        }
    }

    @Override
    public void on_lvl_up() {
        super.on_lvl_up();
        arrowCount += get_lvl()*10;
        add_attackPoints(2*get_lvl());
        add_defencePoints(get_lvl());
    }

    @Override
    public void update() {
        super.update();
        if(tickCount == 10){
            arrowCount += get_lvl();
            tickCount = 0;
        }
        else tickCount++;
    }

    public int getRange() {
        return range;
    }

    public int getArrowCount() {
        return arrowCount;
    }

}
