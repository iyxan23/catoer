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
        this.analyzer.analyze(turn);
    }

    private ChessPieceColor turn = ChessPieceColor.WHITE;

    private void switchTurn() {
        this.turn = this.turn.opposite();
    }

    private ArrayList<Actor> indicators = new ArrayList<>();
    private ChessPiece selectedPiece = null;
    private PossibleMove[] seletedPiecePossibleMoves = null;

    private void showIndicators(PossibleMove[] moves) {
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

    private void clearIndicators() {
        for (Actor indicator : this.indicators)
            this.world.removeObject(indicator);
        this.indicators.clear();
    }

    public void onLeftClick(int rawX, int rawY) {
        // find the associated piece
        int x = rawX / this.cellSize;
        int y = (this.world.getHeight() - rawY) / this.cellSize;

        ChessPiece piece = this.board.getPiece(x, y);

        if (piece == null) {
            // clicking on an empty space

            if (this.selectedPiece != null) {
                if (this.attemptMoveSelectedPieceTo(x, y)) return;

                // not valid move, deselect
                this.clearIndicators();

                this.seletedPiecePossibleMoves = null;
                this.selectedPiece = null;

                return;
            }

            return;
        }

        // piece is not null

        if (this.selectedPiece != null) {
            // clicking on other piece, while having one selected
            // may be a taking move
            if (this.attemptMoveSelectedPieceTo(x, y)) return;

            // no taking move, deselect selected if any, then select the new piece
            this.clearIndicators();

            if (this.selectedPiece == piece) {
                this.seletedPiecePossibleMoves = null;
                this.selectedPiece = null;

                return;
            }
        }

        // only allow selecting the current turn's color
        if (piece.color != this.turn) return;

        this.selectPiece(piece, x, y);
    }

    private void selectPiece(ChessPiece piece, int x, int y) {
        this.selectedPiece = piece;

        PossibleMove[] moves = this.analyzer.analyzePossibleMoves(
            new BoardCoordinate(x, y)
        );

        this.seletedPiecePossibleMoves = moves;
        this.showIndicators(moves);
    }

    private boolean attemptMoveSelectedPieceTo(int x, int y) {
        // we have something selected, this may be an attempt to move
        // check if it's included within the possible moves
        for (PossibleMove move : this.seletedPiecePossibleMoves) {
            if (move.coordinate.x == x && move.coordinate.y == y) {
                this.moveSelectedPiece(move);
                return true;
            }
        }

        return false;
    }

    private void moveSelectedPiece(PossibleMove move) {
        this.clearIndicators();

        this.board.movePiece(
            this.selectedPiece.position,
            move.coordinate
        );

        // trigger re-render
        this.boardRenderer.render(this.board);

        this.seletedPiecePossibleMoves = null;
        this.selectedPiece = null;

        this.switchTurn();
        this.analyzer.analyze(turn);
    }
}
