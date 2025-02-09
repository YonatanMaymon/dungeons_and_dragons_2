package backend.gameLogic.resources;

public class Mana extends Resource {
    public Mana(int resource_pool) {
        super(resource_pool);
        set_resource_amount(resource_pool/4);
    }
    public void increase_mana_pool(int amount){
        add_to_resource_pool(amount);
        replenish_resource(get_resource_amount()+ get_resource_pool()/4);
    }
}
