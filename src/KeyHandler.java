import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public double vely = 0.0;
    public double jumpVelocity = 4.0;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            vely +=0.5;

            if (vely == 300.0){
                upPressed = false;
                System.out.print("False");
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    /*    int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }*/
    }
}
