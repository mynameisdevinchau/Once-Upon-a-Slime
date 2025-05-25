package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/ 2 - (gp.tileSize/2);
        screenY = gp.screenHeight/ 2 - (gp.tileSize/2);

        solidArea=  new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*23;
        speed = 4;

        direction = "right";
    }

    public void update(){
        if(keyH.upPressed){
            direction = "jump";
        } else if(keyH.downPressed){
            direction = "down";

        } else if (keyH.leftPressed) {
            direction = "left";

        } else if(keyH.rightPressed){
            direction = "right";

        }
        //this is used to check if the tile can be walked on
        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        //if collision is false, the player should be able to move

        //fixme: for some reason, it continues to press the key without stopping after release. 
        if(!collisionOn){
            switch(direction){
                case "jump":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
    }

    public void getPlayerImage(){
        try{
            //update this so you can create sprite images for your drawings
            jump = ImageIO.read(getClass().getResourceAsStream("/player/jump_mr_slime.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/get_down_mr_slime.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/step_to_the_left_slime.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/step_to_the_right_slime.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "jump":
                image = jump;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
