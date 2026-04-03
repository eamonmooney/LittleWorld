import java.util.Random;

public class Creature {
    public double x, y;
    private static final Random random = new Random();

    public Creature(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        // Move randomly by -10, 0, or 10 in both x and y directions
        this.x += (random.nextInt(3) - 1) * 10;
        this.y += (random.nextInt(3) - 1) * 10;
    }
}