import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        World world = new World();

        Pane root = new Pane();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().clear();
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