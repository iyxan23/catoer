import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author iyxan23 <hi@iyxan.dev>
 * @version 0.0.1
 */
public class ChessWorld extends World {
    private static final int CHESS_PIECE_SIZE = 68;
    private static final int CHESS_CELL_SIZE = 72;

    private ChessGame chessGame;

    public ChessWorld() {
        super(
            CHESS_CELL_SIZE * Board.WIDTH,
            CHESS_CELL_SIZE * Board.HEIGHT,
            1
        );

        this.chessGame = new ChessGame(
            this,
            CHESS_CELL_SIZE, CHESS_PIECE_SIZE
        );
        prepare();
    }

    public void act() {
        if (Greenfoot.mouseClicked(null)) {
            MouseInfo info = Greenfoot.getMouseInfo();

            switch (info.getButton()) {
                case 1:
                    this.chessGame.onLeftClick(info.getX(), info.getY());
                    break;
            }
        }
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
