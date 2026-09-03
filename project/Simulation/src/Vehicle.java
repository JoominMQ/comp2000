import java.awt.*;

public abstract class Vehicle {

    private float x;
    private float y;

    public Vehicle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move(double deltaSeconds);
    public abstract void draw(Graphics g);

    public float[] getPosition() {
        return new float[]{x, y};
    }


    public float distanceToVehicle(Vehicle other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

}