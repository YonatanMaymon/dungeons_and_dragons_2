package backend.Tiles.PlayerTypes;

import backend.Tiles.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerStatExtractor implements PlayersVisitor{

    private static LinkedHashMap<String, Integer> get_basic_stats(Player player){
        LinkedHashMap <String,Integer> map = new LinkedHashMap<>();
        map.put("level", player.get_lvl());
        map.put("xp progress percentage", player.get_xp_progress_percentage());
        map.put("health",player.health.get_resource_amount());
        map.put("attack", player.get_attackPoints());
        map.put("defence", player.get_defencePoints());
        return map;
    }

    @Override
    public Map<String, Integer> visit_mage(Mage mage) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(mage);
        stats.put("mana", mage.mana.get_resource_amount());
        stats.put("cost", mage._mana_cost);
        stats.put("spell power", mage._spell_power);
        stats.put("hits count", mage._hits_count);
        stats.put("range", mage._ability_range);
        return stats;
    }

    @Override
    public Map<String, Integer> visit_warrior(Warrior warrior) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(warrior);
        stats.put("cooldown",warrior.ability_cooldown.get_cooldown());
        stats.put("range", warrior.getRange());
        return stats;
    }

    @Override
    public Map<String, Integer> visit_rogue(Rogue rogue) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(rogue);
        stats.put("energy", rogue.energy.get_resource_amount());
        stats.put("cost", rogue.getCost());
        stats.put("range", rogue.getRange());
        return stats;
    }

    @Override
    public Map<String, Integer> visit_hunter(Hunter hunter) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(hunter);
        stats.put("arrows", hunter.getArrowCount());
        stats.put("range",hunter.getRange());
        return stats;
    }
}
