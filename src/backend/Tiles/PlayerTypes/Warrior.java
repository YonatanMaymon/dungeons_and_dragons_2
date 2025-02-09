package backend.Tiles.PlayerTypes;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.gameLogic.resources.Cooldown;
import data_records.AbilityUseData;

import java.util.*;

public class Warrior extends Player {
    private final int RANGE = 3;
    Cooldown abilityCooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defencePoints, int abilityCooldown) {
        super(name, healthPool, attackPoints, defencePoints);
        this.abilityCooldown = new Cooldown(abilityCooldown);
    }

    @Override
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_warrior(this);
    }

    @Override
    public boolean has_resources_for_ability() {
        return abilityCooldown.get_cooldown() ==0;
    }

    @Override
    public void on_ability_cast(){
        int damage = health.get_resource_pool()/10;
        ArrayList<Enemy> hitList= get_hit_list(RANGE);
        Map<String,Integer> damageMap= new HashMap<>();
        if (!hitList.isEmpty()) {
            Enemy enemyToAttack = get_random_enemy(hitList);
            damage = enemyToAttack.take_damage(damage);
            damageMap.put(enemyToAttack.get_name(), damage);

        }
        health.heal(10* this.get_defence_points());
        abilityCooldown.on_ability_use();
        onAbilityUse.accept(new AbilityUseData("Avenger’s Shield",damageMap));
    }

    @Override
    public void update() {
        super.update();
        this.abilityCooldown.on_tick();
    }
    @Override
    public void on_lvl_up(){
        super.on_lvl_up();
        this.abilityCooldown.reset();
        this.health.increase_health_pool(5*this.getLvl());
        this.add_attack_points(2*this.getLvl());
        this.add_defence_points(this.getLvl());
    }

    public int get_range() {
        return RANGE;
    }
}
