import greenfoot.*;

public class BoardRenderer {
    private World world;

    private static final Color BOARD_BLACK = new Color(72, 87, 153);
    private static final Color BOARD_WHITE = new Color(225, 241, 247);

    private int cellSize;
    private int pieceSize;

    public BoardRenderer(
        World world,
        int cellSize,
        int pieceSize
    ) {
        this.world = world;

        this.cellSize = cellSize;
        this.pieceSize = pieceSize;
    }

    public void renderBoardBackground() {
        GreenfootImage background = new GreenfootImage(
            Board.WIDTH * this.cellSize,
            Board.HEIGHT * this.cellSize
        );

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                if ((x + y) % 2 == 0) {
                    background.setColor(BOARD_WHITE);
                } else {
                    background.setColor(BOARD_BLACK);
                }

                background.fillRect(
                    x * this.cellSize,
                    y * this.cellSize,
                    this.cellSize,
                    this.cellSize
                );
            }
        }

        this.world.setBackground(background);
    }

    private void clear(Board board) {
        for (ChessPiece piece : board.getAllPieces())
            this.world.removeObject(piece);
    }

    public void render(Board board) {
        this.clear(board);

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                ChessPiece piece = board.getPiece(x, y);
                if (piece == null) continue;

                world.addObject(
                    piece,

                    x * this.cellSize + (int)((this.cellSize) / 2),

                    // our coordinate system starts from the bottom left
                    // so we need to invert the y coordinate
                    this.world.getHeight() - (
                        y * this.cellSize + (int)((this.cellSize) / 2)
                    )
                );
            }
        }
    }
}
