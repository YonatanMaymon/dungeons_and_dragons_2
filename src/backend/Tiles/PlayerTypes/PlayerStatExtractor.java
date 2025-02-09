package backend.Tiles.PlayerTypes;

import backend.Tiles.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerStatExtractor implements PlayersVisitor{

    private static LinkedHashMap<String, Integer> get_basic_stats(Player player){
        LinkedHashMap <String,Integer> map = new LinkedHashMap<>();
        map.put("level", player.getLvl());
        map.put("xp progress percentage", player.get_xp_progress_percentage());
        map.put("health",player.health.get_resource_amount());
        map.put("attack", player.get_attack_points());
        map.put("defence", player.get_defence_points());
        return map;
    }

    @Override
    public Map<String, Integer> visit_mage(Mage mage) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(mage);
        stats.put("mana", mage.mana.get_resource_amount());
        stats.put("cost", mage.get_mana_cost());
        stats.put("spell power", mage.get_spell_power());
        stats.put("hits count", mage.get_hits_count());
        stats.put("range", mage.get_ability_range());
        return stats;
    }

    @Override
    public Map<String, Integer> visit_warrior(Warrior warrior) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(warrior);
        stats.put("cooldown",warrior.abilityCooldown.get_cooldown());
        stats.put("range", warrior.get_range());
        return stats;
    }

    @Override
    public Map<String, Integer> visit_rogue(Rogue rogue) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(rogue);
        stats.put("energy", rogue.energy.get_resource_amount());
        stats.put("cost", rogue.get_cost());
        stats.put("range", rogue.get_range());
        return stats;
    }

    @Override
    public Map<String, Integer> visit_hunter(Hunter hunter) {
        LinkedHashMap<String,Integer> stats = get_basic_stats(hunter);
        stats.put("arrows", hunter.get_arrow_count());
        stats.put("range",hunter.get_range());
        return stats;
    }
}
