import java.awt.*;
import javax.swing.*;

public class App {

    static int worldTimeElapsed = 0;

    static lightManager trafficLights;
    static Car car1;

    public static void main(String[] args) {

        int width = 800;
        int height = 800;

        trafficLights = new lightManager();

        // Create car
        car1 = new Car(0, 390, 40, 20, 3, Color.RED);

        JFrame frame = new JFrame("Traffic Sim");

        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Roads
                g.setColor(Color.GRAY);
                g.fillRect(0, 300, width, 200);
                g.fillRect(300, 0, 200, height);

                // line dividers
                g.setColor(Color.BLACK);
                g.drawLine(0, height / 2, width, height / 2);
                g.drawLine(width / 2, 0, width / 2, height);

                // draw traffic lights
                trafficLights.draw(g);

                // show time
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString(
                    "Time elapsed: " + worldTimeElapsed / 1000 + "s",
                    20,
                    40
                );

                car1.draw(g);
            }
        };

        // timer for animation
        Timer timer = new Timer(16, e -> {


            worldTimeElapsed += 16;

            car1.move();

            // i dont like alex
            trafficLights.update();

            panel.repaint();
        });

        timer.start();

        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}