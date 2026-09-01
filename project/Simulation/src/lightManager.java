import java.awt.Graphics;

public class lightManager {

    private Lights lightLeft;
    private Lights lightRight;
    private Lights lightTop;
    private Lights lightBottom;

    private int state;
    private int elapsedTime;

    public lightManager() {

        // Create lights
        lightLeft = new Lights(290, 300, 10, 200);
        lightRight = new Lights(500, 300, 10, 200);
        lightTop = new Lights(300, 290, 200, 10);
        lightBottom = new Lights(300, 500, 200, 10);

        // Starting state
        state = 1;
        elapsedTime = 0;

        // Set initial light state
        setLights();
    }

    public void update() {

        elapsedTime += 16;

        // Change state based on time
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

        // Update lights
        setLights();
    }

    private void setLights() {

        try {

            switch (state) {

                // Left and right green
                case 1:
                    lightLeft.changeLight(1);
                    lightRight.changeLight(1);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                // Left and right yellow
                case 2:
                    lightLeft.changeLight(2);
                    lightRight.changeLight(2);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                // All red
                case 3:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;

                // Top and bottom green
                case 4:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(1);
                    lightBottom.changeLight(1);
                    break;

                // Top and bottom yellow
                case 5:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(2);
                    lightBottom.changeLight(2);
                    break;

                // All red
                case 6:
                    lightLeft.changeLight(3);
                    lightRight.changeLight(3);
                    lightTop.changeLight(3);
                    lightBottom.changeLight(3);
                    break;
            }

        }
        catch (IllegalArgumentException e) {
            System.out.println(
                "Traffic light error: " + e.getMessage()
            );
        }
    }

    public void draw(Graphics g) {

        lightLeft.spawnLight(g);
        lightRight.spawnLight(g);
        lightTop.spawnLight(g);
        lightBottom.spawnLight(g);
    }

    public int getState() {
        return state;
    }
}