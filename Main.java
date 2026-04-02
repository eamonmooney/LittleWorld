public class Main {
    public static void main(String[] args) throws InterruptedException {
        World world = new World(20, 20, 10);

        int tick = 0;

        while (true) {
            world.update();
            world.printStats(tick);

            Thread.sleep(200); // slow it down
            tick++;
        }
    }
}