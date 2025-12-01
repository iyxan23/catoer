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

    private ArrayList<Actor> moveIndicators = new ArrayList<>();
    private ChessPiece selectedPiece = null;
    private PossibleMove[] seletedPiecePossibleMoves = null;

    private void showMoveIndicators(PossibleMove[] moves) {
        for (PossibleMove move : moves) {
            PossibleMoveIndicator indicator =
                new PossibleMoveIndicator(move, this.pieceSize);

            this.moveIndicators.add(indicator);
            this.world.addObject(
                indicator,
                (move.coordinate.x * this.cellSize) + (this.cellSize/2),
                ((Board.WIDTH - move.coordinate.y - 1) * this.cellSize) + (this.cellSize/2)
            );
        }
    }

    private void clearMoveIndicators() {
        for (Actor indicator : this.moveIndicators)
            this.world.removeObject(indicator);
        this.moveIndicators.clear();
    }

    private PieceSelectionIndicator pieceSelectionIndicator = null;

    private void showPieceSelectionIndicator(
        BoardCoordinate position
    ) {
        if (this.pieceSelectionIndicator != null)
            this.hidePieceSelectionIndicator();


        this.pieceSelectionIndicator =
            new PieceSelectionIndicator(this.pieceSize);

        this.world.addObject(
            this.pieceSelectionIndicator,
            (position.x * this.cellSize) + (this.cellSize/2),
            ((Board.WIDTH - position.y - 1) * this.cellSize) + (this.cellSize/2)
        );
    }

    private void hidePieceSelectionIndicator() {
        if (this.pieceSelectionIndicator == null) return;
        this.world.removeObject(this.pieceSelectionIndicator);
        this.pieceSelectionIndicator = null;
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
                this.deselectPiece();
                return;
            }

            return;
        }

        // piece is not null

        if (this.selectedPiece != null) {
            // clicking on other piece, while having one selected
            // may be a taking move
            if (this.attemptMoveSelectedPieceTo(x, y)) return;

            this.deselectPiece();
            if (this.selectedPiece == piece) return;
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
        this.showMoveIndicators(moves);
        this.showPieceSelectionIndicator(new BoardCoordinate(x, y));
    }

    private void deselectPiece() {
        this.clearMoveIndicators();
        this.hidePieceSelectionIndicator();

        this.seletedPiecePossibleMoves = null;
        this.selectedPiece = null;
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
        this.clearMoveIndicators();
        this.hidePieceSelectionIndicator();
        this.hideKingCheckIndicator();

        this.board.movePiece(
            this.selectedPiece.position,
            move.coordinate
        );

        // trigger re-render
        this.boardRenderer.render(this.board);

        this.seletedPiecePossibleMoves = null;
        this.selectedPiece = null;

        this.switchTurn();
        this.analyzeBoard();
    }

    private KingCheckIndicator kingCheckIndicator = null;

    private void showKingCheckIndicator(
        BoardCoordinate position
    ) {
        this.kingCheckIndicator = new KingCheckIndicator(this.pieceSize);
        this.world.addObject(
            this.kingCheckIndicator,
            (position.x * this.cellSize) + (this.cellSize/2),
            ((Board.WIDTH - position.y - 1) * this.cellSize) + (this.cellSize/2)
        );
    }

    private void hideKingCheckIndicator() {
        if (this.kingCheckIndicator == null) return;

        this.world.removeObject(this.kingCheckIndicator);
        this.kingCheckIndicator = null;
    }

    private void analyzeBoard() {
        this.analyzer.analyze(turn);

        PossibleMove checkMove = this.analyzer.getCheckMove();

        if (checkMove != null) {
            // show check indicator
            this.showKingCheckIndicator(checkMove.coordinate);
        }
    }
}
