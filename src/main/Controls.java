package main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controls {
	
	protected static void setupInput(Pong pong) {
		pong.stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
        	if(!pong.keys.contains(e.getCode())) {
            	pong.keys.add(e.getCode());
        	}
        	
        	// Start game when SPACE is pressed
        	if(e.getCode() == KeyCode.SPACE) pong.move_ball = true;
        });
        
        pong.stage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
        	if(pong.keys.contains(e.getCode())) {
            	pong.keys.remove(e.getCode());
        	}
        });
       
	}
	
	protected static void handleInput(Pong pong) {
					
		// Move Player 1 paddle
    	if(!(pong.paddle.getLayoutY() <= (0)) && pong.keys.contains(KeyCode.W)) {
    		pong.paddle.setLayoutY(pong.paddle.getLayoutY() - pong.paddle_dy);
    	}
    	if(!(pong.paddle.getLayoutY() >= (pong.scene.getHeight() - pong.paddle.getHeight())) && pong.keys.contains(KeyCode.S)) {
    		pong.paddle.setLayoutY(pong.paddle.getLayoutY() + pong.paddle_dy);
    	}
    	
    	// Move Player 2 paddle
    	if(!(pong.ai_paddle.getLayoutY() <= (0)) && pong.keys.contains(KeyCode.UP)) {
    		pong.ai_paddle.setLayoutY(pong.ai_paddle.getLayoutY() - pong.paddle_dy);
    	}
    	if(!(pong.ai_paddle.getLayoutY() >= (pong.scene.getHeight() - pong.ai_paddle.getHeight())) && pong.keys.contains(KeyCode.DOWN)) {
    		pong.ai_paddle.setLayoutY(pong.ai_paddle.getLayoutY() + pong.paddle_dy);
    	}
    	
	}

}
