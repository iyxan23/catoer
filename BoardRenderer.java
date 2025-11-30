import greenfoot.*;

public class BoardRenderer {
    private World world;

    private static final Color BOARD_BLACK = new Color(72, 87, 153);
    private static final Color BOARD_WHITE = new Color(225, 241, 247);

    public BoardRenderer(World world) {
        this.world = world;
    }

    public void renderBoardBackground(int cellSize) {
        GreenfootImage background = new GreenfootImage(
            Board.WIDTH * cellSize,
            Board.HEIGHT * cellSize
        );

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                if ((x + y) % 2 == 0) {
                    background.setColor(BOARD_WHITE);
                } else {
                    background.setColor(BOARD_BLACK);
                }

                background.fillRect(
                    x * cellSize,
                    y * cellSize,
                    cellSize,
                    cellSize
                );
            }
        }

        this.world.setBackground(background);
    }

    public void render(
        Board board,

        int cellSize,
        int pieceSize
    ) {
        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                ChessPiece piece = board.getPiece(x, y);
                if (piece == null) continue;

                world.addObject(
                    piece,

                    x * cellSize + (int)((cellSize) / 2),

                    // our coordinate system starts from the bottom left
                    // so we need to invert the y coordinate
                    this.world.getHeight() - (
                        y * cellSize + (int)((cellSize) / 2)
                    )
                );
            }
        }
    }
}
