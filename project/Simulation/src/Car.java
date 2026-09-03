// Car.java manages the properties of a car in the simulation, including its position, speed, direction, 
// -and handles the drawing of the car on the screen.

import java.awt.*;
import java.awt.geom.*;;

public class Car extends Vehicle {    
    private float x;
    private float y;
    private int radius;
    private int speed;
    private Color color;
    private  float directionRadians;  // 0 = facing +x, increases counter-clockwise
    private float acceleration = 0; // accel and decel, pixels per second squared

    public Car(int x, int y, int radius, int speed, float directionRadians, Color color) {
        super(x, y);

        if (radius <= 0 || speed < 0) {
            throw new IllegalArgumentException("Radius and speed must be positive values.");
        }
        
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speed = speed;
        this.color = color;
        this.directionRadians = directionRadians;
    }

    @Override
    public void move(double deltaSeconds) {
        x += (float) (Math.cos(directionRadians) * speed * deltaSeconds);
        y += (float) (Math.sin(directionRadians) * speed * deltaSeconds);
    }

    @Override
    public void draw(Graphics g) {
        checkCollision();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Float(x - radius, y - radius, 2 * radius, 2 * radius)); // draw the car as a circle
        
        float endX = (float) (x + Math.cos(directionRadians) * radius);
        float endY = (float) (y + Math.sin(directionRadians) * radius);
        g2d.setColor(Color.BLACK);
        g2d.drawLine((int) x, (int) y, (int) endX, (int) endY);
    }

    private void checkCollision() {
        for (Car other : App.getCars()) {
            if (other != this && this.isCollidingWithCar(other)) {
                this.setSpeed(0);
                return;
            }
        }
    }



// Getters and Setters

    public float getDirectionDegrees() {
        return (float) Math.toDegrees(directionRadians);
    }
    public float getDirectionRadians() {
        return directionRadians;
    }
    public void setDirectionRadians(float degrees) {
        this.directionRadians = (float) Math.toRadians(degrees);
    }

    public int getRadius() {
        return radius;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed must be a non-negative value.");
        }
        this.speed = speed;
    }

    public float getAcceleration() {
        return acceleration;
    }


// Helper methods

    public boolean isCollidingWithCar(Car other) {
        float distance = this.distanceToVehicle(other);
        return distance < (this.getRadius() + other.getRadius()); // Check if distance is less than the sum of the radii
    }
}
