package backend.Tiles.PlayerTypes;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.gameLogic.resources.Cooldown;
import data_records.AbilityUseData;

import java.util.*;

public class Warrior extends Player {
    private int range = 3;
    Cooldown ability_cooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defencePoints, int ability_cooldown) {
        super(name, healthPool, attackPoints, defencePoints);
        this.ability_cooldown = new Cooldown(ability_cooldown);
    }

    @Override
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_warrior(this);
    }

    @Override
    public boolean has_resources_for_ability() {
        return ability_cooldown.get_cooldown() ==0;
    }

    @Override
    public void on_ability_cast(){
        int damage = health.get_resource_pool()/10;
        ArrayList<Enemy> hitList= getHitList(range);
        Map<String,Integer> damageMap= new HashMap<>();
        if (!hitList.isEmpty()) {
            Enemy enemyToAttack = get_random_enemy(hitList);
            damage = enemyToAttack.take_damage(damage);
            damageMap.put(enemyToAttack.get_name(), damage);

        }
        health.heal(10* this.get_defencePoints());
        ability_cooldown.on_ability_use();
        onAbilityUse.accept(new AbilityUseData("Avenger’s Shield",damageMap));
    }

    @Override
    public void update() {
        super.update();
        this.ability_cooldown.on_tick();
    }
    @Override
    public void on_lvl_up(){
        super.on_lvl_up();
        this.ability_cooldown.reset();
        this.health.increase_health_pool(5*this.get_lvl());
        this.add_attackPoints(2*this.get_lvl());
        this.add_defencePoints(this.get_lvl());
    }

    public int getRange() {
        return range;
    }
}
