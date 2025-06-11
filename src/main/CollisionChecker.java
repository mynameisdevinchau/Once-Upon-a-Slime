package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // 1) start fresh each tick
        entity.collisionOn = false;

        // 2) calculate the world‐coords of your entity’s solid box
        int leftX   = entity.worldX + entity.solidArea.x;
        int rightX  = entity.worldX + entity.solidArea.x + entity.solidArea.width - 1;
        int topY    = entity.worldY + entity.solidArea.y;
        int bottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height - 1;

        // 3) find the current tile‐cols and rows
        int leftCol   =  leftX  / gp.tileSize;
        int rightCol  =  rightX / gp.tileSize;
        int topRow    =  topY   / gp.tileSize;
        int bottomRow =  bottomY/ gp.tileSize;

        int tileA_bg, tileB_bg;   // background
        int tileA_fg, tileB_fg;   // foreground

        switch(entity.direction) {
            case "jump":
                topRow = (topY - entity.speed) / gp.tileSize;
                // check both corners: left & right
                tileA_bg = gp.tileManager.background[gp.currentMap][leftCol][ topRow];
                tileB_bg = gp.tileManager.background[gp.currentMap][rightCol][ topRow];
                tileA_fg = gp.tileManager.mapTileNumber[gp.currentMap][ leftCol][ topRow];
                tileB_fg = gp.tileManager.mapTileNumber[gp.currentMap][rightCol][ topRow];
                break;

            case "down":
                bottomRow = (bottomY + entity.speed) / gp.tileSize;
                tileA_bg = gp.tileManager.background[gp.currentMap][leftCol][ bottomRow];
                tileB_bg = gp.tileManager.background[gp.currentMap][rightCol][ bottomRow];
                tileA_fg = gp.tileManager.mapTileNumber[gp.currentMap][ leftCol][ bottomRow];
                tileB_fg = gp.tileManager.mapTileNumber[gp.currentMap][rightCol][ bottomRow];
                break;

            case "left":
                leftCol = (leftX - entity.speed) / gp.tileSize;
                tileA_bg = gp.tileManager.background[gp.currentMap][ leftCol][ topRow];
                tileB_bg = gp.tileManager.background[gp.currentMap][ leftCol][ bottomRow];
                tileA_fg = gp.tileManager.mapTileNumber[gp.currentMap][ leftCol][ topRow];
                tileB_fg = gp.tileManager.mapTileNumber[gp.currentMap][ leftCol][ bottomRow];
                break;

            case "right":
                rightCol = (rightX + entity.speed) / gp.tileSize;
                tileA_bg = gp.tileManager.background[gp.currentMap][ rightCol][ topRow];
                tileB_bg = gp.tileManager.background[gp.currentMap][ rightCol][ bottomRow];
                tileA_fg = gp.tileManager.mapTileNumber[gp.currentMap][ rightCol][ topRow];
                tileB_fg = gp.tileManager.mapTileNumber[gp.currentMap][ rightCol][ bottomRow];
                break;

            default:
                // no movement? bail out
                return;
        }

        // 4) If **either** layer’s tile has `collision == true`, block the movement
        if (   gp.tileManager.tile[tileA_bg].collision
                || gp.tileManager.tile[tileB_bg].collision
                || gp.tileManager.tile[tileA_fg].collision
                || gp.tileManager.tile[tileB_fg].collision) {
            entity.collisionOn = true;
        }
    }



}
