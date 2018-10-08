package game;

import javafx.scene.shape.Rectangle;

public class AI {
	
	protected static void ai_impossible(PongGame pong, Rectangle paddle) {
		
		// Move up or down if ball is coming towards the AI
		if((pong.ball_vec.x_value > 0)|| pong.move_ball == false) {
			
			double y_impact = calc_y_impact(pong);
			
			double precision = 0;
			if(pong.paddle.getLayoutY() + pong.paddle.getHeight()/2 >= pong.scene_height/2) {
				precision = precision_shot_up(pong, paddle, pong.paddle);
			} else {
				precision = precision_shot_down(pong, paddle, pong.paddle);
			}
					
			double target = y_impact + precision;
			
			// Move UP
	    	if(!(paddle.getLayoutY() < (0)) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 > target)) {
	    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
	    	}
	    	
	    	// Move down
	    	if(!(paddle.getLayoutY() > (pong.scene_height - paddle.getHeight())) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 < target)) {
	    		paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
	    	}
			
		} else {
			if((paddle.getLayoutY() + paddle.getHeight()/2 < pong.scene_height/2 + pong.paddle_dy) &&  (paddle.getLayoutY() + paddle.getHeight()/2 > pong.scene_height/2 - pong.paddle_dy)) {
				paddle.setLayoutY(pong.scene_height/2 - paddle.getHeight()/2);
			} else if(paddle.getLayoutY() + paddle.getHeight()/2 > pong.scene_height/2) {
				paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
			} else if (paddle.getLayoutY() + paddle.getHeight()/2 < pong.scene_height/2){
				paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
			}
		}
		
	}
	
	protected static void ai_expert(PongGame pong, Rectangle paddle) {
		
		// Follow the y cord of the ball
		if((pong.ball_vec.x_value > 0)|| pong.move_ball == false) {
					
			// Move UP
	    	if(!(paddle.getLayoutY() <= (0)) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 > pong.ball.getLayoutY())) {
	    		paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
	    	}
	    	
	    	// Move down
	    	if(!(paddle.getLayoutY() >= (pong.scene_height) - paddle.getHeight()) 
	    			&& (paddle.getLayoutY() + paddle.getHeight()/2 < pong.ball.getLayoutY())) {
	    		paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
	    	}
			
		} else {
			if((paddle.getLayoutY() + paddle.getHeight()/2 < pong.scene_height/2 + pong.paddle_dy) &&  (paddle.getLayoutY() + paddle.getHeight()/2 > pong.scene_height/2 - pong.paddle_dy)) {
				paddle.setLayoutY(pong.scene_height/2 - paddle.getHeight()/2);
			} else if(paddle.getLayoutY() + paddle.getHeight()/2 > pong.scene_height/2) {
				paddle.setLayoutY(paddle.getLayoutY() - pong.paddle_dy);
			} else if (paddle.getLayoutY() + paddle.getHeight()/2 < pong.scene_height/2){
				paddle.setLayoutY(paddle.getLayoutY() + pong.paddle_dy);
			}
		}
	}
	
	// Calculate where the ball will impact
	private static double calc_y_impact(PongGame pong) {
		
		double x_pos = pong.ball.getLayoutX();
		double y_pos = pong.ball.getLayoutY();
		
		double delta_x =  pong.scene_width - (pong.scene_width - pong.ai_paddle.getLayoutX()) - x_pos;
		
		double alpha = pong.ball_vec.angle;
		
		double delta_y = Math.tan(alpha)*delta_x;
		
		double impact = delta_y;;
		
		if(pong.ball_vec.y_value < 0) impact *= -1;
		impact += y_pos;		
		
		double sh = pong.scene_height;
		
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

	// Calculate where to shoot the ball, to hit the corners
	private static double precision_shot_up(PongGame pong, Rectangle paddle_1, Rectangle paddle_2) {
		
		double delta_y = paddle_1.getLayoutY()+(paddle_1.getHeight()/2);
		
		double delta_x = pong.scene_width - ((pong.scene_width - paddle_1.getLayoutX()));
		double alpha = Math.atan(delta_y/delta_x);
		
		double h_diff = alpha * Math.PI/4;
		
		double precision = h_diff*(paddle_1.getHeight()/2);
		
		return precision;
	}
	private static double precision_shot_down(PongGame pong, Rectangle paddle_1, Rectangle paddle_2) {
		
		double delta_y = pong.scene_height - (paddle_1.getLayoutY()+(paddle_1.getHeight()/2));
		
		double delta_x = pong.scene_width - ((pong.scene_width - paddle_1.getLayoutX()));
		double alpha = Math.atan(delta_y/delta_x);
		
		double h_diff = alpha * Math.PI/4;
		
		double precision = h_diff*(paddle_1.getHeight()/2)*-1;
		
		return precision;
	}
	
}
