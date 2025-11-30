import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author iyxan23 <hi@iyxan.dev>
 * @version 0.0.1
 */
public class ChessWorld extends World
{
    private static final int CHESS_PIECE_SIZE = 128;
    private static final int CHESS_PIECE_PADDING = 16;

    public ChessWorld()
    {
        super(
            (CHESS_PIECE_SIZE + CHESS_PIECE_PADDING) * 8,
            (CHESS_PIECE_SIZE + CHESS_PIECE_PADDING) * 8,
            1
        );
    }
}
