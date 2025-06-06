package entity;

import main.GamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    // setting world size and speed
    public int worldX, worldY;
    public int speed;

    // images for the different entities
    public BufferedImage jump, down, left, right, jumpLeft, jumpRight, downLeft, downRight;
    public String direction;

    // hit box of the entity
    public Rectangle solidArea;
    public boolean collisionOn = false;

    // incorporating gravity to entities
    public double velocityY = 0;

    //i can change back to 0.5 if i want
    public double gravity = 1;
    public double jumpStrength = -10;
    public boolean onGround = false;

    public void applyGravity(GamePanel gp) {
        // Apply gravity to vertical speed
        velocityY += gravity;

        // Limit fall speed
        if (velocityY > 10) {
            velocityY = 10;
        }

        // Calculate bottom-center of the entity
        int entityBottomWorldY = worldY + solidArea.y + solidArea.height;
        int belowRow = (entityBottomWorldY + 1) / gp.tileSize;
        int centerX = worldX + solidArea.x + solidArea.width / 2;
        int belowCol = centerX / gp.tileSize;

        boolean tileBelowIsSolid = false;

        if (belowCol >= 0 && belowCol < gp.maxWorldCol &&
                belowRow >= 0 && belowRow < gp.maxWorldRow) {

            int tileBelow = gp.tileManager.mapTileNumber[belowCol][belowRow];
            tileBelowIsSolid = gp.tileManager.tile[tileBelow].collision;
        }

        // Only snap to ground if falling (velocityY >= 0)
        if (tileBelowIsSolid && velocityY >= 0) {
            velocityY = 0;
            onGround = true;

            // Snap to top of the solid tile
            worldY = belowRow * gp.tileSize - solidArea.y - solidArea.height;
        } else {
            onGround = false;
            // Move vertically
            worldY += (int) velocityY;
        }
    }


    public void jump() {
        if (onGround) {
            System.out.println("JUMP!");
            velocityY = jumpStrength;
            onGround = false;
        } else {
            System.out.println("Blocked: not on ground");
        }
    }

}
