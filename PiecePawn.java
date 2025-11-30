import greenfoot.*;
import java.util.*;

public class PiecePawn extends ChessPiece {
    public PiecePawn(
        ChessPieceColor color,
        BoardCoordinate position,
        int size
    ) {
        super(color, position, size);

        GreenfootImage img;

        if (color == ChessPieceColor.WHITE) {
            img = new GreenfootImage("white-pawn.png");
        } else {
            img = new GreenfootImage("black-pawn.png");
        }

        img.scale(size, size);
        setImage(img);
    }

    public PossibleMove[] calculatePossibleMoves(
        BoardCoordinate position,
        Board board
    ) {
        ArrayList<PossibleMove> moves = new ArrayList<>();

        int direction = (color == ChessPieceColor.WHITE) ? 1 : -1;
        int startRow  = (color == ChessPieceColor.WHITE) ? 1 : Board.HEIGHT - 2;

        int x = position.x;
        int y = position.y;

        BoardCoordinate oneStep = position.newMove(0, direction);
        if (Board.inBounds(oneStep.x, oneStep.y) &&
            !board.hasPiece(oneStep.x, oneStep.y)) {
            moves.add(new PossibleMove(oneStep, null));

            if (y == startRow) {
                BoardCoordinate twoStep = position.newMove(0, 2 * direction);

                if (Board.inBounds(twoStep.x, twoStep.y) &&
                    !board.hasPiece(twoStep.x, twoStep.y)) {
                    moves.add(new PossibleMove(twoStep, null));
                }
            }
        }

        int[][] captureOffsets = new int[][] {
            { -1, direction },
            {  1, direction }
        };

        for (int[] off : captureOffsets) {
            int nx = x + off[0];
            int ny = y + off[1];

            if (!Board.inBounds(nx, ny)) continue;

            ChessPiece target = board.getPiece(nx, ny);

            if (target != null && target.color != color) {
                moves.add(new PossibleMove(new BoardCoordinate(nx, ny), target));
            }
        }

        return moves.toArray(new PossibleMove[0]);
    }
}
