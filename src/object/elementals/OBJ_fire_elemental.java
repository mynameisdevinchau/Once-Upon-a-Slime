package object.elementals;

import object.SuperObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.*;

public class OBJ_fire_elemental extends SuperObject {
    public OBJ_fire_elemental(String name, String imagePath) {
        this.name = name;

        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is == null) {
            try {
                is = new FileInputStream("res/" + imagePath);
                System.out.println("Loaded " + name + " from file: res/" + imagePath);
            } catch (FileNotFoundException fnfe) {
                System.err.println(name + " image not found: " + imagePath);
                return;
            }
        } else {
            System.out.println("Loaded " + name + " from classpath: " + imagePath);
        }

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // collision = true;
    }
    @Override
    public void interact(GamePanel gp) {
        System.out.println(name + ": sup bitch"); // You could later replace this with a dialog box
    }
}
