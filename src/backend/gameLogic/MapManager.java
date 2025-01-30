package backend.gameLogic;

import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Tiles.Unit;
import backend.Tiles.Wall;

import java.util.ArrayList;

public class MapManager {
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();
    public Player player;
    private UnitFactory unitFactory = new UnitFactory();

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
