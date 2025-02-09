package backend.Tiles;

import backend.Tiles.Enemies.Enemy;
import backend.Tiles.PlayerTypes.PlayersVisitor;
import backend.Util;
import backend.gameLogic.Position;
import data_records.AbilityUseData;
import enums.DIRECTION;
import exeptions.InsufficientResourcesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class Player extends Unit implements HeroicUnit{
    private int xp;
    private int lvl;
    protected Consumer<AbilityUseData> onAbilityUse = (AbilityUseData)->{};
    protected ArrayList<Enemy> enemies = new ArrayList<>();

    public Player(String name, int healthPool, int attackPoints, int defencePoints) {
        super(name, '@', healthPool, attackPoints, defencePoints);
        this.lvl = 1;
        this.xp = 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_player(this);
    }

    public Map<String,Integer> accept(PlayersVisitor visitor){return null;}

    @Override
    void on_death() {
        super.on_death();
        set_tile('X');
    }

    protected ArrayList<Enemy> get_hit_list(int range){
        ArrayList<Enemy> hitList= new ArrayList<>();
        for (Enemy enemy : enemies){
            if(get_position().distance_from(enemy.get_position())<=range && enemy.isAlive){
                hitList.add(enemy);
            }
        }
        return hitList;
    }

    protected Enemy get_random_enemy(ArrayList<Enemy> hitList){
        Random random = new Random();
        return hitList.get(random.nextInt(0,hitList.size()));
    }

    public void set_on_ability_use(Consumer<AbilityUseData> onAbilityUse) {
        this.onAbilityUse = onAbilityUse;
    }

    public void set_enemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    public void on_boss_ability_cast(int damage){
        damage = take_damage(damage);
        HashMap <String,Integer> damageMap = new HashMap<>();
        damageMap.put(get_name(),damage);
        onAbilityUse.accept(new AbilityUseData("Boss Ability", damageMap));
    }

    public Position get_next_position(char input){
        DIRECTION direction = Util.input_interpreter(input);
        if (direction == null) return null;
        else if(direction == DIRECTION.CAST_ABILITY) cast_ability();
        return get_next_position(direction);
    }
    public void on_ability_cast(){}
    public boolean has_resources_for_ability(){
        return false;
    }
    public void cast_ability(){
        if (has_resources_for_ability()){
            on_ability_cast();
            return;
        }
        throw new InsufficientResourcesException();
    }

    int get_xp_threshold(){return 50 * this.lvl;}

    public void gain_xp(int amount){
        this.xp += amount;
        int xp_threshold = get_xp_threshold();
        if (this.xp >=xp_threshold){
            this.xp -= xp_threshold;
            lvl_up();
        }
    }
    void lvl_up(){
        this.lvl++;
        on_lvl_up();
    }
    public void on_lvl_up(){
        this.health.increase_health_pool(10*this.lvl);
        this.add_attack_points(4*this.lvl);
        this.add_defence_points( this.lvl);
    }

    public int getLvl() {
        return lvl;
    }

    public int get_xp_progress_percentage() {return 100 - (get_xp_threshold() - xp) *100 /get_xp_threshold();}
}
