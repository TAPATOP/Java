package bg.uni.sofia.fmi.mjt.dungeon.actor;

public class Position {
    // Constructor //
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position other){
        this.x = other.x;
        this.y = other.y;
    }

    // Methods//
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Member Variables //
    private int x;
    private int y;
}
