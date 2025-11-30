import greenfoot.*;

public abstract class ChessPiece extends Actor {
    BoardCoordinate position;
    ChessPieceColor color;

    public ChessPiece(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        this.position = position;
        this.color = color;
    }

    public abstract PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    );
}