package backend.gameLogic;

public class Position {
    private int _x;
    private int _y;
    public Position(){}
    public Position(int x, int y){
        this._x = x;
        this._y = y;
    }

    public double distance_from(Position target){
        int distance_x = target.get_x() - this.get_x();
        int distance_y = target.get_y() - this.get_y();
        return Math.sqrt((float) (Math.pow(distance_x, 2)+Math.pow(distance_y,2)));
    }

    public int get_x() {
        return _x;
    }
    public int get_y() {
        return _y;
    }

    public void setPosition (Position newPosition){
        _x = newPosition.get_x();
        _y = newPosition.get_y();
    }


    public void move(int x, int y){
        this._x += x;
        this._y += y;
    }

    public boolean compare_value(Position position){
        return _x == position.get_x()&& _y == position.get_y();
    }

}
