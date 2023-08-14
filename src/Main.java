import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        //Stops the program when leaving the game
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Can change if you want to change to fullscreen or not
        window.setResizable(false);
        //Name of title
        window.setTitle("Once Upon a Slime");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}