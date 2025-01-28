package backend.gameLogic.resources;

public class Health extends Resource {
    public Health(int healthPool){
        super(healthPool);
        this.fill_resource();
    }

    public void increase_health_pool(int amount){
        add_to_resource_pool(amount);
        fill_resource();
    }

    public void heal(int amount){
        replenish_resource(amount);
    }

    public void take_damage(int damage){decrement_resource(damage);}
}
