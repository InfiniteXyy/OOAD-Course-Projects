package chapter01;

public class Ant {
    private int velocity;
    private int position;
    private boolean isAlive;

    public Ant(int velocity, int position) {
        this.velocity = velocity;
        this.position = position;
        this.isAlive = true;
    }

    public int creep() {
        this.position += this.velocity;
        return this.position;
    }

    public void collide() {
        this.velocity = -this.velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getPosition() {
        return position;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
