package backend.gameLogic.visitors;

import backend.Tiles.*;
import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Trap;
import backend.gameLogic.LevelMap;
import backend.gameLogic.Position;
import frontend.UI;

public class UnitMovement implements Visitor {
    LevelMap map;

    public UnitMovement(LevelMap levelMap){
        this.map = levelMap;
    }

    boolean is_dead(Unit unit){return !unit.isAlive;}

    @Override
    public void visit_monster(Monster monster) {
        if(is_dead(monster)) return;
        Interact interact = new Interact(map, monster);
        Position new_pos = monster.get_next_position(map.player);
        map.tileMap.getTile(new_pos).accept(interact);
    }

    @Override
    public void visit_trap(Trap trap) {
        if(is_dead(trap)) return;
        if(trap.get_position().distance_from(map.player.get_position())<2){
            Interact interact = new Interact(map, trap);
            map.player.accept(interact);
        }
    }

    @Override
    public void visit_player(Player player) {
        Interact interact = new Interact(map, player);
        char input = UI.get_player_input();
        Position new_pos = player.get_next_position(input);
        map.tileMap.getTile(new_pos).accept(interact);
    }

    @Override
    public void visit_tile(Tile tile) {}

    @Override
    public void visit_wall(Wall wall) {}
}
