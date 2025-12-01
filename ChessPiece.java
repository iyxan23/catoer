import greenfoot.*;

public abstract class ChessPiece extends Actor implements Cloneable {
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        ChessPiece cloned = (ChessPiece) super.clone();
        cloned.position = (BoardCoordinate) this.position.clone();
        cloned.color = this.color;
        return cloned;
    }
}
