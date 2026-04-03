import java.util.Random;

public class Creature {
    public double x, y;
    private static final Random random = new Random();
    private double worldWidth, worldHeight;

    public Creature(double x, double y, double worldWidth, double worldHeight) {
        this.x = x;
        this.y = y;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public void move() {
        // Move randomly by -10, 0, or 10 in both x and y directions
        double newX = this.x + (random.nextInt(3) - 1) * 10;
        double newY = this.y + (random.nextInt(3) - 1) * 10;

        // Ensure the new position is within bounds
        if (newX >= 0 && newX <= worldWidth) {
            this.x = newX;
        }
        if (newY >= 0 && newY <= worldHeight) {
            this.y = newY;
        }
    }
}