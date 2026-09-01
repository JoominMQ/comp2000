import java.awt.*;

public class Car {
    float x;
    float y;
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

    public void move(double deltaSeconds) {
        System.out.println("Moving car by " + (speed * deltaSeconds) + " pixels" + " at speed " + speed + " pixels/second" + " with deltaSeconds " + deltaSeconds);
        x += (speed * deltaSeconds);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }


}
