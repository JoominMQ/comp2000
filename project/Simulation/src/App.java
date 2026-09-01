import java.awt.*;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
        int width = 800;
        int height = 800;

        // create light objects
        Lights lightLeft = new Lights(290, 300, 10, 200);
        Lights lightRight = new Lights(500, 300, 10, 200);
        Lights lightTop = new Lights(300, 290, 200, 10);
        Lights lightBottom = new Lights(300, 500, 200, 10);

        JFrame frame = new JFrame("Traffic Lights");

        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Roads
                g.setColor(Color.GRAY);
                g.fillRect(0, 300, width, 200);
                g.fillRect(300, 0, 200, height);

                // Line dividers
                g.setColor(Color.BLACK);
                g.drawLine(0, height / 2, width, height / 2);
                g.drawLine(width / 2, 0, width / 2, height);

                // Draw lights
                lightLeft.spawnLight(g);
                lightRight.spawnLight(g);
                lightTop.spawnLight(g);
                lightBottom.spawnLight(g);
            }
        };

        // Animation timer
        Timer timer = new Timer(16, e -> {
            
            // Change lights here
            lightLeft.changeLight(1);
            lightRight.changeLight(1);
            lightTop.changeLight(3);
            lightBottom.changeLight(3);

            // Redraw
            panel.repaint();
        });

        timer.start();

        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}