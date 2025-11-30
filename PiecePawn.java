public class PiecePawn extends ChessPiece {
    public PiecePawn(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        super(color, position);

        GreenfootImage img;

        if (color == ChessPieceColor.WHITE) {
            img = new GreenfootImage("pawn-white.png")
        } else {
            img = new GreenfootImage("pawn-black.png");
        }

        img.scale(size, size);
        setImage(img);
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    ) {
        ArrayList<BoardCoordiante> potentialMoves;

        if (color == ChessPieceColor.WHITE) {
            potentialMoves.push(position.newMove(0, 1));
            potentialMoves.push(position.newMove(1, 1));
            potentialMoves.push(position.newMove(-1, 1));

            if (
                position.y == 1 &&
                !board.hasPiece(position.x, 2)
            ) {
                potentialMoves.newMove(0, 2),
            }
        } else {
            potentialMoves.push(position.newMove(0, -1));
            potentialMoves.push(position.newMove(1, -1));
            potentialMoves.push(position.newMove(-1, -1));

            if (
                position.y == Board.HEIGHT - 2 &&
                !board.hasPiece(position.x, Board.HEIGHT - 3)
            ) {
                potentialMoves.push(position.newMove(0, -2));
            }
        }

        ArrayList<PossibleMove> possibleMoves = new ArrayList<>();

        for (BoardCoordinate move : potentialMoves) {
            if (move.x < 0 || move.x >= Board.WIDTH) continue;
            if (move.y < 0 || move.y >= Board.HEIGHT) continue;

            ChessPiece piece = board.getPiece(move.x, move.y)
            if (piece.color == color) continue;

            possibleMoves.push(
                new PossibleMove(move, piece)
            );
        }

        return possibleMoves.toArray(new PossibleMove[0]);
    }
}
