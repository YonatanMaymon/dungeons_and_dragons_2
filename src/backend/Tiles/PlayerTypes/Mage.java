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
    private int spellPower;
    private final int manaCost;
    private final int hitsCount;
    private final int abilityRange;
    public Mage(String name, int healthPool, int attackPoints, int defencePoints, int mana_pool, int manaCost, int hitsCount, int spellPower, int abilityRange) {
        super(name, healthPool, attackPoints, defencePoints);
        this.mana = new Mana(mana_pool);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public Map<String, Integer> accept(PlayersVisitor visitor) {
        return visitor.visit_mage(this);
    }

    @Override
    public void on_lvl_up(){
        super.on_lvl_up();
        mana.increase_mana_pool(25* getLvl());
        spellPower += 10* getLvl();
    }

    @Override
    public void update() {
        super.update();
        mana.replenish_resource(getLvl());
    }

    @Override
    public boolean has_resources_for_ability() {
        return mana.get_resource_amount()>= manaCost;
    }

    @Override
    public void on_ability_cast() {
        int hits = 0;
        ArrayList<Enemy> hitList = get_hit_list(abilityRange);
        Map<String,Integer> damageMap = new HashMap<>();
        while(hits< hitsCount && !hitList.isEmpty()){
            Enemy enemyToAttack = get_random_enemy(hitList);

            int damage = spellPower - Util.roll(enemyToAttack.get_defence_points());
            damage = enemyToAttack.take_damage(damage);
            if (!enemyToAttack.isAlive) hitList.remove(enemyToAttack);
            damageMap.put(enemyToAttack.get_name(),damage);
            hits++;
        }
        onAbilityUse.accept(new AbilityUseData("Blizzard",damageMap));
        mana.supplement_resource(manaCost);
    }

    public int get_mana_cost() {
        return manaCost;
    }

    public int get_spell_power() {
        return spellPower;
    }

    public int get_hits_count() {
        return hitsCount;
    }

    public int get_ability_range() {
        return abilityRange;
    }
}
