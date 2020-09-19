package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyShape {

    public Rectangle rectangle = new Rectangle(10, 10);
    Direction direction;

    MyShape next;

    public MyShape(double x, double y, Direction direction){
        rectangle.setFill(Color.GREEN);
        rectangle.setX(x);
        rectangle.setY(y);
        this.direction = direction;
    }

    public MyShape next(){
        return next;
    }

}
