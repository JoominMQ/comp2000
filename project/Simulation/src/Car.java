import java.awt.*;
import java.awt.geom.*;;

public class Car extends Vehicle {
    private float x;
    private float y;
    private int width;
    private int height;
    private int speed;
    private Color color;
    private  float directionRadians;  // 0 = facing +x, increases counter-clockwise

    public Car(int x, int y, int width, int height, int speed, float directionRadians, Color color) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        this.directionRadians = directionRadians;
    }

    @Override
    public void move(double deltaSeconds) {
        System.out.println("Moving car by " + (speed * deltaSeconds) + " pixels" + " at speed " + speed + " pixels/second" + " with deltaSeconds " + deltaSeconds);
        x += (float) (Math.cos(directionRadians) * speed * deltaSeconds);
        y += (float) (Math.sin(directionRadians) * speed * deltaSeconds);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform(); // save current state

        float centerX = x + width / 2f;
        float centerY = y + height / 2f;

        g2d.rotate(directionRadians, centerX, centerY); // rotate around the car's own center
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Float(x, y, width, height));

        g2d.setTransform(originalTransform); // restore, or everything drawn after this is rotated too
    }

    public float getDirectionDegrees() {
        return (float) Math.toDegrees(directionRadians);
    }

    public void setDirectionRadians(float degrees) {
        this.directionRadians = (float) Math.toRadians(degrees);
    }


}
