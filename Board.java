import greenfoot.*;
import java.util.*;

public class Board {
    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    public ChessPiece[][] pieces = new ChessPiece[WIDTH][HEIGHT];
    private ArrayList<ChessPiece> arrayPieces = new ArrayList<>();

    public Board() {}

    public static Board newStartingGame(int pieceSize) {
        Board board = new Board();

        board.pieces[0][0] = new PieceRook(ChessPieceColor.WHITE,   new BoardCoordinate(0, 0), pieceSize);
        board.pieces[1][0] = new PieceKnight(ChessPieceColor.WHITE, new BoardCoordinate(1, 0), pieceSize);
        board.pieces[2][0] = new PieceBishop(ChessPieceColor.WHITE, new BoardCoordinate(2, 0), pieceSize);
        board.pieces[3][0] = new PieceQueen(ChessPieceColor.WHITE,  new BoardCoordinate(3, 0), pieceSize);
        board.pieces[4][0] = new PieceKing(ChessPieceColor.WHITE,   new BoardCoordinate(4, 0), pieceSize);
        board.pieces[5][0] = new PieceBishop(ChessPieceColor.WHITE, new BoardCoordinate(5, 0), pieceSize);
        board.pieces[6][0] = new PieceKnight(ChessPieceColor.WHITE, new BoardCoordinate(6, 0), pieceSize);
        board.pieces[7][0] = new PieceRook(ChessPieceColor.WHITE,   new BoardCoordinate(7, 0), pieceSize);

        board.pieces[0][7] = new PieceRook(ChessPieceColor.BLACK,   new BoardCoordinate(0, 7), pieceSize);
        board.pieces[1][7] = new PieceKnight(ChessPieceColor.BLACK, new BoardCoordinate(1, 7), pieceSize);
        board.pieces[2][7] = new PieceBishop(ChessPieceColor.BLACK, new BoardCoordinate(2, 7), pieceSize);
        board.pieces[3][7] = new PieceQueen(ChessPieceColor.BLACK,  new BoardCoordinate(3, 7), pieceSize);
        board.pieces[4][7] = new PieceKing(ChessPieceColor.BLACK,   new BoardCoordinate(4, 7), pieceSize);
        board.pieces[5][7] = new PieceBishop(ChessPieceColor.BLACK, new BoardCoordinate(5, 7), pieceSize);
        board.pieces[6][7] = new PieceKnight(ChessPieceColor.BLACK, new BoardCoordinate(6, 7), pieceSize);
        board.pieces[7][7] = new PieceRook(ChessPieceColor.BLACK,   new BoardCoordinate(7, 7), pieceSize);

        for (int i = 0; i < WIDTH; i++) {
            board.pieces[i][1] = new PiecePawn(ChessPieceColor.WHITE, new BoardCoordinate(i, 1), pieceSize);
            board.pieces[i][6] = new PiecePawn(ChessPieceColor.BLACK, new BoardCoordinate(i, 6), pieceSize);
        }

        board.syncPieces();

        return board;
    }

    protected void syncPieces() {
        this.arrayPieces.clear();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (this.pieces[x][y] != null) {
                    this.arrayPieces.add(this.pieces[x][y]);
                }
            }
        }
    }

    public void movePiece(
        BoardCoordinate from,
        BoardCoordinate to
    ) {
        if (!this.hasPiece(from.x, from.y))
            throw new RuntimeException("No piece exists at " + from);

        this.pieces[to.x][to.y] = this.pieces[from.x][from.y];
        this.pieces[from.x][from.y] = null;

        this.pieces[to.x][to.y].position = to;

        this.syncPieces();
    }

    public ChessPiece[] getAllPieces() {
        return this.arrayPieces.toArray(new ChessPiece[0]);
    }

    public void setPiece(ChessPiece piece, int x, int y) {
        pieces[x][y] = piece;
    }

    public ChessPiece getPiece(int x, int y) {
        return pieces[x][y];
    }

    public boolean hasPiece(int x, int y) {
        return pieces[x][y] != null;
    }

    public static boolean inBounds(int x, int y) {
        return x >= 0 && x < Board.WIDTH && y >= 0 && y < Board.HEIGHT;
    }
}
