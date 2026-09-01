import java.awt.*;
import javax.swing.*;

public class App {

    static int state = 1;
    static int elapsedTime = 0;
    static int worldTimeElapsed = 0;

    static Lights lightLeft;
    static Lights lightRight;
    static Lights lightTop;
    static Lights lightBottom;
    static Car car1;

    public static void main(String[] args) {
        int width = 800;
        int height = 800;



        // create light objects
        lightLeft = new Lights(290, 300, 10, 200);
        lightRight = new Lights(500, 300, 10, 200);
        lightTop = new Lights(300, 290, 200, 10);
        lightBottom = new Lights(300, 500, 200, 10);

        // in me mums car
        car1 = new Car(0, 390, 40, 20, 3, Color.RED);


        JFrame frame = new JFrame("Traffic Sim");

        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // roads
                g.setColor(Color.GRAY);
                g.fillRect(0, 300, width, 200);
                g.fillRect(300, 0, 200, height);

                // line dividers
                g.setColor(Color.BLACK);
                g.drawLine(0, height / 2, width, height / 2);
                g.drawLine(width / 2, 0, width / 2, height);

                // draw lights
                lightLeft.spawnLight(g);
                lightRight.spawnLight(g);
                lightTop.spawnLight(g);
                lightBottom.spawnLight(g);

                // show time
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString("Time elapsed: " + worldTimeElapsed / 1000 + "s", 20, 40);

                // draw car
                car1.draw(g);
            }
        };

        // timer for animation
        Timer timer = new Timer(16, e -> {
            worldTimeElapsed += 16;
            elapsedTime += 16;
            
            car1.move();
            lightStates();
            System.out.println(elapsedTime);

            panel.repaint();
        });

        timer.start();

        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    static void lightStates() {

        if (state == 1 && elapsedTime >= 8000) {
            state = 2;
            elapsedTime = 0;
        }

        else if (state == 2 && elapsedTime >= 3000) {
            state = 3;
            elapsedTime = 0;
        }

        else if (state == 3 && elapsedTime >= 3000) {
            state = 4;
            elapsedTime = 0;
        }

        else if (state == 4 && elapsedTime >= 8000) {
            state = 5;
            elapsedTime = 0;
        }

        else if (state == 5 && elapsedTime >= 3000) {
            state = 6;
            elapsedTime = 0;
        }

        else if (state == 6 && elapsedTime >= 3000) {
            state = 1;
            elapsedTime = 0;
        }

 // Set the lights
        try {
            
            switch (state) {

                case 1:
                    lightLeft.changeLight(1);
                    lightRight.changeLight(1);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                case 2:
                    lightLeft.changeLight(2);
                    lightRight.changeLight(2);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                case 3:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                case 4:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(1);
                    lightBottom.changeLight(1);
                    break;

                case 5:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(2);
                    lightBottom.changeLight(2);
                    break;

                case 6:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Traffic light error");
        }

    }

}