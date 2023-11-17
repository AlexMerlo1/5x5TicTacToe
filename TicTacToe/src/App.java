import scenes.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of GetStartedApp
        Welcome getStartedApp = new Welcome();

        // Start the GetStartedApp
        getStartedApp.start(primaryStage);

        // Show the stage
        primaryStage.show();
    }
}
