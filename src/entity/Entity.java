package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage jump, down, left, right;
    public String direction;

    public Rectangle solidArea;
    public boolean collisionOn = false;

}
