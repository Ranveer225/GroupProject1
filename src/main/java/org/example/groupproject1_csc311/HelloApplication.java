package org.example.groupproject1_csc311;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Title: Group Project 1
 * Description:
 * @author Cody Bandrowski, Other Authors
 */
public class HelloApplication extends Application {
    private Robot robot;

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        Image mazeImage = new Image("path/to/maze.png");
        gc.drawImage(mazeImage, 0, 0);


        robot = new Robot(50, 50);
        robot.draw(gc);


        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 600, 600);


        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> robot.move(0, -5);
                case DOWN -> robot.move(0, 5);
                case LEFT -> robot.move(-5, 0);
                case RIGHT -> robot.move(5, 0);
            }


            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(mazeImage, 0, 0);
            robot.draw(gc);  // Redraw robot
        });

        stage.setTitle("Maze Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}