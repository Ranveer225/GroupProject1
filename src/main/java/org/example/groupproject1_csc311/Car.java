package org.example.groupproject1_csc311;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Car {
    private Polygon carShape;
    private double width = 30;
    private double height = 15;

    public Car(double x, double y) {
        carShape = new Polygon();
        drawCar(x, y);
    }
    private void drawCar(double x, double y) {
        carShape.getPoints().clear();
        carShape.getPoints().addAll(
                x, y,
                x + width, y + height / 2,
                x, y + height
        );
        carShape.setFill(Color.BLUE);
    }
    public Polygon getCarShape() {
        return carShape;
    }
    public double getX() {
        return carShape.getPoints().get(0);
    }
    public double getY() {
        return carShape.getPoints().get(1);
    }
    public void setPosition(double x, double y) {
        drawCar(x, y);
    }
    public void setRotation(double angle) {
        carShape.setRotate(angle);
    }
}

