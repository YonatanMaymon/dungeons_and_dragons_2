package backend.gameLogic;

import backend.Tiles.*;
import backend.Tiles.Enemies.Enemy;
import backend.Tiles.Enemies.Monster;
import backend.Tiles.Enemies.Trap;
import enums.UNIT_STATUS;

public class Interact implements Visitor {
    LevelMap map;
    Unit interactor;
    public Interact(LevelMap map, Unit interactor){
        this.map=map;
        this.interactor = interactor;
    }

    void visit_enemy(Enemy enemy){
        if (!enemy.isAlive )
            map.change_positions(interactor,enemy.get_position());
        else if (interactor instanceof Player) {
            map.onCombat.accept(enemy.on_attack(interactor));

            if (!enemy.isAlive)
                map.change_positions(interactor,enemy.get_position());
        }
    }

    @Override
    public void visit_monster(Monster monster) {
        visit_enemy(monster);
    }

    @Override
    public void visit_trap(Trap trap) {
        visit_enemy(trap);
    }

    @Override
    public void visit_player(Player player) {
        if (interactor instanceof Enemy)
            map.onCombat.accept(player.on_attack(interactor));
    }

    @Override
    public void visit_tile(Tile tile) {
        map.change_positions(interactor,tile.get_position());
    }

    @Override
    public void visit_wall(Wall wall) {

    }
}
