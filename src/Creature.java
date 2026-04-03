import java.util.Random;

public class Creature {
    double x, y;
    int energy = 100;
    boolean isAlive = true;

    private static final Random random = new Random();

    public Creature(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (energy <= 0) {
            die();
            return;
        }

        // Move randomly by -10, 0, or 10 in both x and y directions
        double newX = this.x + (random.nextInt(3) - 1) * 10;
        double newY = this.y + (random.nextInt(3) - 1) * 10;

        // Ensure the new position is within bounds
        if (newX >= 0 && newX <= World.WORLD_WIDTH) {
            this.x = newX;
        }
        if (newY >= 0 && newY <= World.WORLD_HEIGHT) {
            this.y = newY;
        }
    }

    public boolean collidesWith(Creature other) {
        return this != other && this.x == other.x && this.y == other.y;
    }

    public void eatGrass(Grass grass) {
        // Placeholder for future functionality
    }

    public void die() {
        isAlive = false;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}