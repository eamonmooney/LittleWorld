import java.util.Random;

public class Creature {
    int x, y;
    int energy;

    private static final Random rand = new Random();

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        this.energy = 20;
    }

    public void update(World world) {
        move(world);
        energy--;

        Tile tile = world.getTile(x, y);

        // Eat food
        if (tile.type == TileType.FOOD) {
            energy += 10;
            tile.type = TileType.EMPTY;
        }
    }

    private void move(World world) {
        int dx = rand.nextInt(3) - 1; // -1, 0, 1
        int dy = rand.nextInt(3) - 1;

        int newX = x + dx;
        int newY = y + dy;

        if (world.isInside(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public boolean isDead() {
        return energy <= 0;
    }
}