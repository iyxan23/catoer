import greenfoot.*;
import java.util.*;

public class PieceKing extends ChessPiece {
    public PieceKing(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        super(color, position, size);

        GreenfootImage img;

        if (color == ChessPieceColor.WHITE) {
            img = new GreenfootImage("white-king.png");
        } else {
            img = new GreenfootImage("black-king.png");
        }

        img.scale(size, size);
        setImage(img);
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    ) {
        // TODO: the king cannot move in squares that are in sights of the
        //       other enemy's pieces, nor take pieces that are defended.

        int[][] potentialMoves = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        ArrayList<PossibleMove> possibleMoves = new ArrayList<>();

        for (int[] move : potentialMoves) {
            int dx = move[0];
            int dy = move[1];

            if (position.x + dx < 0 || position.x + dx >= Board.WIDTH) continue;
            if (position.y + dy < 0 || position.y + dy >= Board.HEIGHT) continue;

            ChessPiece piece = board.getPiece(position.x + dx, position.y + dy);
            if (piece != null && piece.color == color) continue;

            possibleMoves.add(new PossibleMove(
                position.newMove(dx, dy),
                piece
            ));
        }

        return possibleMoves.toArray(new PossibleMove[0]);
    }
}
