package backend.gameLogic.resources;

public class Cooldown extends Resource {

    public Cooldown(int resource_pool) {
        super(resource_pool);
        fill_resource();
    }
    public int get_cooldown(){
        return get_resource_pool()- get_resource_amount();
    }
    public void on_tick(){
        replenish_resource(1);
    }
    public void reset(){
        fill_resource();
    }
    public void on_ability_use(){
        empty_resource();
    }
}
