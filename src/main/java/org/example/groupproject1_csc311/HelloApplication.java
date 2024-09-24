package org.example.groupproject1_csc311;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.util.Objects;

public class HelloApplication extends Application {
    private Image mazeImage1;
    private Image mazeImage2;
    private Car car;
    private Pane mazePane1;
    private Pane mazePane2;
    private boolean autoMoveEnabled = false;
    private Timeline autoMoveTimeline;

    @Override
    public void start(Stage primaryStage) {
        mazeImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/maze.png")));
        mazeImage2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/maze2.png")));
        car = new Car(50, 50);

        mazePane1 = createMazePane(mazeImage1, car);
        mazePane2 = createMazePane(mazeImage2, new Car(50, 50));

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Maze 1", mazePane1);
        Tab tab2 = new Tab("Maze 2", mazePane2);
        tabPane.getTabs().addAll(tab1, tab2);

        Scene scene = new Scene(tabPane, mazeImage1.getWidth(), mazeImage1.getHeight());
        scene.setOnKeyPressed(this::handleKeyPress);
        primaryStage.setTitle("Maze Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        setupAutoMovement();
    }
    private Pane createMazePane(Image mazeImage, Car car) {
        Canvas canvas = new Canvas(mazeImage.getWidth(), mazeImage.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(mazeImage, 0, 0);
        Pane pane = new Pane();
        pane.getChildren().addAll(canvas, car.getCarShape());
        Button resetButton = new Button("Reset Car");
        resetButton.setOnAction(e -> {
            car.setPosition(50, 50);
        });
        resetButton.setLayoutX(10);
        resetButton.setLayoutY(10);
        pane.getChildren().add(resetButton);
        return pane;
    }
    private void handleKeyPress(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case W -> moveCar(0, -10);
            case S -> moveCar(0, 10);
            case A -> moveCar(-10, 0);
            case D -> moveCar(10, 0);
            case SPACE -> toggleAutoMove();
        }
    }

    private void moveCar(int dx, int dy) {
        PixelReader pixelReader = getCurrentMaze().getPixelReader();
        double nextX = car.getX() + dx;
        double nextY = car.getY() + dy;
        if (nextX >= 0 && nextX < getCurrentMaze().getWidth() && nextY >= 0 && nextY < getCurrentMaze().getHeight()) {
            Color color = pixelReader.getColor((int) nextX, (int) nextY);
            if (!color.equals(Color.BLACK)) {
                car.setPosition(nextX, nextY);
                if (dx != 0 || dy != 0) {
                    car.setRotation(Math.toDegrees(Math.atan2(dy, dx)));
                }
            }
        }
    }
    private Image getCurrentMaze() {
        return mazeImage1;
    }
    private void setupAutoMovement() {
        autoMoveTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (autoMoveEnabled) {
                moveCar(10, 0);
            }
        }));
        autoMoveTimeline.setCycleCount(Timeline.INDEFINITE);
    }
    private void toggleAutoMove() {
        autoMoveEnabled = !autoMoveEnabled; // Toggle the flag
        if (autoMoveEnabled) {
            autoMoveTimeline.play();
        } else {
            autoMoveTimeline.stop();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
