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
        while (!snake.isGameOver()) {
            game.updateGame();
            try {
                Thread.sleep(game.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.gameOver();
//        System.out.println("YOU LOST!");
    }

}