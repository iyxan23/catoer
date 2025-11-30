import greenfoot.*;

public class Board {
    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    public ChessPiece[][] pieces = new ChessPiece[WIDTH][HEIGHT];

    public Board() {}

    public static Board newStartingGame(int pieceSize) {
        Board board = new Board();

        board.pieces[0][0] = new PieceRook(ChessPieceColor.WHITE, new BoardCoordinate(0, 0), pieceSize);
        board.pieces[1][0] = new PieceKnight(ChessPieceColor.WHITE, new BoardCoordinate(0, 1), pieceSize);
        board.pieces[2][0] = new PieceBishop(ChessPieceColor.WHITE, new BoardCoordinate(0, 2), pieceSize);
        board.pieces[3][0] = new PieceQueen(ChessPieceColor.WHITE, new BoardCoordinate(0, 3), pieceSize);
        board.pieces[4][0] = new PieceKing(ChessPieceColor.WHITE, new BoardCoordinate(0, 4), pieceSize);
        board.pieces[5][0] = new PieceBishop(ChessPieceColor.WHITE, new BoardCoordinate(0, 5), pieceSize);
        board.pieces[6][0] = new PieceKnight(ChessPieceColor.WHITE, new BoardCoordinate(0, 6), pieceSize);
        board.pieces[7][0] = new PieceRook(ChessPieceColor.WHITE, new BoardCoordinate(0, 7), pieceSize);

        board.pieces[0][7] = new PieceRook(ChessPieceColor.BLACK, new BoardCoordinate(7, 0), pieceSize);
        board.pieces[1][7] = new PieceKnight(ChessPieceColor.BLACK, new BoardCoordinate(7, 1), pieceSize);
        board.pieces[2][7] = new PieceBishop(ChessPieceColor.BLACK, new BoardCoordinate(7, 2), pieceSize);
        board.pieces[3][7] = new PieceQueen(ChessPieceColor.BLACK, new BoardCoordinate(7, 3), pieceSize);
        board.pieces[4][7] = new PieceKing(ChessPieceColor.BLACK, new BoardCoordinate(7, 4), pieceSize);
        board.pieces[5][7] = new PieceBishop(ChessPieceColor.BLACK, new BoardCoordinate(7, 5), pieceSize);
        board.pieces[6][7] = new PieceKnight(ChessPieceColor.BLACK, new BoardCoordinate(7, 6), pieceSize);
        board.pieces[7][7] = new PieceRook(ChessPieceColor.BLACK, new BoardCoordinate(7, 7), pieceSize);

        for (int i = 0; i < WIDTH; i++) {
            board.pieces[i][1] = new PiecePawn(ChessPieceColor.WHITE, new BoardCoordinate(i, 1), pieceSize);
            board.pieces[i][6] = new PiecePawn(ChessPieceColor.BLACK, new BoardCoordinate(i, 6), pieceSize);
        }

        return board;
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
}
