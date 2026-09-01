import java.awt.*;

public class Car {
    int x;
    int y;
    int width;
    int height;
    int speed;
    Color color;

    public Car(int x, int y, int width, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public void move() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }


}
