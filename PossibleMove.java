public class PossibleMove {
    public BoardCoordinate coordinate;
    public ChessPiece takesPiece;

    public PossibleMove(
        BoardCoordinate coordinate,
        ChessPiece takesPiece
    ) {
        this.coordinate = coordinate;
        this.takesPiece = takesPiece;
    }
}
