package backend.Tiles.PlayerTypes;

import backend.Tiles.Player;
import backend.gameLogic.resources.Cooldown;

public class Warrior extends Player {
    Cooldown _ability_cooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defencePoints, int ability_cooldown) {
        super(name, healthPool, attackPoints, defencePoints);
        this._ability_cooldown = new Cooldown(ability_cooldown);
    }

    @Override
    public boolean has_resources_for_ability() {
        return _ability_cooldown.get_cooldown() ==0;
    }

    @Override
    public void on_ability_cast(){ //Avenger’s Shield
        /*TO DO make it randomly hits one enemy withing range < 3 for an amount
        equals to 10% of the warrior’s max health*/
        this.health.heal(10* this.get_defencePoints());
        this._ability_cooldown.on_ability_use();
    }

    @Override
    public void update() {
        super.update();
        this._ability_cooldown.on_tick();
    }
    @Override
    public void on_lvl_up(){
        super.on_lvl_up();
        this._ability_cooldown.reset();
        this.health.increase_health_pool(5*this.get_lvl());
        this.add_attackPoints(2*this.get_lvl());
        this.add_defencePoints(this.get_lvl());
    }
}
