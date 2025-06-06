package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        //where you spawn
        worldX = 43; //gp.tileSize();
        worldY = 2304;
        speed = 4;

        direction = "right";

        // Gravity setup
        gravity = 0.7;
        jumpStrength = -10;
        velocityY = 0;
        onGround = false;
    }

    public void update() {
        // Jump if up key is pressed and on ground
        if (keyH.upPressed) {
            jump();
        }

        // Duck
        if (keyH.downPressed) {
            direction = "down";
        }

        // Horizontal movement
        if (keyH.leftPressed) {
            direction = "left";
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            if (!collisionOn) {
                worldX -= speed;
            }
        } else if (keyH.rightPressed) {
            direction = "right";
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            if (!collisionOn) {
                worldX += speed;
            }
        }

        // Apply gravity (handles onGround flag internally)
        applyGravity(gp);
    }

    public void getPlayerImage() {
        try {
            jumpLeft = ImageIO.read(getClass().getResourceAsStream("/player/slime_jump_left.png"));
            jumpRight = ImageIO.read(getClass().getResourceAsStream("/player/slime_jump_right.png"));
            downLeft = ImageIO.read(getClass().getResourceAsStream("/player/slime_down_left.png"));
            downRight = ImageIO.read(getClass().getResourceAsStream("/player/slime_down_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/slime_left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/slime_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image;

        if (!onGround) {
            image = direction.equals("left") ? jumpLeft : jumpRight;
        } else if (keyH.leftPressed) {
            image = left;
        } else if (keyH.rightPressed) {
            image = right;
        } else {
            // Idle or duck
            if (direction.equals("down")) {
                image = direction.equals("left") ? downLeft : downRight;
            } else if (direction.equals("left")) {
                image = left;
            } else {
                image = right;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
