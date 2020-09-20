package com.company;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final int snake_width = 25;
    public static final int snake_height = 25;

    int x = 1;
    SnakeGame game;
    private Direction dir;
    public List<Rectangle> snakeParts;
    public Rectangle ass;
    private boolean over = false;

    public Snake(SnakeGame game){
        this.game = game;
        initDefaults();
    }

    public void move(){
        switch (dir) {
            case UP -> {
                moveBody();
                if (head().getY() == 0){
                    head().setY(game.WINDOW_HEIGHT - snake_height);
                } else {
                    head().setY(head().getY() - snake_height);
                }
            }
            case DOWN -> {
                moveBody();
                if (head().getY() == game.WINDOW_HEIGHT - snake_height){
                    head().setY(0);
                } else {
                    head().setY(head().getY() + snake_height);
                }
            }
            case LEFT -> {
                moveBody();
                if (head().getX() == 0){
                    head().setX(game.WINDOW_WIDTH - snake_width);
                } else {
                    head().setX(head().getX() - snake_height);
                }
            }
            case RIGHT -> {
                moveBody();
                if (head().getX() == game.WINDOW_WIDTH - snake_width){
                    head().setX(0);
                } else {
                    head().setX(head().getX() + snake_height);
                }
            }
        }
    }

    public void changeDirection(Direction direction){
        this.dir = direction;
    }

    private void moveBody() {
        for (int i = snakeParts.size() - 1; i > 0; i--) {
            if (i == snakeParts.size() - 1) {
                ass = new Rectangle(snakeParts.get(i).getX(), snakeParts.get(i).getY(), snake_width, snake_height);
            }
            Rectangle temp = snakeParts.get(i - 1);
            snakeParts.get(i).setX(temp.getX());
            snakeParts.get(i).setY(temp.getY());
        }
    }

    public List<Rectangle> getParts(){
        return snakeParts;
    }

    public Rectangle head(){
        return snakeParts.get(0);
    }

    private void initDefaults() {
        snakeParts = Collections
                .synchronizedList(new ArrayList<Rectangle>());
        snakeParts.add(new Rectangle(250, 225, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 250, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 275, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 300, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 325, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 350, snake_width, snake_height));
        for (Rectangle r : snakeParts){
            r.setFill(Color.GREEN);
            r.setArcWidth(5.0);
            r.setArcHeight(5.0);
        }
    }

    public void check() {
        Apple apple = game.getApple();
        // Ate itself
        for (int i = 1; i < snakeParts.size(); i++) {
            if (head().getX() == snakeParts.get(i).getX()
                    && head().getY()== snakeParts.get(i).getY()) {
                over = true;
                game.gameOver();
                return;
            }
        }
        // Ate apple
        if (head().getX() == apple.getShape().getX()
                && head().getY() == apple.getShape().getY()) {
            game.apple.next(this);
            grow();
        }
    }

    public void grow(){
        ass.setFill(Color.GREEN);
        ass.setArcWidth(5.0);
        ass.setArcHeight(5.0);
        snakeParts.add(ass);
        game.group.getChildren().add(ass);
        if(game.SPEED > 40){
            game.SPEED = game.SPEED - x;
        }
    }

    public boolean isGameOver(){
        return over;
    }

}
