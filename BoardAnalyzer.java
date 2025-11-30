public class BoardAnalyzer {
    private Board board;

    public BoardAnalyzer(Board board) {
        this.board = board;
    }

    public PossibleMove[] analyzePossibleMoves(
        ChessPiece piece,
        BoardCoordinate position
    ) {
        return piece.calculatePossibleMoves(position, this.board);
    }
}
