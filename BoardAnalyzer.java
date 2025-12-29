import java.util.ArrayList;
import greenfoot.*;

public class BoardAnalyzer {
    private Board board;
    private boolean checkmated = false;

    // 8x8 -> list of moves
    private PossibleMove[][][] currentTurnPossibleMoves =
        new PossibleMove[Board.WIDTH][Board.HEIGHT][];

    public BoardAnalyzer(Board board) {
        this.board = board;
    }

    private void clearPossibleMoves() {
        this.currentTurnPossibleMoves =
            new PossibleMove[Board.WIDTH][Board.HEIGHT][];
    }

    private static PossibleMove[][][] analyzeAllMoves(
        Board board,
        ChessPieceColor onlyAnalyzeColor
    ) {
        PossibleMove[][][] boardPossibleMoves =
            new PossibleMove[Board.WIDTH][Board.HEIGHT][];

        for (ChessPiece piece : board.getAllPieces()) {
            if (onlyAnalyzeColor != null)
                if (piece.color != onlyAnalyzeColor) continue;

            boardPossibleMoves[piece.position.x][piece.position.y] =
                piece.calculatePossibleMoves(piece.position, board);
        }

        return boardPossibleMoves;
    }

    private static PossibleMove findChecks(
        PossibleMove[][][] boardPossibleMoves,
        ChessPieceColor currentTurnColor
    ) {
        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                PossibleMove[] moves = boardPossibleMoves[x][y];
                if (moves == null) continue;

                for (PossibleMove move : moves) {
                    if (
                        move.takesPiece != null &&
                        move.takesPiece.color == currentTurnColor &&
                        move.takesPiece instanceof PieceKing
                    ) {
                        return move;
                    }
                }
            }
        }

        return null;
    }

    private PossibleMove checkMove = null;

    public void analyze(ChessPieceColor currentTurnColor) {
        this.clearPossibleMoves();
        this.checkMove = null;

        // basic board analysis
        PossibleMove[][][] allPossibleMoves =
            BoardAnalyzer.analyzeAllMoves(this.board, null);

        // check if there are any checks
        PossibleMove possibleCheck =
            BoardAnalyzer.findChecks(allPossibleMoves, currentTurnColor);

        if (possibleCheck != null) this.checkMove = possibleCheck;

        // find all legal moves
        ArrayList<PossibleMove>[][] legalMoves = new ArrayList[Board.WIDTH][Board.HEIGHT];

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                ChessPiece piece = this.board.getPiece(x, y);
                if (piece == null) continue;
                if (piece.color != currentTurnColor) continue;

                PossibleMove[] moves = allPossibleMoves[x][y];
                if (moves == null) continue;

                for (PossibleMove move : moves) {
                    // simulate this move, then re-analyze it.
                    // in that anaysis, find any checks.
                    // if there are no checks, the move is valid
                    Board virtualBoard;
                    try {
                        virtualBoard = (Board) this.board.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                    virtualBoard.movePiece(
                        new BoardCoordinate(x, y),
                        move.coordinate
                    );

                    // re-analyze
                    PossibleMove[][][] analysis =
                        BoardAnalyzer.analyzeAllMoves(
                            virtualBoard, currentTurnColor.opposite()
                        );

                    if (possibleCheck != null) {
                        if (BoardAnalyzer.findChecks(
                                analysis, currentTurnColor
                            ) == null) {
                            // this is a valid move
                            if (legalMoves[x][y] == null)
                                legalMoves[x][y] = new ArrayList<>();
                            legalMoves[x][y].add(move);
                        }
                        continue;
                    }

                    // if there are no checks, analyze moves that don't put you
                    // in check
                    if (BoardAnalyzer.findChecks(
                            analysis, currentTurnColor
                        ) != null) 
                        // this is not a valid move
                        continue;


                    if (legalMoves[x][y] == null) 
                        legalMoves[x][y] = new ArrayList<>();
                    legalMoves[x][y].add(move);
                }
            }
        }

        // set currentTurnPossibleMoves
        this.checkmated = true;
        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                this.currentTurnPossibleMoves[x][y] =
                    legalMoves[x][y] != null ?
                        legalMoves[x][y].toArray(new PossibleMove[0]) :
                        new PossibleMove[0];

                if (legalMoves[x][y] != null) {
                    this.checkmated = false;
                }
            }
        }
    }

    public boolean isCheckmated() {
        return this.checkmated;
    }

    public boolean isInCheck() {
        return this.checkMove != null;
    }

    public PossibleMove getCheckMove() {
        return this.checkMove;
    }

    public PossibleMove[] analyzePossibleMoves(
        BoardCoordinate position
    ) {
        return this.currentTurnPossibleMoves[position.x][position.y];
    }
}
