import greenfoot.*;

public class KingCheckIndicator extends Actor {
    public KingCheckIndicator(int pieceSize) {
        GreenfootImage img = new GreenfootImage("check-indicator.png");
        img.scale(pieceSize, pieceSize);
        this.setImage(img);
    }
}
