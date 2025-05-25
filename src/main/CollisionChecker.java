package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        //fixme: might be something wrong with this
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX/ gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY /gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "jump":
                // fixme: problem with this line of code. crashes the code when jumping.
//                entityTopRow = (entityTopWorldY - entity.speed) / gp.originalTileSize;
//                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
//                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
//                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision){
//                    entity.collisionOn = true;
//                }
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;

        }
    }
}
