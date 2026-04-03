import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {
    private List<Creature> creatures;
    private List<Grass> grass;
    private static final double WORLD_WIDTH = 600; 
    private static final double WORLD_HEIGHT = 400; 
    private static final int GRASS_SPAWN_INTERVAL = 10;
    private static final double GRASS_EAT_RADIUS = 10.0;
    private static final Random random = new Random();

    private int moveCounter = 0;

    public World() {
        creatures = new ArrayList<>();
        grass = new CopyOnWriteArrayList<>();
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

    public List<Grass> getGrass() {
        return grass;
    }

    public void moveCreatures() {
        moveCounter++;
        for (Creature creature : creatures) {
            boolean validMove = false;
            double oldX = creature.x;
            double oldY = creature.y;

            while (!validMove) {
                creature.move();

                // Check for collisions with other creatures only
                boolean collision = creatures.stream()
                    .filter(other -> other != creature) // Exclude itself
                    .anyMatch(other -> creature.collidesWith(other));

                if (!collision) {
                    validMove = true;
                } else {
                    // Revert to old position and retry
                    creature.x = oldX;
                    creature.y = oldY;
                }
            }

            // Check for grass overlap with a radius
            grass.removeIf(grass -> {
                double distance = Math.sqrt(Math.pow(creature.x - grass.x, 2) + Math.pow(creature.y - grass.y, 2));
                if (distance <= GRASS_EAT_RADIUS) {
                    creature.eatGrass(grass);
                    return true;
                }
                return false;
            });
        }

        // Spawn grass periodically
        if (moveCounter % GRASS_SPAWN_INTERVAL == 0) {
            spawnGrass();
        }
    }

    private void spawnGrass() {
        double x = random.nextDouble() * WORLD_WIDTH;
        double y = random.nextDouble() * WORLD_HEIGHT;
        grass.add(new Grass(x, y));
    }

    public double getWidth() {
        return WORLD_WIDTH;
    }

    public double getHeight() {
        return WORLD_HEIGHT;
    }
}