package com.company;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final int snake_width = 10;
    public static final int snake_height = 10;

    private SnakeGame game;
    private Direction dir;
    public List<Rectangle> snakeParts;
//    private Rectangle head;
    private Rectangle temp;
    private Rectangle ass;
    private boolean over = false;

    public Snake(SnakeGame game){
        this.game = game;
        initDefaults();
    }

    public void move(){
        switch (dir){
            case UP:
                moveBody();
                snakeParts.get(0).setY(snakeParts.get(0).getY() - snake_height);
                if (snakeParts.get(0).getY() < 0){
                    over = true;
                }
                break;
            case DOWN:
                moveBody();
                snakeParts.get(0).setY(snakeParts.get(0).getY() + snake_height);
                if (snakeParts.get(0).getY() > SnakeGame.WINDOW_HEIGHT){
                    over = true;
                }
                break;
            case LEFT:
                moveBody();
                snakeParts.get(0).setX(snakeParts.get(0).getX() - snake_height);
                if (snakeParts.get(0).getX() < 0){
                    over = true;
                }
                break;
            case RIGHT:
                moveBody();
                snakeParts.get(0).setX(snakeParts.get(0).getX() + snake_height);
                if (snakeParts.get(0).getX() > SnakeGame.WINDOW_WIDTH){
                    over = true;
                }
                break;
        }
    }

    public void changeDirection(Direction direction){
        this.dir = direction;
    }

    private void moveBody() {
        for (int i = snakeParts.size() - 1; i > 0; i--) {
            temp = snakeParts.get(i - 1);
            snakeParts.get(i).setX(temp.getX());
            snakeParts.get(i).setY(temp.getY());
        }
    }

    public List<Rectangle> getParts(){
        return snakeParts;
    }

//    public Rectangle getHead(){
//        return head;
//    }

    private void initDefaults() {
        snakeParts = Collections
                .synchronizedList(new ArrayList<Rectangle>());
        snakeParts.add(new Rectangle(250, 250, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 260, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 270, snake_width, snake_height));
        snakeParts.add(new Rectangle(250, 280, snake_width, snake_height));
    }

    public boolean isGameOver(){
        return over;
    }

}
