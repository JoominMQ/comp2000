public abstract class Objects {

    // x y
    protected float x;
    protected float y;

    public Objects(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // get position
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // set position
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}