package backend.gameLogic.visitors;

import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Trap;
import backend.Tiles.Player;
import backend.Tiles.Tile;
import backend.Tiles.Visitor;
import backend.Tiles.Wall;
import backend.gameLogic.LevelMap;

public class InteractWithPlayer implements Visitor {
    Player player;
    LevelMap map;
    public InteractWithPlayer(LevelMap map, Player player){
        this.map = map;
        this.player = player;
    }

    @Override
    public void visit_monster(Monster monster) {
        map.onCombat.accept(player.on_attack(monster));

    }

    @Override
    public void visit_trap(Trap trap) {
        map.onCombat.accept(player.on_attack(trap));
    }

    @Override
    public void visit_player(Player player) {}
    @Override
    public void visit_tile(Tile tile) {}
    @Override
    public void visit_wall(Wall wall) {}
}
