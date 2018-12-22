package game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Controls {
	
	protected static void setupInput(PongGame pong) {
		
		pong.stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			e.consume();
        	if(!pong.keys.contains(e.getCode())) {
            	pong.keys.add(e.getCode());
        	}
        	
        	// Start game when SPACE is pressed
        	if(e.getCode() == KeyCode.SPACE) {
        		pong.move_ball = true;
        		pong.start_info.setVisible(false);
        	}
        	
        	// Pause game
        	if(e.getCode() == KeyCode.ESCAPE) {
        		pong.togglePause();
        	}
        });
        
        pong.stage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
        	e.consume();
        	if(pong.keys.contains(e.getCode())) {
            	pong.keys.remove(e.getCode());
        	}
        });
       
	}
	
	protected static void handleInputPlayer1(PongGame pong, Rectangle paddle) {
					
		// Move Player 1 paddle
    	if(!(paddle.getLayoutY() <= (0)) && pong.keys.contains(KeyCode.W)) {
    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
    	}
    	if(!(paddle.getLayoutY() >= (pong.scene_height - paddle.getHeight())) && pong.keys.contains(KeyCode.S)) {
    		paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
    	}
	}
	
	protected static void handleInputPlayer2(PongGame pong, Rectangle paddle) {
		
    	// Move Player 2 paddle
    	if(!(paddle.getLayoutY() <= (0)) && pong.keys.contains(KeyCode.UP)) {
    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
    	}
    	if(!(paddle.getLayoutY() >= (pong.scene_height - paddle.getHeight())) && pong.keys.contains(KeyCode.DOWN)) {
    		paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
    	}
    	
	}

}
