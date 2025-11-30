import greenfoot.*;
import java.util.*;

public class PieceKnight extends ChessPiece {
    public PieceKnight(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        super(color, position, size);

        GreenfootImage img;

        if (color == ChessPieceColor.WHITE) {
            img = new GreenfootImage("white-knight.png");
        } else {
            img = new GreenfootImage("black-knight.png");
        }

        img.scale(size, size);
        setImage(img);
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    ) {
        int[][] potentialMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {-1, 2}, {1, -2}, {-1, -2}
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
