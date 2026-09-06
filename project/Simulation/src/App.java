import java.awt.*;
import javax.swing.*;




public class App {

    static float worldTimeElapsed = 0;
    static boolean paused = false;
    static LightManager trafficLights;
    // Cars List and spawn timer
    static private VehicleManager vehicleManager;

    public static void main(String[] args) {

        int width = 800;
        int height = 800;

        trafficLights = new LightManager();
        vehicleManager = new VehicleManager(trafficLights);

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

                vehicleManager.draw(g);
                
            }
        };
        pauseButtonSetup(frame, panel);

        long[] lastTime = { System.nanoTime() };
        long startTime = System.currentTimeMillis();
        
        // timer for animation
        Timer timer = new Timer(16, e -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime[0]) / 1_000_000_000.0; // Convert to seconds
            lastTime[0] = currentTime; // Update lastTime for the next frame

            worldTimeElapsed = (int) (System.currentTimeMillis() - startTime);

            if (paused) {
                return; // Skip updating if paused
            }

            // Manages cars
            vehicleManager.update(deltaTime, worldTimeElapsed);

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



    public static void pauseButtonSetup(JFrame frame, JPanel panel) {
    frame.add(panel);
    
    // make overlay
    panel.setLayout(new GridBagLayout());
    
    // make pause button
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener(e -> {
        paused = !paused;
        pauseButton.setText(paused ? "Resume" : "Pause");
        panel.repaint();
    });

    // make bottom-left corner
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.SOUTHWEST;
    gbc.insets = new Insets(10, 10, 10, 10); // 10px margin from bottom-left edges

    panel.add(pauseButton, gbc);
    }
}