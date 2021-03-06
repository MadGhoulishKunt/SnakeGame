package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SnakeGame extends Application {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    public int SPEED = 100;
    public Group group;
    public Scene scene;
    Snake snake;
    Apple apple;
    Direction dir;
    Thread thread;
    private boolean started = false;

    @Override
    public void start(Stage stage){
        group = new Group();
        scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
        initGame();
        group.getChildren().add(apple.getShape());
        group.getChildren().addAll(snake.getParts());
        moveSnakeOnKeyPress(scene);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(4);
            }
        });
        stage.setResizable(false);
        stage.setTitle("SnakeGame");
        stage.show();
    }

    public void initGame(){
        apple = new Apple(150, 150);
        snake = new Snake(this);
        Runnable r = new UpdateThread(this, snake);
        thread = new Thread(r);
    }

    public void newGame(){
        started = true;
        thread.start();
    }

    public void updateGame(){
        Platform.runLater(() -> {
            snake.move();
            snake.check();
        });
    }

    private void moveSnakeOnKeyPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (dir == Direction.DOWN) return;
                    if (!started)
                        newGame();
                    if (snake != null) {
                        dir = Direction.UP;
                        snake.changeDirection(dir);
                    }
                }
                case RIGHT -> {
                    if (dir == Direction.LEFT) return;
                    if (!started)
                        newGame();
                    if (snake != null) {
                        dir = Direction.RIGHT;
                        snake.changeDirection(dir);
                    }
                }
                case DOWN -> {
                    if (dir == Direction.UP) return;
                    if (!started)
                        newGame();
                    if (snake != null) {
                        dir = Direction.DOWN;
                        snake.changeDirection(dir);
                    }
                }
                case LEFT -> {
                    if (dir == Direction.RIGHT) return;
                    if (!started)
                        newGame();
                    if (snake != null) {
                        dir = Direction.LEFT;
                        snake.changeDirection(dir);
                    }
                }
            }
        });
    }

    public void gameOver(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(5);
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Apple getApple() {
        return apple;
    }


}
