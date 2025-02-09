package backend.gameLogic.resources;

public class Resource {
    private int resource_pool;
    private int resource_amount;
    public Resource(int resource_pool){
        this.resource_pool = resource_pool;
        this.resource_amount = 0;
    }
    public void replenish_resource(int amount){
        resource_amount = Math.min(resource_amount +amount, resource_pool);
    }
    public void decrement_resource(int amount){
        resource_amount = Math.max(resource_amount -amount,0);
    }
    public void supplement_resource(int amount){
        resource_amount = Math.max(0, resource_amount -amount);
    }
    public void fill_resource(){
        resource_amount = resource_pool;
    }
    public void empty_resource(){
        resource_amount = 0;
    }
    public void add_to_resource_pool(int amount){
        resource_pool +=amount;
    }

    public int get_resource_pool() {
        return resource_pool;
    }

    public int get_resource_amount() {
        return resource_amount;
    }

    public void set_resource_amount(int amount) {
        resource_amount = amount;
    }
}
