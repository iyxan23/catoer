import greenfoot.*;
import java.util.*;

public class ChessGame {
    private World world;

    private Board board;
    private BoardRenderer boardRenderer;
    private BoardAnalyzer analyzer;

    private int cellSize;
    private int pieceSize;

    public ChessGame(
        World world,

        int cellSize,
        int pieceSize
    ) {
        this.world = world;

        this.cellSize = cellSize;
        this.pieceSize = pieceSize;

        this.board = Board.newStartingGame(pieceSize);
        this.boardRenderer = new BoardRenderer(world, cellSize, pieceSize);
        this.boardRenderer.renderBoardBackground();
        this.boardRenderer.render(this.board);

        this.analyzer = new BoardAnalyzer(this.board);
    }

    private ArrayList<Actor> indicators = new ArrayList<>();
    private ChessPiece selectedPiece = null;

    public void onLeftClick(int rawX, int rawY) {
        // find the associated piece
        int x = rawX / this.cellSize;
        int y = (this.world.getHeight() - rawY) / this.cellSize;

        ChessPiece piece = this.board.getPiece(x, y);
        if (piece == null) return;

        if (this.selectedPiece != null) {
            // reset
            for (Actor indicator : this.indicators)
                this.world.removeObject(indicator);
            this.indicators.clear();

            // if already selected, deselect and return
            if (piece == this.selectedPiece) {
                this.selectedPiece = null;
                return;
            }
        }

        this.selectedPiece = piece;

        PossibleMove[] moves = this.analyzer.analyzePossibleMoves(
            piece, new BoardCoordinate(x, y)
        );

        // show indicators
        for (PossibleMove move : moves) {
            PossibleMoveIndicator indicator =
                new PossibleMoveIndicator(move, this.pieceSize);
            this.indicators.add(indicator);
            this.world.addObject(
                indicator,
                (move.coordinate.x * this.cellSize) + (this.cellSize/2),
                ((Board.WIDTH - move.coordinate.y - 1) * this.cellSize) + (this.cellSize/2)
            );
        }
    }
}
