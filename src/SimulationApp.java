import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        World world = new World();
        Pane root = new Pane();

        // Add a rectangle to represent the world's border
        Rectangle border = new Rectangle(0, 0, world.getWidth(), world.getHeight());
        border.setStroke(Color.BLACK);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeWidth(2);
        root.getChildren().add(border);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            javafx.application.Platform.runLater(() -> {
                if (!Toggles.PAINT_MODE) {
                    root.getChildren().removeIf(node -> node instanceof Circle || node instanceof Rectangle && node != border);
                }

                // Render grass
                root.getChildren().removeIf(node -> node instanceof Rectangle && node != border);
                for (Grass g : world.getGrass()) {
                    Rectangle grassRect = new Rectangle(g.x, g.y, 5, 5);
                    grassRect.setFill(Color.GREEN);
                    root.getChildren().add(grassRect);
                }

                // Render creatures
                world.moveCreatures();
                for (Creature c : world.getCreatures()) {
                    Circle circle = new Circle(c.x, c.y, 10.0, Color.BLUE);
                    root.getChildren().add(circle);
                }
            });
        }, 0, 200, TimeUnit.MILLISECONDS);

        Scene scene = new Scene(root, world.getWidth(), world.getHeight());
        primaryStage.setTitle("LittleWorld Simulation");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> executor.shutdown());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}