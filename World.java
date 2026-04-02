import java.util.*;

public class World {
    int width, height;
    Tile[][] tiles;
    List<Creature> creatures;

    public World(int width, int height, int creatureCount) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];
        creatures = new ArrayList<>();

        initTiles();
        initCreatures(creatureCount);
    }

    private void initTiles() {
        Random rand = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TileType type = rand.nextDouble() < 0.2
                        ? TileType.FOOD
                        : TileType.EMPTY;

                tiles[x][y] = new Tile(type);
            }
        }
    }

    private void initCreatures(int count) {
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            creatures.add(new Creature(
                    rand.nextInt(width),
                    rand.nextInt(height)
            ));
        }
    }

    public void update() {
        // Update tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y].update();
            }
        }

        // Update creatures
        Iterator<Creature> it = creatures.iterator();
        while (it.hasNext()) {
            Creature c = it.next();
            c.update(this);

            if (c.isDead()) {
                it.remove();
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void printStats(int tick) {
        System.out.println("Tick: " + tick +
                " | Creatures: " + creatures.size());
    }
}