package backend.gameLogic;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Trap;
import backend.Tiles.Player;
import backend.Tiles.PlayerTypes.Mage;
import backend.Tiles.PlayerTypes.Rogue;
import backend.Tiles.PlayerTypes.Warrior;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UnitFactory {
    private final List<Supplier<Player>> playersList;
    private final Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selected;

    public Enemy get_enemy(char tile){return enemiesMap.get(tile).get();
    }
    public Player get_player(int num){return playersList.get(num).get();}

    public UnitFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                // Monsters
                () -> new Monster('s', "Lannister Soldier", 80, 8, 3, 3, 25),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 4, 50),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 5, 100),
                () -> new Monster('z', "Wright", 600, 30, 15, 3, 100),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 4, 250),
                () -> new Monster('g', "Giant-Wright", 1500, 100, 40, 5, 500),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 6, 1000),
                () -> new Monster('M', "The Mountain", 1000, 60, 25, 6, 500),
                () -> new Monster('C', "Queen Cersei", 100, 10, 10, 1, 1000),
                () -> new Monster('K', "Night's King", 5000, 300, 150, 8, 5000),

                // Traps
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().get_tile(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Warrior("The Hound", 400, 20, 6, 5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40, 2, 20),
                () -> new Rogue("Bronn", 250, 35, 3, 50)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }


}
