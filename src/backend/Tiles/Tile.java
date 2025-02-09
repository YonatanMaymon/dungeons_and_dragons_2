package backend.Tiles;

import backend.gameLogic.Position;

public class Tile {
    private char tile;
    Position position;
    public Tile( char tile){
        this.tile = tile;
    }
    public Tile(Position position, char tile){
        this.position = position;
        this.tile = tile;
    }

    public void accept(Visitor visitor){
        visitor.visit_tile(this);
    }

    public Character get_tile() {
        return tile;
    }

    protected void set_tile(char tile){
        this.tile = tile;}

    public Position get_position() {
        return position;
    }

    public void set_position(Position position) {
        this.position = position;
    }

}
