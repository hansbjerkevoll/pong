package game;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Collision {
	
	
	
	protected static void handleCollisions(Pong pong) {
		
		hit_detection(pong, pong.paddle, pong.ball);
		hit_detection(pong, pong.ai_paddle, pong.ball);
		
		 // Restart!
        if(pong.ball.getLayoutX() <= (-pong.ball.getRadius())){
        	pong.score_2.setText(Integer.toString((Integer.parseInt(pong.score_2.getText()) + 1)));
        	pong.ball.relocate(((pong.scene.getWidth()-10)/2 - pong.ball.getRadius()), (pong.scene.getHeight()/2 - pong.ball.getRadius()*2));
        	pong.resetBoard();
    		
        } else if(pong.ball.getLayoutX() >= (pong.scene.getWidth()) ) {
        	pong.score_1.setText(Integer.toString((Integer.parseInt(pong.score_1.getText()) + 1)));
        	pong.ball.relocate(((pong.scene.getWidth()-10)/2 - pong.ball.getRadius()), (pong.scene.getHeight()/2 - pong.ball.getRadius()*2));
        	pong.resetBoard();
        }

        //If the ball reaches the bottom or top border make the step negative
        if((pong.ball.getLayoutY() >= (pong.scene.getHeight() - pong.ball.getRadius())) || (pong.ball.getLayoutY() <= (pong.ball.getRadius()))){
        	pong.ball_vec.y_value *= -1;;
        }
	}
	
	private static void hit_detection(Pong pong, Rectangle paddle, Circle ball) {
		
		if((ball.getLayoutX() <= paddle.getLayoutX() + paddle.getWidth() + ball.getRadius())
				&& (ball.getLayoutX() >= paddle.getLayoutX() - ball.getRadius())) {
			if(ball.getLayoutY() >= paddle.getLayoutY() - ball.getRadius() 
					&& (ball.getLayoutY() <= paddle.getLayoutY() + paddle.getHeight() + ball.getRadius())) {
				
				boolean change_xdir = false;
				if(pong.ball_vec.x_value > 0) change_xdir = true;
				
				double ang_frac = ((paddle.getLayoutY() + (paddle.getHeight() / 2)) - pong.ball.getLayoutY()) / (paddle.getHeight()/2)*-1;
				
				double new_ang = ang_frac * (Math.PI/4);
				pong.ball_vec.setAngle(new_ang);  
				
				if(change_xdir)pong.ball_vec.x_value *= -1; 
				 
			}
		}
		
	}

}
