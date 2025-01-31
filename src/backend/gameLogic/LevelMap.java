package backend.gameLogic;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Tiles.PlayerTypes.PlayerStatExtractor;
import backend.Tiles.Tile;
import backend.Tiles.Unit;
import backend.Tiles.Wall;
import backend.gameLogic.visitors.UnitMovement;
import data_records.AbilityUseData;
import data_records.BattleData;
import data_records.MapAndStats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class LevelMap extends MapManager {
    UnitMovement unitMovement = new UnitMovement(this);
    public Consumer<BattleData> onCombat;
    public Consumer<AbilityUseData> onAbilityUse;
    public Consumer<String> onDeath;
    public Consumer<MapAndStats> onMapAndStatsUpdate;
    private String file_dir = "levels_dir\\levels_dir\\level";
    int num_col=0;
    int num_row=0;
    public TileMap tileMap;
    private final PlayerStatExtractor playerStatExtractor = new PlayerStatExtractor();

    public LevelMap(int level, Player player, Consumer<BattleData> onCombat, Consumer<AbilityUseData> onAbilityUse, Consumer<String> onDeath, Consumer<MapAndStats> onMapAndStatsUpdate) throws IOException {
        super(player);
        file_dir+=level+ ".txt";
        BufferedReader reader = new BufferedReader(new FileReader(file_dir));
        String line;
        while ((line = reader.readLine()) != null){
            num_col = Math.max(num_col, line.length());
            num_row++;
        }
        tileMap = new TileMap(num_row,num_col);
        this.onCombat = onCombat;
        this.onAbilityUse = onAbilityUse;
        this.onDeath = onDeath;
        this.onMapAndStatsUpdate = onMapAndStatsUpdate;
    }

    public void loudMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file_dir));
        String line;
        int j = 0;
        while ((line = reader.readLine()) != null){
            for(int i =0 ; i < line.length(); i++){
                if(line.charAt(i) !='.')
                    addGameObject(line.charAt(i),new Position(i,j));
            }
            j++;
        }
        reader.close();
        player.setOnAbilityUse(onAbilityUse);
        player.setEnemies(getEnemies());
        for (Wall wall : getWalls()){tileMap.loud_tile_on_map(wall);}
        for (Enemy enemy : getEnemies()){tileMap.loud_tile_on_map(enemy);}
        tileMap.loud_tile_on_map(player);
    }

    public void update(){
        Map<String, Integer> stats = player.accept(playerStatExtractor);
        onMapAndStatsUpdate.accept(new MapAndStats(tileMap.to_string(),stats));
        player.update();
        player.accept(unitMovement);
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy: enemies){
            if (!enemy.isAlive){
                onDeath.accept(enemy.get_name());
                player.gain_xp(enemy.exp_value);
                enemiesToRemove.add(enemy);
            }
            enemy.update();
            enemy.accept(unitMovement);
        }
        for(Enemy enemy: enemiesToRemove){
            Position position = enemy.get_position();
            tileMap.map[position.get_y()][position.get_x()] = new Tile(position,'.');
            enemies.remove(enemy);
        }
    }

    public void change_positions(Unit unit, Position position){
        Position prev_position = unit.get_position();
        Tile pervTile = tileMap.map[position.get_y()][position.get_x()];
        tileMap.map[position.get_y()][position.get_x()] = unit;
        tileMap.map[prev_position.get_y()][prev_position.get_x()] = pervTile;
        unit.set_position(position);
        pervTile.set_position(prev_position);
    }
}
