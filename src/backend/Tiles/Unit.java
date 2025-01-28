package backend.Tiles;

import backend.gameLogic.resources.Health;
import backend.gameLogic.Position;
import data_records.BattleData;
import enums.DIRECTION;
import enums.UNIT_STATUS;

import java.util.Random;

public class Unit extends Tile{
    public UNIT_STATUS status = UNIT_STATUS.ALIVE;
    private final String _name;
    private final String _description;
    public Health health;
    private int _attackPoints;
    private int _defencePoints;
    public Unit(String name, String _description, Position position, char tile, int healthPool, int attackPoints, int defencePoints) {
        super(position, tile);
        this._name =name;
        this._description = _description;
        this.health = new Health(healthPool);
        this._attackPoints = attackPoints;
        this._defencePoints = defencePoints;
    }

    public void update(){}

    public Position get_next_position(DIRECTION direction){
        Position nextPosition = new Position();
        nextPosition.setPosition( _position);
        switch (direction) {
            case DIRECTION.LEFT->
                nextPosition.move(-1,0);
            case DIRECTION.RIGHT->
                nextPosition.move(1,0);
            case DIRECTION.DOWN->
                nextPosition.move(0,1);
            case DIRECTION.UP->
                nextPosition.move(0,-1);
        }
        return nextPosition;
    }


    void on_death(){
        status = UNIT_STATUS.DEAD;
        set_tile('.');
    }

    public BattleData on_attack(Unit attacker){
       Random random = new Random();
        int attackRoll = random.nextInt(attacker.get_attackPoints());
        int defenceRoll = random.nextInt(get_defencePoints());
        int damageTaken = Math.max(0,attackRoll-defenceRoll);
        health.take_damage(damageTaken);
        if (health.get_resource_amount() <= 0){on_death();}
        return new BattleData
                (attacker.get_name(),get_name(),attackRoll,defenceRoll, health.get_resource_amount());
    }

    public int get_attackPoints() {
        return _attackPoints;
    }

    public void add_attackPoints(int amount) {
        this._attackPoints += amount;
    }

    public int get_defencePoints() {
        return _defencePoints;
    }

    public void add_defencePoints(int amount) {
        this._defencePoints += amount;
    }

    public String get_name() {return _name;}
    public String get_description(){return _description;}
}
