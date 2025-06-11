package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // the world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public final int maxMap = 1;
    public int currentMap = 0;

    // camera offsets
    public int cameraX = 0;
    public int cameraY = 0;

    //entities and objects will be added here
    /*
    public Entity obj[][] = new Entity[maxMap][20]
    ...
     */

    public SuperObject[] obj = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);

    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        assetSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();

        //center camera on player
        cameraX = player.worldX - (screenWidth / 2 - tileSize / 2);
        cameraY = player.worldY - (screenHeight / 2 - tileSize / 2);

        //clamp camera so it never shows outside world
        if (cameraX < 0) cameraX = 0;
        if (cameraY < 0) cameraY = 0;
        int maxCamX = worldWidth - screenWidth;
        int maxCamY = worldHeight - screenHeight;
        if (cameraX > maxCamX) cameraX = maxCamX;
        if (cameraY > maxCamY) cameraY = maxCamY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. draw tiles
        tileManager.draw(g2);

        // 2. draw objects
        for (SuperObject object : obj) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

        // 3. draw player
        player.draw(g2);

        g2.dispose();
    }

}

