import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        World world = new World();

        Pane root = new Pane();
        for (Creature c : world.getCreatures()) {
            Circle circle = new Circle(c.x, c.y, 10, Color.BLUE);
            root.getChildren().add(circle);
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("LittleWorld Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}