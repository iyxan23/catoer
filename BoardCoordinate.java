public class BoardCoordinate implements Cloneable {
    public int x;
    public int y;

    public BoardCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public BoardCoordinate newMove(int x, int y) {
        return new BoardCoordinate(
            this.x + x, this.y + y
        );
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
