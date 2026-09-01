import java.awt.Color;
import java.awt.Graphics;

public class Lights {
    private int x;
    private int y;
    private int width;
    private int height;
    private int colourMode;

    public Lights(int ecks, int why, int with, int hite) {
        this.x = ecks;
        this.y = why;
        this.width = with;
        this.height = hite;
        this.colourMode = 1; // Start green
    }

    public void spawnLight(Graphics g) {
        if (colourMode == 1) {
            g.setColor(Color.GREEN);
        }
        else if (colourMode == 2) {
            g.setColor(Color.ORANGE);
        }
        else if (colourMode == 3) {
            g.setColor(Color.RED);
        }

        g.fillRect(x, y, width, height);
    }

    public void changeLight(int type) {
        if (type < 1 || type > 3) {
            throw new IllegalArgumentException("Invalid light colour");
        }
        this.colourMode = type;
    }

    public int getColour() {
        return colourMode;
    }


}