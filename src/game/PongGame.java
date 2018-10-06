package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import globals.Fonts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import menu.MainMenuController;
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
	Label start_info = new Label("Press SPACE to start");
	Label pause_label = new Label("PAUSED");
	
	Button exit_button = new Button("Exit to main menu");
	
	Circle ball = new Circle(10, Color.WHITE);
	Rectangle paddle = new Rectangle(20,100, Color.WHITE);
	Rectangle ai_paddle = new Rectangle(20, 100, Color.WHITE);
	
	Vector ball_vec;
	double paddle_dy = 10;	
	
	boolean move_ball;	
	
	boolean is_paused = false;
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
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
        	
        	// If game is paused, do nothing
        	if(is_paused) {
        		return;
        	}
        	
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
       	 	
        	if(is_paused) {
        		return;
        	}
        	
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
    
    public void togglePause() {
    	
		pause_label.setVisible(!is_paused);
		exit_button.setVisible(!is_paused);
		is_paused = !is_paused;
		
		if(is_paused) {
			score_1.setStyle("-fx-text-fill: #8C8C8C;");
			score_2.setStyle("-fx-text-fill: #8C8C8C;");
		    start_info.setStyle("-fx-text-fill: #8C8C8C;");
		    line.setStroke(Color.color(140/255d, 140/255d, 140/255d));
		    ball.setFill(Color.color(140/255d, 140/255d, 140/255d));
		    paddle.setFill(Color.color(140/255d, 140/255d, 140/255d));
		    ai_paddle.setFill(Color.color(140/255d, 140/255d, 140/255d));
		} else {			
			score_1.setStyle("-fx-text-fill: #FFFFFF;");
			score_2.setStyle("-fx-text-fill: #FFFFFF;");
		    start_info.setStyle("-fx-text-fill: #FFFFFF;");
		    line.setStroke(Color.color(255/255d, 255/255d, 255/255d));
		    ball.setFill(Color.color(255/255d, 255/255d, 255/255d));
		    paddle.setFill(Color.color(255/255d, 255/255d, 255/255d));
		    ai_paddle.setFill(Color.color(255/255d, 255/255d, 255/255d));
		}
    }
       
    public void resetBoard(){
    	ball_vec = new Vector(15, 0, new Random().nextBoolean(), new Random().nextBoolean());
    	move_ball = false;
    	ball.relocate((scene_width/2 - ball.getRadius()), (scene_height/2 - ball.getRadius()*2));
    	start_info.setVisible(true);
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
        
        start_info.layoutXProperty().bind(pane.widthProperty().subtract(start_info.widthProperty()).divide(2));
        start_info.setLayoutY(100);
        start_info.setFont(Fonts.MAIN_BUTTON_FONT);
        start_info.setStyle("-fx-text-fill: #FFFFFF;");
        
        pause_label.layoutXProperty().bind(pane.widthProperty().subtract(pause_label.widthProperty()).divide(2));
        pause_label.layoutYProperty().bind(pane.heightProperty().subtract(pause_label.heightProperty()).divide(2));
        pause_label.setFont(Fonts.MAIN_TITLE_FONT);
        pause_label.setStyle("-fx-text-fill: #FFFFFF;");
        pause_label.setVisible(false);
        
        exit_button.layoutXProperty().bind(pane.widthProperty().subtract(exit_button.widthProperty()).divide(2));
        exit_button.layoutYProperty().bind(pane.heightProperty().subtract(exit_button.heightProperty()).divide(2).add(100));
        button_config(exit_button);
        exit_button.setVisible(false);
        
        pane.getChildren().addAll(ball, paddle, ai_paddle, line, score_1, score_2, start_info, pause_label, exit_button);
        
        stage.getScene().setRoot(pane);
        
        Controls.setupInput(this);
    }
    
	private void button_config(Button button) {
		
		button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu/MainMenu.fxml"));
				MainMenuController controller = new MainMenuController(stage);
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
		button.setOnMouseEntered(me -> {
			mousebtn_hover = true;
			button.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #555555;");			
		});
		
		button.setOnMouseExited(me -> {
			mousebtn_hover = false;
			if(!mousebtn_hold) {
				button.setStyle("-fx-background-color: #FFFFFF;");
			}
		});
		
		button.setOnMousePressed(mc -> {
			mousebtn_hold = true;
			button.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFFFFF;");
		});
		
		button.setOnMouseReleased(mr -> {
			mousebtn_hold = false;
			if(mousebtn_hover) {
				button.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #555555;");		
			} else {
				button.setStyle("-fx-background-color: #FFFFFF;");
			}
		});
		
	}
    
}
 