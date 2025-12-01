public enum ChessPieceColor {
    WHITE, BLACK;

    public ChessPieceColor opposite() {
        if (this == WHITE) return BLACK;
        else return WHITE;
    }
}
