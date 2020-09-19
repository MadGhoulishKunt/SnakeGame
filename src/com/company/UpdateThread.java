package com.company;

public class UpdateThread implements Runnable {

    public static final int DELAY = 100;

    SnakeGame game;
    Snake snake;

    public UpdateThread(SnakeGame game, Snake snake){
        this.game = game;
        this.snake = snake;
    }

    @Override
    public void run() {
        int runCount = 0;
        try {
            while (!snake.isGameOver()) {
                game.updateSnake();
                if (snake.isGameOver()) {
                    Thread.currentThread().interrupt();
                    System.exit(3);
                }
                Thread.sleep(DELAY);
                runCount++;
                System.out.println(Thread.currentThread().getName() + " executed " + runCount + " times");
            }
        } catch (InterruptedException ex) {
            game.gameOver();
        }
        game.gameOver();
    }

}