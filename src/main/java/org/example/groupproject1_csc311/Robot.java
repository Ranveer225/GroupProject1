package org.example.groupproject1_csc311;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Robot {
    private double x;
    private double y;
    private Image robotImage;

    public Robot(double x, double y) {
        this.x = x;
        this.y = y;
        this.robotImage = new Image("path/to/robot.png");
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(robotImage, x, y);
    }
}

