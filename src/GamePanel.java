import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
public class GamePanel extends JPanel implements Runnable {

    public Main w;
    //For the jumping
    public double Gravity = 4;

    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    //Setting the FPS to 60 Frames
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set player's default position;
    double slimeX = 100;
    double slimeY = 325;
    double slimeSpeed = 4;
    int heightLimit = 500;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //This is to update the information such as character positions.
                update();
                // Draw the screen with the updated information
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            slimeY -= slimeSpeed;
            this.tick();
        }

    }

    public void tick() {

        if (keyH.vely < Gravity) {
            keyH.vely += 0.01;
        }
        if (slimeY + keyH.vely < 325.0) {
            slimeY += keyH.vely;
        } else {
            keyH.vely = 0.0;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        //Box to where the thing is on the screen
        g2.fillRect((int)slimeX, (int)slimeY, tileSize, tileSize);


        //Not necessary but saves on memory
        g2.dispose();


    }
}