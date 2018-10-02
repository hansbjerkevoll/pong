package main;

public class AI {
	
	protected static void ai_movement(Pong pong) {
			
		// Move UP
    	if(!(pong.ai_paddle.getLayoutY() <= (0)) 
    			&& (pong.ai_paddle.getLayoutY() + pong.ai_paddle.getHeight()/2 > pong.ball.getLayoutY())) {
    		pong.ai_paddle.setLayoutY(pong.ai_paddle.getLayoutY() - pong.paddle_dy);
    	}
    	
    	// Move down
    	if(!(pong.ai_paddle.getLayoutY() >= (pong.scene.getHeight() - pong.ai_paddle.getHeight())) 
    			&& (pong.ai_paddle.getLayoutY()+pong.ai_paddle.getHeight()/2 < pong.ball.getLayoutY())) {
    		pong.ai_paddle.setLayoutY(pong.ai_paddle.getLayoutY() + pong.paddle_dy);
    	}
		
	}

}
