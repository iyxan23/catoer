import greenfoot.*;

public class PossibleMoveIndicator extends Actor {
    public PossibleMoveIndicator(
        PossibleMove possibleMove,
        int pieceSize
    ) {
        GreenfootImage img = new GreenfootImage("move-indicator.png");
        if (possibleMove.takesPiece != null) {
            img = new GreenfootImage("take-indicator.png");
        }

        img.scale(pieceSize, pieceSize);
        this.setImage(img);
    }
}
