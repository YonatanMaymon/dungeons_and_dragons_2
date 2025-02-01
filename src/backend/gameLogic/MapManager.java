package backend.gameLogic;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Tiles.Wall;

import java.util.ArrayList;

public class MapManager {
    public static final String LEVEL_FILE_DIR = "levels_dir\\levels_dir\\";
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();
    public Player player;
    private final UnitFactory unitFactory = new UnitFactory();

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls(){return walls;}

    public MapManager(Player player){
        this.player = player;
    }



    public void addGameObject(char object, Position position){
        switch (object){
            case '@'->
                player.set_position(position);
            case '#'->walls.add(new Wall(position));
            case '.'->{}
            default-> {
                Enemy enemy = unitFactory.get_enemy(object);
                enemy.set_position(position);
                enemies.add(enemy);
            }
        }
    }
}
