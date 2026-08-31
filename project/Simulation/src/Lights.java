import java.awt.Color;
import java.awt.Graphics;

public class Lights {
    private int x;
    private int y;
    private int width;
    private int height;
    private int colourMode;

    private Graphics g;

    public Lights(Graphics g, int ecks, int why, int with, int hite) {
        this.g = g;
        this.x = ecks;
        this.y = why;
        this.width = with;
        this.height = hite;
        this.colourMode = 1; // Start green
    }

    public void spawnLight() {
        if (colourMode == 1) {
            g.setColor(Color.GREEN);
        }
        else if (colourMode == 2) {
            g.setColor(Color.YELLOW);
        }
        else if (colourMode == 3) {
            g.setColor(Color.RED);
        }

        g.fillRect(x, y, width, height);
    }

    public void changeLight(int type) {
        this.colourMode = type;
        
    }

    public int getColour() {
        return colourMode;
    }


}