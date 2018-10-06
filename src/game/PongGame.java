package game;

import java.util.ArrayList;
import java.util.Random;

import globals.Fonts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.MainMenuController.GameType;

public class PongGame {
	
	
	Stage stage;
	Pane pane = new Pane();
	
	Timeline timeline;

	ArrayList<KeyCode> keys = new ArrayList<>();
		
	double scene_width;
	double scene_height;
	
	Line line;
	
	Label score_1 = new Label("0");
	Label score_2 = new Label("0");
	Label info = new Label("Press SPACE to start");
	
	Circle ball = new Circle(10, Color.WHITE);
	Rectangle paddle = new Rectangle(20,100, Color.WHITE);
	Rectangle ai_paddle = new Rectangle(20, 100, Color.WHITE);
	
	Vector ball_vec;
	double paddle_dy = 10;	
	boolean move_ball;
	
    public void start(Stage stage, GameType gametype) {

    	this.stage = stage;
    	
    	initializeUI();
        
        if(gametype == GameType.SINGLE_PLAYER) {
        	timeline = single_player_timeline();
        } else if(gametype == GameType.MULTI_PLAYER) {
        	timeline = multi_player_timeline();
        }
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    private Timeline single_player_timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
       	 
        	if(move_ball) {
            	ball.setLayoutX(ball.getLayoutX() + ball_vec.x_value);
            	ball.setLayoutY(ball.getLayoutY() + ball_vec.y_value);	
        	}
            
            //Handle colisions
        	Collision.handleCollisions(this);
            // Handle user input
            Controls.handleInputPlayer1(this, paddle);   
            Controls.handleInputPlayer2(this, paddle); 
            // AI movement
            AI.ai_impossible(this, ai_paddle); 
            
        }));
        

        timeline.setCycleCount(20);
        return timeline;
        
    }
    
    private Timeline multi_player_timeline() {
        return timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
       	 
        	if(move_ball) {
            	ball.setLayoutX(ball.getLayoutX() + ball_vec.x_value);
            	ball.setLayoutY(ball.getLayoutY() + ball_vec.y_value);	
        	}
            
            //Handle colisions
        	Collision.handleCollisions(this);
            // Handle user input
            Controls.handleInputPlayer1(this, paddle);  
            Controls.handleInputPlayer2(this, ai_paddle);
            
        }));
        
    }
       
    public void resetBoard(){
    	ball_vec = new Vector(15, 0, new Random().nextBoolean(), new Random().nextBoolean());
    	move_ball = false;
    	ball.relocate((scene_width/2 - ball.getRadius()), (scene_height/2 - ball.getRadius()*2));
    	info.setVisible(true);
    }
    
    private void initializeUI() {
    	
    	scene_width = stage.getWidth();
    	scene_height = stage.getHeight();
    	
    	resetBoard();
       	 
    	pane.setStyle("-fx-background-color: #000000;");
    	
        paddle.relocate(30, (scene_height/2 - paddle.getHeight()/2));
        
        ai_paddle.relocate(scene_width-ai_paddle.getWidth()*2, (scene_height/2 - ai_paddle.getHeight()/2));        
        
        line = new Line(scene_width/2, 0, scene_width/2, scene_height);
        line.setStroke(Color.WHITE);
        line.getStrokeDashArray().addAll(5d);
        
        score_1.layoutXProperty().bind(pane.widthProperty().subtract(score_1.widthProperty()).divide(2).subtract(70));
        score_1.setLayoutY(0);
        score_1.setStyle("-fx-text-fill: #FFFFFF;");
        score_1.setFont(Fonts.SCORE_FONT);
        
        score_2.layoutXProperty().bind(pane.widthProperty().subtract(score_2.widthProperty()).divide(2).add(75));
        score_2.setLayoutY(0);
        score_2.setStyle("-fx-text-fill: #FFFFFF;");
        score_2.setFont(Fonts.SCORE_FONT);
        
        info.layoutXProperty().bind(pane.widthProperty().subtract(info.widthProperty()).divide(2));
        info.setLayoutY(100);
        info.setFont(Fonts.MAIN_BUTTON_FONT);
        info.setStyle("-fx-text-fill: #FFFFFF;");
        
        pane.getChildren().addAll(ball, paddle, ai_paddle, line, score_1, score_2, info);
        
        stage.getScene().setRoot(pane);
        
        Controls.setupInput(this);
    }
    
}
 