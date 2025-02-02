package backend.Tiles.PlayerTypes;

import java.util.Map;

public interface PlayersVisitor {
    Map<String,Integer> visit_mage(Mage mage);
    Map<String,Integer> visit_warrior(Warrior warrior);
    Map<String,Integer> visit_rogue(Rogue rogue);
    Map<String,Integer> visit_hunter(Hunter hunter);
}
