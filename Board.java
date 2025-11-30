public class Board {
    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    public ChessPiece[][] pieces = new ChessPiece[WIDTH][HEIGHT];

    public Board() {}

    public void setPiece(ChessPiece piece, int x, int y) {
        pieces[x][y] = piece;
    }

    public ChessPiece getPiece(int x, int y) {
        return pieces[x][y];
    }

    public boolean hasPiece(int x, int y) {
        return pieces[x][y] != null;
    }
}
