import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().removeIf(node -> node instanceof Circle);
                world.moveCreatures();
                for (Creature c : world.getCreatures()) {
                    Circle circle = new Circle(c.x, c.y, 10.0, Color.BLUE);
                    root.getChildren().add(circle);
                }
            }
        };
        timer.start();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("LittleWorld Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}