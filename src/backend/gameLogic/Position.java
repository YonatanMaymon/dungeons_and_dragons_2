package backend.gameLogic;

public class Position {
    private int x;
    private int y;
    public Position(){}
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double distance_from(Position target){
        int distance_x = target.get_x() - this.get_x();
        int distance_y = target.get_y() - this.get_y();
        return Math.sqrt((float) (Math.pow(distance_x, 2)+Math.pow(distance_y,2)));
    }

    public int get_x() {
        return x;
    }
    public int get_y() {
        return y;
    }

    public void set_position(Position newPosition){
        x = newPosition.get_x();
        y = newPosition.get_y();
    }


    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

    public boolean compare_value(Position position){
        return x == position.get_x()&& y == position.get_y();
    }

}
