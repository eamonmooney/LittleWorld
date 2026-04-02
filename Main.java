public class Main {
    public static void main(String[] args) throws InterruptedException {
        World world = new World(20, 20, 10);

        int tick = 0;

        while (true) {
            world.update();
            world.printWorld();
            world.printStats(tick);
            System.out.println("------------------");

            Thread.sleep(500); // slow it down
            tick++;
        }
    }
}