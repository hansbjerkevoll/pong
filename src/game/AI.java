package game;

import javafx.scene.shape.Rectangle;

public class AI {
	
	protected static void ai_movement_calc(Pong pong, Rectangle paddle) {
		
		// Move up or down if ball is coming towards the AI
		if((pong.ball_vec.x_value > 0)|| pong.move_ball == false) {
			
			double y_impact = calc_y_impact(pong);
			//System.out.println(y_impact);
			
			
			// Move UP
	    	if(!(paddle.getLayoutY() <= (0)) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 > y_impact)) {
	    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
	    	}
	    	
	    	// Move down
	    	if(!(paddle.getLayoutY() >= (pong.scene.getHeight() - paddle.getHeight())) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 < y_impact)) {
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
	
	protected static void ai_movement_ypos(Pong pong, Rectangle paddle) {
		
		// Follow the y cord of the ball
		if((pong.ball_vec.x_value < 0)|| pong.move_ball == false) {
					
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
	
	private static double calc_y_impact(Pong pong) {
		
		double x_pos = pong.ball.getLayoutX();
		double y_pos = pong.ball.getLayoutY();
		
		double delta_x =  pong.scene.getWidth() - (pong.scene.getWidth() - pong.ai_paddle.getLayoutX())-x_pos;
		
		double alpha = pong.ball_vec.angle;
		
		double delta_y = Math.tan(alpha)*delta_x;
		
		double impact = delta_y;;
		
		if(pong.ball_vec.y_value < 0) impact *= -1;
		impact += y_pos;		
		
		double sh = pong.scene.getHeight();
		
		while(impact > sh * 3 || impact < -sh*2) {
			if(impact > sh * 3) {
				impact -= sh;
			} else if(impact < -sh * 2 ) {
				impact += sh;
			}
		}
			
		if(impact > sh*2) {
			impact -= 2*sh;
		} else if(impact > sh) {
			impact = sh - (impact - sh);
		} else if(impact < -sh) {
			impact += 2*sh;
		} else if(impact < 0) {
			impact *= -1;
		} 
		
		return impact;
		
	}
}
