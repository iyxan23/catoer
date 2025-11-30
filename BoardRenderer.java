import greenfoot.*;

public class BoardRenderer {
    private World world;

    public BoardRenderer(World world) {
        this.world = world;
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
