package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width - 1;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height - 1;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        System.out.println("Checking direction: " + entity.direction);

        switch (entity.direction) {
            case "jump":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                if (entityTopRow >= 0 && entityTopRow < gp.tileManager.mapTileNumber[0].length &&
                        entityLeftCol >= 0 && entityLeftCol < gp.tileManager.mapTileNumber.length &&
                        entityRightCol >= 0 && entityRightCol < gp.tileManager.mapTileNumber.length) {

                    tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                    System.out.println("Jump tiles: " + tileNum1 + ", " + tileNum2);

                    if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                        System.out.println("Collision on jump!");
                    }
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                if (entityBottomRow >= 0 && entityBottomRow < gp.tileManager.mapTileNumber[0].length &&
                        entityLeftCol >= 0 && entityLeftCol < gp.tileManager.mapTileNumber.length &&
                        entityRightCol >= 0 && entityRightCol < gp.tileManager.mapTileNumber.length) {

                    tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                    System.out.println("Down tiles: " + tileNum1 + ", " + tileNum2);

                    if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                        System.out.println("Collision on down!");
                    }
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                if (entityLeftCol >= 0 && entityLeftCol < gp.tileManager.mapTileNumber.length &&
                        entityTopRow >= 0 && entityTopRow < gp.tileManager.mapTileNumber[0].length &&
                        entityBottomRow >= 0 && entityBottomRow < gp.tileManager.mapTileNumber[0].length) {

                    tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                    System.out.println("Left tiles: " + tileNum1 + ", " + tileNum2);

                    if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                        System.out.println("Collision on left!");
                    }
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                if (entityRightCol >= 0 && entityRightCol < gp.tileManager.mapTileNumber.length &&
                        entityTopRow >= 0 && entityTopRow < gp.tileManager.mapTileNumber[0].length &&
                        entityBottomRow >= 0 && entityBottomRow < gp.tileManager.mapTileNumber[0].length) {

                    tileNum1 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                    System.out.println("Right tiles: " + tileNum1 + ", " + tileNum2);

                    if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                        System.out.println("Collision on right!");
                    }
                }
                break;
        }

        System.out.println("Entity pos (worldX, worldY): (" + entity.worldX + ", " + entity.worldY + ")");
        System.out.println("Entity cols (L,R): " + entityLeftCol + ", " + entityRightCol);
        System.out.println("Entity rows (T,B): " + entityTopRow + ", " + entityBottomRow);
        System.out.println("Collision status: " + entity.collisionOn);
    }



}
