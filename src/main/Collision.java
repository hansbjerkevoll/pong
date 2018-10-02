package main;

import java.util.Random;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Collision {
	
	
	
	protected static void handleCollisions(Pong pong) {
		
		hit_detection(pong, pong.paddle, pong.ball);
		hit_detection(pong, pong.ai_paddle, pong.ball);
		
		 // Restart!
        if(pong.ball.getLayoutX() <= (-pong.ball.getRadius()) || pong.ball.getLayoutX() >= (pong.scene.getWidth()) ){
        	pong.ball.relocate(((pong.scene.getWidth()-10)/2 - pong.ball.getRadius()), (pong.scene.getHeight()/2 - pong.ball.getRadius()*2));
        	resetBoard(pong);
        }

        //If the ball reaches the bottom or top border make the step negative
        if((pong.ball.getLayoutY() >= (pong.scene.getHeight() - pong.ball.getRadius())) || (pong.ball.getLayoutY() <= (pong.ball.getRadius()))){
        	pong.ball_dy = -pong.ball_dy;
        }
	}
	
	private static void hit_detection(Pong pong, Rectangle paddle, Circle ball) {
		
		if((ball.getLayoutX() <= paddle.getLayoutX() + paddle.getWidth() + ball.getRadius())
				&& (ball.getLayoutX() >= paddle.getLayoutX() - ball.getRadius())) {
			if(ball.getLayoutY() >= paddle.getLayoutY() - ball.getRadius() 
					&& (ball.getLayoutY() <= paddle.getLayoutY() + paddle.getHeight() + ball.getRadius())) {
				
				pong.ball_dx *= -1;
				
				if(ball.getLayoutY() >= paddle.getLayoutY() + paddle.getHeight() / 2) {
					if(pong.ball_dy < 0) pong.ball_dy *= -1;
				} else {
					if(pong.ball_dy > 0) pong.ball_dy *= -1;
				}
				 
			}
		}
		
	}
	
	
	
	private static void resetBoard(Pong pong) {
		pong.ball_dy = Math.max(5, Math.random()*7);
		pong.ball_dx = Math.sqrt(Math.pow(pong.ball_speed, 2) - Math.pow(pong.ball_dy, 2));
        if(new Random().nextBoolean()) pong.ball_dx *= -1;
        if(new Random().nextBoolean()) pong.ball_dy *= -1;
    	pong.move_ball = false;
	}

}
