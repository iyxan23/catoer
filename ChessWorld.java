import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author iyxan23 <hi@iyxan.dev>
 * @version 0.0.1
 */
public class ChessWorld extends World {
    private static final int CHESS_PIECE_SIZE = 68;
    private static final int CHESS_CELL_SIZE = 72;

    private Board board;
    private BoardRenderer boardRenderer;

    public ChessWorld() {
        super(
            CHESS_CELL_SIZE * Board.WIDTH,
            CHESS_CELL_SIZE * Board.HEIGHT,
            1
        );

        this.board = Board.newStartingGame(CHESS_PIECE_SIZE);
        this.boardRenderer = new BoardRenderer(this);
        this.boardRenderer.render(
            this.board,
            CHESS_CELL_SIZE,
            CHESS_PIECE_SIZE
        );
    }
}
