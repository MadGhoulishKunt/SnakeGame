package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Apple {

    public static final int apple_width = 10;
    public static final int apple_height = 10;

    Random r = new Random();
    Rectangle shape;

    public Apple(double x, double y) {
        this.shape = new Rectangle(x, y, apple_width, apple_height);
        shape.setFill(Color.RED);
    }

    public void next(Snake snake) {
        for (Rectangle e : snake.getParts()) {
            while (shape.getX() == e.getX() && shape.getY() == e.getY()) {
                shape.setX(getNew());
                shape.setY(getNew());
            }
        }
    }

    private double getNew() {
        int d = 501;
        while (d >= 500 || d % 10 != 0) {
            d = r.nextInt(500);
        }
        return d;
    }

    public Rectangle getShape(){
        return shape;
    }

}
