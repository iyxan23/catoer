import greenfoot.*;
import java.util.*;

public class PieceBishop extends ChessPiece {
    public PieceBishop(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        super(color, position, size);

        GreenfootImage img;

        if (color == ChessPieceColor.WHITE) {
            img = new GreenfootImage("white-bishop.png");
        } else {
            img = new GreenfootImage("black-bishop.png");
        }

        img.scale(size, size);
        setImage(img);
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    ) {
        ArrayList<PossibleMove> moves = new ArrayList<>();

        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // diagonals

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            int x = position.x + dx;
            int y = position.y + dy;

            while (
                x >= 0 && x < Board.WIDTH &&
                y >= 0 && y < Board.HEIGHT
            ) {
                ChessPiece piece = board.getPiece(x, y);
                if (piece != null && piece.color == color) break;

                moves.add(new PossibleMove(
                    new BoardCoordinate(x, y),
                    piece
                ));

                if (piece != null) break;

                x += dx;
                y += dy;
            }
        }

        return moves.toArray(new PossibleMove[0]);
    }
}
