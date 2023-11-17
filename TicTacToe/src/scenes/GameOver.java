package scenes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOver extends Application {

    private String winnerSymbol;

    public void setWinnerSymbol(String symbol) {
        this.winnerSymbol = symbol;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TicTacToe Game Over");

        // Create a button
        Button startButton = new Button("Play Again!");
        startButton.setPrefSize(150, 50);
        startButton.setId("startButton");

        // Switch scenes when the button is clicked
        startButton.setOnAction(e -> {
            // Create an instance of the Game class
            Game game = new Game();

            // Get the current stage from the button
            Stage currentStage = (Stage) startButton.getScene().getWindow();

            // Call the start method of the Game class to display the new scene
            game.start(currentStage);
        });

        // Display winner information
        Text winnerText = new Text("Player " + winnerSymbol + " wins!");
        winnerText.setId("welcomeText");

        // Create a VBox to stack winner text at the top and the button at the bottom
        VBox root = new VBox(winnerText, startButton);
        root.setAlignment(Pos.TOP_CENTER);

        // Set the top margin to position
        VBox.setMargin(winnerText, new Insets(100, 0, 0, 0));
        VBox.setMargin(startButton, new Insets(300, 0, 0, 0));

        // Set background color
        root.setStyle("-fx-background-color: lightblue;");

        // Create the scene
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
}
