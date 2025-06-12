package object;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.cameraX;
        int screenY = worldY - gp.cameraY;
        g2.drawImage(image, screenX, screenY,
                gp.tileSize, gp.tileSize, null);
    }

    public void interact(GamePanel gp){
        System.out.println("This object has no interaction deifned");
    }
}
