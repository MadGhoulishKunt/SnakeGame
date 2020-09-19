package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final int snake_width = 10;
    public static final int snake_height = 10;

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
                head().setY(head().getY() - snake_height);
                if (head().getY() < 0) {
                    over = true;
                }
            }
            case DOWN -> {
                moveBody();
                head().setY(head().getY() + snake_height);
                if (head().getY() > SnakeGame.WINDOW_HEIGHT) {
                    over = true;
                }
            }
            case LEFT -> {
                moveBody();
                head().setX(head().getX() - snake_height);
                if (head().getX() < 0) {
                    over = true;
                }
            }
            case RIGHT -> {
                moveBody();
                head().setX(head().getX() + snake_height);
                if (head().getX() > SnakeGame.WINDOW_WIDTH) {
                    over = true;
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
                ass = snakeParts.get(i);
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
        snakeParts.add(new Rectangle(250, 250, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 260, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 270, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 280, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 290, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 300, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 310, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 320, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 330, snake_width, snake_height));
        for (Rectangle r : snakeParts){
            r.setFill(Color.GREEN);
        }
    }

    public void check() {
        Apple apple = game.getApple();
        // Ate itself
        for (int i = 1; i < snakeParts.size(); i++) {
            if (head().getX() == snakeParts.get(i).getX()
                    && head().getY() == snakeParts.get(i).getY()) {
                over = true;
                return;
            }
        }
        // Ate apple
        if (head().getX() == apple.getShape().getX()
                && head().getY() == apple.getShape().getY()) {
            game.apple.next(this);
            snakeParts.add(ass);
        }
    }

    public void grow(){

    }

    public boolean isGameOver(){
        return over;
    }

}
