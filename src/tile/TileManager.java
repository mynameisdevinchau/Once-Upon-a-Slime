package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;


    //used to finish the entire map but i want to split it up to background and foreground

    //using mapTileNumber as the foreground of the world
    public int[][] mapTileNumber;
    public int[][] background;

    public TileManager(GamePanel gp){
        this.gp = gp;

        //10 types of background. we can change as needed
        tile = new Tile[10];


        //layering this on top. going to put it behind mapTileNumber

        background = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();

        //we'll layer the map first with the background
        loadMap("/maps/background", background);

        //foreground. need to fix file names for them to be more organized
        loadMap("/maps/foreground", mapTileNumber);
    }

    public void loadMap(String filePath, int[][] mapTileNumber) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < gp.maxWorldRow) {

                String line = br.readLine();

                // trim and split on all whitespace
                System.out.println(filePath);
                System.out.println(line);
                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                }
                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getTileImage() {
        try {

            //i'll need to adjust this for later

            //open path
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile0.png"));

            //floor
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/snow_biome/snow.png")); //might need to fix the file name
            tile[1].collision = true;

            //bush tiles
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile3.png"));

            //penguins!
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/snow_biome/penguin_right.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                int worldX  = worldCol * gp.tileSize;
                int worldY  = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                //only draw tiles inside the camera view
                if (worldX + gp.tileSize  > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY) {

                    //draw the background tile
                    int bgNum = background[worldCol][worldRow];
                    if (bgNum >= 0 && tile[bgNum] != null) {
                        g2.drawImage(tile[bgNum].image,
                                screenX, screenY,
                                gp.tileSize, gp.tileSize,
                                null);
                    }

                    //draw the foreground tile
                    int fgNum = mapTileNumber[worldCol][worldRow];
                    // for example, use -1 or 0 to mean “no foreground”
                    if (fgNum > 0 && fgNum < tile.length && tile[fgNum] != null) {
                        g2.drawImage(tile[fgNum].image,
                                screenX, screenY,
                                gp.tileSize, gp.tileSize,
                                null);
                    }
                }
            }
        }
    }

}
