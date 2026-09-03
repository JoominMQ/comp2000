import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class App {

    static float worldTimeElapsed = 0;

    static lightManager trafficLights;
    // Cars List and spawn timer
    static private List<Car> cars = new ArrayList<>();
    static private int lastCarSpawnTime = 0;
    static private final int CAR_SPAWN_INTERVAL_MS = 2000;

    public static void main(String[] args) {

        int width = 800;
        int height = 800;

        trafficLights = new lightManager();

        // Create cars
        Car new1_car = new Car(0, 340, 20, 50, 0, Color.MAGENTA);
        Car new2_car = new Car(450, 0, 20, 35, (float) (Math.PI / 2), Color.MAGENTA);
        Car new3_car = new Car(740, 440, 20, 40, (float) Math.PI, Color.MAGENTA);
        cars.add(new1_car);
        cars.add(new2_car);
        cars.add(new3_car);

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

                for (Car car : cars) {
                    car.draw(g);
                }
            }
        };
        long[] lastTime = { System.nanoTime() };
        long startTime = System.currentTimeMillis();
        
        // timer for animation
        Timer timer = new Timer(16, e -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime[0]) / 1_000_000_000.0; // Convert to seconds
            lastTime[0] = currentTime; // Update lastTime for the next frame

            worldTimeElapsed = (int) (System.currentTimeMillis() - startTime);

            // Manages cars
            carManager(deltaTime, worldTimeElapsed);


            // i dont like alex // you'll have to deal with it lol
            trafficLights.update();

            panel.repaint();
        });

        timer.start();

        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    public static void carManager(double deltaTime, float worldTimer) {
        for (Car car : cars) {
                car.move(deltaTime);
        }
        if (worldTimer - lastCarSpawnTime >= CAR_SPAWN_INTERVAL_MS) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(4) + 1; // Generates 0-3, then adds 1
            Car newCar = null;
            switch (randomNumber) {
                case 1 -> newCar = new Car(0, 340, 20, 50, 0, Color.CYAN); // West lane
                case 2 -> newCar = new Car(450, 0, 20, 50, (float) (Math.PI / 2), Color.CYAN); // North lane
                case 3 -> newCar = new Car(350, 740, 20, 50, (float) (3 * Math.PI / 2), Color.CYAN); // South lane
                case 4 -> newCar = new Car(740, 440, 20, 50, (float) Math.PI, Color.CYAN); // East lane
            }
            cars.add(newCar);
            lastCarSpawnTime = (int) worldTimer;
        }
    }

    public static List<Car> getCars() {
        return cars;
    }
}