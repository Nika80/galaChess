package com.example.galachess;

import com.example.galachess.Figures.Figure;
import com.example.galachess.Game.CustomButton;
import com.example.galachess.Game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class HelloApplication extends Application {

    private static final int BOARD_SIZE = 10;

    private Game game;
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        paint();
        Scene scene = new Scene(this.gridPane, 500, 500);
        primaryStage.setTitle("Chessboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void paint() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                CustomButton button;
                Figure figure = game.getCell(row, col);
                GridPane pane = new GridPane();
                switch (figure.getType()) {
                    case KING:
                        button = new CustomButton("K");
                        break;
                    case PAWN:
                        button = new CustomButton("P");
                        break;
                    case ELEPHANT:
                        button = new CustomButton("E");
                        break;
                    case ROOK:
                        button = new CustomButton("R");
                        break;
                    default:
                        button = new CustomButton();
                        break;
                }
                button.setRowCol(row, col);
                button.setStyle("-fx-background-color: " + getCellColor(row, col) + "; -fx-text-fill: " + figure.getStringColor());
                button.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                button.setPrefSize(50, 50);
                button.setOnAction(e -> {
                    int resp = this.game.move(button.getRow(), button.getCol());
                    if (resp == 1) {
                        paint();
                    }
                });
                pane.getChildren().add(button);
                this.gridPane.add(pane, col, row);
            }
        }
    }

    // Определение цвета ячейки доски
    private String getCellColor(int row, int col) {
        if ((row + col) % 2 == 0) {
            return "red";
        } else {
            return "orange";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}