import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int width = 800;
        int height = 800;

        JFrame frame = new JFrame("Test stringing nows");

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.GRAY);
                g.fillRect(0, 300, width, 200);     // horizontal
                g.fillRect(300, 0, 200, height);    // vertical
                g.setColor(Color.green);
                //g.fillRect(290, 300, 10, 200);// left
                //g.fillRect(500, 300, 10, 200);// right
                //g.fillRect(300, 290, 200, 10);// left
                //g.fillRect(300, 500, 200, 10);// right
                
                Lights light = new Lights(g, 290, 300, 10, 200);
                light.changeLight(3);
                light.spawnLight();
                repaint();
                
            }
        };

        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}