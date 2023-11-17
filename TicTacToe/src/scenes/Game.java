package scenes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Game extends Application {
  private boolean isXTurn = true;
  private Button[][] buttons = new Button[5][5];
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TicTacToe Game");

        // Create the game board
        createGameBoard(primaryStage);

        primaryStage.show();
    }

    private void createGameBoard(Stage primaryStage) {
      // Create a Pane
      Pane root = new Pane();

      // Create horizontal lines
      for (int i = 0; i < 4; i++) {
          Line horizontalLine = createHorizontalLine(125, 200 + i * 125);
          root.getChildren().add(horizontalLine);
      }
  
      // Create vertical lines
      for (int i = 0; i < 4; i++) {
          Line verticalLine = createVerticalLine(150 + i * 125, 110);
          root.getChildren().add(verticalLine);
      }
  
      // Create buttons 
      for (int row = 0; row < 5; row++) {
        for (int i = 0; i < 5; i++) {
            Button button = createButton(buttons, 115 + i * 125 + 25, 120 + row * 120, row, i, primaryStage);
            buttons[row][i] = button;
            root.getChildren().add(button);
        }
    }
    
  
      // Create the scene
      Scene scene = new Scene(root, 800, 800);
      scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
      // Set the scene to the stage
      primaryStage.setScene(scene);
    }

    private Line createHorizontalLine(double startX, double startY) {
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX + 550);
        line.setEndY(startY);
        line.setStrokeWidth(20); 
        line.setStroke(javafx.scene.paint.Color.BLACK); // Set the color of the line
        return line;
    }

    private Line createVerticalLine(double startX, double startY) {
        Line line = new Line();
        line.setStartX(startX + 75);
        line.setStartY(startY);
        line.setEndX(startX + 75);
        line.setEndY(startY + 550); 
        line.setStrokeWidth(20);
        line.setStroke(javafx.scene.paint.Color.BLACK); // Set the color of the line
        return line;
    
    }
    private void checkWinner(Button[][] buttons, Stage primaryStage, int row, int col) {
      String currentPlayerSymbol;
      if (isXTurn) {
          currentPlayerSymbol = "X";
      } 
      else {
          currentPlayerSymbol = "O";
      }
  
      // Check horizontally
      int countHorizontal = 1;
      for (int i = 1; i < 5; i++) {
        if (col - i >= 0 && buttons[row][col - i].getText().equals(currentPlayerSymbol)) {
            countHorizontal++;
        } 
        else {
            break;
        }
      }
      for (int i = 1; i < 5; i++) {
          if (col + i < buttons[row].length && buttons[row][col + i].getText().equals(currentPlayerSymbol)) {
              countHorizontal++;
          } 
          else {
              break;
          }
      }
      // Check Vertically
      int countVertical = 1;
      for (int i = 1; i < 5; i++) {
          if (row - i >= 0 && buttons[row - i][col].getText().equals(currentPlayerSymbol)) {
              countVertical++;
          } 
          else {
              break;
          }
      }
      for (int i = 1; i < 5; i++) {
          if (row + i < buttons.length && buttons[row + i][col].getText().equals(currentPlayerSymbol)) {
              countVertical++;
          }
          else {
              break;
          }
      }
      // Check Diagonal 1
      int countDiagonal1 = 1;
      for (int i = 1; i < 5; i++) {
          if (row - i >= 0 && col - i >= 0 && buttons[row - i][col - i].getText().equals(currentPlayerSymbol)) {
              countDiagonal1++;
          } 
          else {
              break;
          }
      }
      
      for (int i = 1; i < 5; i++) {
          if (row + i < buttons.length && col + i < buttons[row].length && buttons[row + i][col + i].getText().equals(currentPlayerSymbol)) {
              countDiagonal1++;
          } 
          else {
              break;
          }
      }
      
      // Check diagonal 2
      int countDiagonal2 = 1;
      for (int i = 1; i < 5; i++) {
          if (row + i < buttons.length && col - i >= 0 && buttons[row + i][col - i].getText().equals(currentPlayerSymbol)) {
              countDiagonal2++;
          } 
          else {
              break;
          }
      }

      for (int i = 1; i < 5; i++) {
          if (row - i >= 0 && col + i < buttons[row].length && buttons[row - i][col + i].getText().equals(currentPlayerSymbol)) {
              countDiagonal2++;
          } 
          else {
              break;
          }
      }

  
      // Check if there are 5 in a row horizontally
      if (countHorizontal >= 5) {
        GameOver gameOver = new GameOver();
        gameOver.setWinnerSymbol(currentPlayerSymbol);
        gameOver.start(primaryStage);
      }
      // Check if there are 5 in a row vertically
      if (countVertical >= 5) {
        GameOver gameOver = new GameOver();
        gameOver.setWinnerSymbol(currentPlayerSymbol);
        gameOver.start(primaryStage);
      }
      //Check if there are 5 in a row diagonally
      if (countDiagonal1 >= 5 || countDiagonal2 >= 5) {
        GameOver gameOver = new GameOver();
        gameOver.setWinnerSymbol(currentPlayerSymbol);
        gameOver.start(primaryStage);
      }

  } 

  private Button createButton(Button[][] buttons, int x, int y, int row, int col, Stage primaryStage) {
    Button button = new Button();
    button.setLayoutX(x);
    button.setLayoutY(y);
    button.setPrefSize(50, 50);

    // Add an event handler to change the text of the clicked button
    button.setOnAction(event -> {
        if (button.getText().isEmpty()) {
            if (isXTurn) {
                button.setId("gameText");
                button.setText("X");
            } 
            else {
                button.setId("gameText");
                button.setText("O");
            }
            // Check for a winner
            checkWinner(buttons, primaryStage, row, col);
            // Change turns after move
            isXTurn = !isXTurn;
        }
    });

    return button;
    }
  
}
