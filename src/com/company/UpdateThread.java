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
                snake.check();
                game.updateGame();
                if (snake.isGameOver()) {
                    Thread.currentThread().interrupt();
                    System.exit(3);
                }
//                if (!Thread.currentThread().isInterrupted()) {
//                    game.group.getChildren().setAll(snake.getParts());
//                }
                Thread.sleep(DELAY);
                runCount++;
//                System.out.println(Thread.currentThread().getName() + " executed " + runCount + " times");
            }
        } catch (InterruptedException ex) {
            game.gameOver();
        }
        game.gameOver();
    }

}