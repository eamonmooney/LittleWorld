import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Creature> creatures;

    public World() {
        creatures = new ArrayList<>();
        // spawn a few creatures at random positions
        creatures.add(new Creature(100, 100));
        creatures.add(new Creature(200, 150));
        creatures.add(new Creature(300, 200));
    }

    public List<Creature> getCreatures() {
        return creatures;
    }
}