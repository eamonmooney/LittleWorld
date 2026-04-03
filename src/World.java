import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private List<Creature> creatures;
    private static final double WORLD_WIDTH = 600; // Match the scene width
    private static final double WORLD_HEIGHT = 400; // Match the scene height
    private static final Random random = new Random();

    public World() {
        creatures = new ArrayList<>();
        // Spawn creatures at random positions without overlap
        for (int i = 0; i < 5; i++) {
            boolean validPosition = false;
            double x = 0, y = 0;

            while (!validPosition) {
                x = random.nextDouble() * WORLD_WIDTH;
                y = random.nextDouble() * WORLD_HEIGHT;

                double finalX = x;
                double finalY = y;
                validPosition = creatures.stream()
                    .noneMatch(c -> c.x == finalX && c.y == finalY);
            }

            creatures.add(new Creature(x, y, WORLD_WIDTH, WORLD_HEIGHT));
        }
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void moveCreatures() {
        for (Creature creature : creatures) {
            boolean validMove = false;
            double oldX = creature.x;
            double oldY = creature.y;

            while (!validMove) {
                creature.move();

                // Check for collisions
                boolean collision = creatures.stream()
                    .anyMatch(other -> creature.collidesWith(other));

                if (!collision) {
                    validMove = true;
                } else {
                    // Revert to old position and retry
                    creature.x = oldX;
                    creature.y = oldY;
                }
            }
        }
    }

    public double getWidth() {
        return WORLD_WIDTH;
    }

    public double getHeight() {
        return WORLD_HEIGHT;
    }
}