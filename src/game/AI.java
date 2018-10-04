package game;

import javafx.scene.shape.Rectangle;

public class AI {
	
	protected static void ai_movement(Pong pong, Rectangle paddle) {
		
		// Move up or down if ball is coming towards the AI
		if(pong.ball_vec.x_value > 0 || pong.move_ball == false) {
			// Move UP
	    	if(!(paddle.getLayoutY() <= (0)) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 > pong.ball.getLayoutY())) {
	    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
	    	}
	    	
	    	// Move down
	    	if(!(paddle.getLayoutY() >= (pong.scene.getHeight() - paddle.getHeight())) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 < pong.ball.getLayoutY())) {
	    		paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
	    	}
		} else {
			if(paddle.getLayoutY() + paddle.getHeight()/2 > pong.scene.getHeight()/2) {
				paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
			} else if (paddle.getLayoutY() + paddle.getHeight()/2 < pong.scene.getHeight()/2){
				paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
			}
		}
		
    	
	}

}
