package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.cameraX;
        int screenY = worldY - gp.cameraY;

        if (worldX + gp.tileSize > gp.cameraX &&
                worldX - gp.tileSize < gp.cameraX + gp.screenWidth &&
                worldY + gp.tileSize > gp.cameraY &&
                worldY - gp.tileSize < gp.cameraY + gp.screenHeight) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
