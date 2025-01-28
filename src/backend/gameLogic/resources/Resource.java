package backend.gameLogic.resources;

public class Resource {
    int _resource_pool;
    int _resource_amount;
    public Resource(int resource_pool){
        this._resource_pool = resource_pool;
        this._resource_amount = 0;
    }
    public void replenish_resource(int amount){
        this._resource_amount = Math.min(this._resource_amount+amount, this._resource_pool);
    }
    public void decrement_resource(int amount){
        _resource_amount = Math.max(_resource_amount-amount,0);
    }
    public void supplement_resource(int amount){
        _resource_amount = Math.max(0,_resource_amount-amount);
    }
    public void fill_resource(){
        this._resource_amount = this._resource_pool;
    }
    public void empty_resource(){
        this._resource_amount = 0;
    }
    public void add_to_resource_pool(int amount){
        this._resource_pool+=amount;
    }

    public int get_resource_pool() {
        return _resource_pool;
    }

    public int get_resource_amount() {
        return _resource_amount;
    }

    public void set_resource_amount(int _resource_amount) {
        this._resource_amount = _resource_amount;
    }
}
