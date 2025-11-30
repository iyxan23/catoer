import greenfoot.*;

public abstract class ChessPiece extends Actor {
    BoardCoordinate position;
    ChessPieceColor color;

    public ChessPiece(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        this.position = coords;
        this.color = color;
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    );
}

public class PossibleMove {
    public BoardCoordiante coordinate;
    public ChessPiece takesPiece;

    public PossibleMove(
        BoardCoordinate coordinate,
        ChessPiece takesPiece
    ) {
        this.coordinate = coordinate;
        this.takesPiece = takesPiece;
    }
}
