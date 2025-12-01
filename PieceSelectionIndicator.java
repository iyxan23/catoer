import greenfoot.*;

public class PieceSelectionIndicator extends Actor {
    public PieceSelectionIndicator(int pieceSize) {
        GreenfootImage img = new GreenfootImage("selection-indicator.png");
        img.scale(pieceSize, pieceSize);
        this.setImage(img);
    }
}
