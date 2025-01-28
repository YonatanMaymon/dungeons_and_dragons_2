package backend.gameLogic;

import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Player;
import backend.Tiles.Unit;
import backend.Tiles.Wall;

import java.util.ArrayList;

public class MapManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();
    public Player player;

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
            default->
                enemies.add(new Monster(
                        "meaw",
                        "desc",
                        4,
                        position,
                        object,
                        20,
                        10,
                        5
                ));
        }
    }
}
