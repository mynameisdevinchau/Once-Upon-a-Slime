package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] background, mapTileNumber;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        // i'll adjust this value later. there's only 4 tiles so far
        tile = new Tile[5];
        background    = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadAllMaps();
    }

    private void loadAllMaps() {
        for (int i = 0; i < gp.maxMap; i++) {
            loadMap(String.format("/maps/background%d", i), background,    i);
            loadMap(String.format("/maps/foreground%d", i), mapTileNumber, i);
        }
    }

    private InputStream openStream(String resourcePath) {
        InputStream is = getClass().getResourceAsStream(resourcePath);
        if (is != null) return is;
        try {
            return new FileInputStream("res" + resourcePath);
        } catch (FileNotFoundException e) {
            System.err.println("Missing resource: " + resourcePath);
            return null;
        }
    }

    public void loadMap(String filePath, int[][][] mapArray, int mapIndex) {
        InputStream is = openStream(filePath);
        if (is == null) return;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) break;
                String[] nums = line.trim().split("\\s+");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    mapArray[mapIndex][col][row] =
                            (col < nums.length) ? Integer.parseInt(nums[col]) : 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        for (int i = 0; i < tile.length; i++) {
            String path = String.format("/tiles/tile%d.png", i);
            InputStream is = openStream(path);
            if (is == null) continue;

            try {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(is);
                // —— mark solid tiles so collisionChecker sees them

                //add more to this if statement later
                if (i == 1 || i == 4 || i == 3) {
                    tile[i].collision = true;
                }

            } catch (IOException e) {
                System.err.println("Error loading tile image: " + path);
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int worldX  = col * gp.tileSize;
                int worldY  = row * gp.tileSize;
                int screenX = worldX - gp.cameraX;
                int screenY = worldY - gp.cameraY;

                if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
                        screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {

                    // draw background
                    int bg = background[gp.currentMap][col][row];
                    if (bg >= 0 && bg < tile.length && tile[bg] != null) {
                        g2.drawImage(tile[bg].image,
                                screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }

                    // draw foreground
                    int fg = mapTileNumber[gp.currentMap][col][row];
                    if (fg > 0 && fg < tile.length && tile[fg] != null) {
                        g2.drawImage(tile[fg].image,
                                screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}
