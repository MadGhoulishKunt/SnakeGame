package com.company;

public class UpdateThread implements Runnable {

    SnakeGame game;
    Snake snake;

    public UpdateThread(SnakeGame game, Snake snake){
        this.game = game;
        this.snake = snake;
    }

    @Override
    public void run() {
        try {
            while (!snake.isGameOver()) {
                game.updateGame();
                if (snake.isGameOver()) {
                    Thread.currentThread().interrupt();
                    System.exit(3);
                }
                Thread.sleep(game.SPEED);
            }
        } catch (InterruptedException ex) {
            game.gameOver();
        }
        game.gameOver();
    }

}