package org.example.groupproject1_csc311;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class Robot {
    private ImageView imageView;
    public Robot(double x, double y) {
        Image robotImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/robot.png")));
        imageView = new ImageView(robotImage);
        imageView.setX(x);
        imageView.setY(y);
    }
    public ImageView getImageView() {
        return imageView;
    }
    public double getX() {
        return imageView.getX();
    }

    public double getY() {
        return imageView.getY();
    }
    public void setPosition(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }
}

