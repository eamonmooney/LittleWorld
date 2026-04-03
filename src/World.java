import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Creature> creatures;
    private static final double WORLD_WIDTH = 600; // Match the scene width
    private static final double WORLD_HEIGHT = 400; // Match the scene height

    public World() {
        creatures = new ArrayList<>();
        // spawn a few creatures at random positions
        creatures.add(new Creature(Math.random(), Math.random(), WORLD_WIDTH, WORLD_HEIGHT));
        creatures.add(new Creature(Math.random(), Math.random(), WORLD_WIDTH, WORLD_HEIGHT));
        creatures.add(new Creature(Math.random(), Math.random(), WORLD_WIDTH, WORLD_HEIGHT));
        creatures.add(new Creature(Math.random(), Math.random(), WORLD_WIDTH, WORLD_HEIGHT));
        creatures.add(new Creature(Math.random(), Math.random(), WORLD_WIDTH, WORLD_HEIGHT));
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void moveCreatures() {
        for (Creature creature : creatures) {
            creature.move();
        }
    }
}