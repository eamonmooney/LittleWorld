public class Tile {
    TileType type;
    int foodTimer; // controls regrowth

    public Tile(TileType type) {
        this.type = type;
        this.foodTimer = 0;
    }

    public void update() {
        if (type == TileType.EMPTY) {
            foodTimer++;
            if (foodTimer > 20) { // regrow food
                type = TileType.FOOD;
                foodTimer = 0;
            }
        }
    }
}