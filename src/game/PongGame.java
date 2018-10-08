package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import globals.Fonts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.MainMenuController;
import menu.MainMenuController.GameType;
import menu.settings.Settings;
import menu.settings.SettingsFactory;

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
		
	Button resume_button = new Button("Resume");
	Button restart_button = new Button("Restart");
	Button sound_button = new Button("Sound: ON");
	Button exit_button = new Button("Exit to main menu");
	
	Circle ball;
	Rectangle paddle;
	Rectangle ai_paddle;
	
	Vector ball_vec;
	
	boolean move_ball = false;	
	boolean is_paused = false;
	boolean sound = true;
	boolean mousebtn_hover = false;
	boolean mousebtn_hold = false;
	
	Settings current_settings;
	double ball_speed = 15;
	double paddle_size = 100;
	double paddle_dy = 10;	
	String ai_difficulty = "IMPOSSIBLE";
	
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
            Controls.handleInputPlayer2(this, paddle); 
            // AI movement
            AI.ai_impossible(this, ai_paddle);  	
            
        }));
        
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
		resume_button.setVisible(!is_paused);
		restart_button.setVisible(!is_paused);
		sound_button.setVisible(!is_paused);
		exit_button.setVisible(!is_paused);
		is_paused = !is_paused;
		
		if(is_paused) {
			timeline.pause();
			score_1.setStyle("-fx-text-fill: #8C8C8C;");
			score_2.setStyle("-fx-text-fill: #8C8C8C;");
		    start_info.setStyle("-fx-text-fill: #8C8C8C;");
		    line.setStroke(Color.color(140/255d, 140/255d, 140/255d));
		    ball.setFill(Color.color(140/255d, 140/255d, 140/255d));
		    paddle.setFill(Color.color(140/255d, 140/255d, 140/255d));
		    ai_paddle.setFill(Color.color(140/255d, 140/255d, 140/255d));
		} else {		
			timeline.play();
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
    	ball_vec = new Vector(ball_speed, 0, new Random().nextBoolean(), new Random().nextBoolean());
    	move_ball = false;
    	ball.relocate((scene_width/2 - ball.getRadius()), (scene_height/2 - ball.getRadius()*2));
    	start_info.setVisible(true);
    }
    
    private void initializeUI() {
    	
    	// Load settings from local file
    	current_settings = SettingsFactory.getSettings();
    	
    	if(current_settings != null) {
    		ball_speed = Integer.parseInt(current_settings.getBall_speed());
    		paddle_dy = Integer.parseInt(current_settings.getPaddle_speed());
    		paddle_size = Double.parseDouble(current_settings.getPaddle_size());
    	}
    	
    	scene_width = 1080;
    	scene_height = 720;
       	 
    	pane.setStyle("-fx-background-color: #000000;");
    	
    	ball = new Circle(10, Color.WHITE);
    	
    	paddle = new Rectangle(20,scene_height/paddle_size, Color.WHITE);
        paddle.relocate(30, (scene_height/2 - paddle.getHeight()/2));
        
        ai_paddle = new Rectangle(20,scene_height/paddle_size, Color.WHITE);
        ai_paddle.relocate(scene_width-ai_paddle.getWidth()*2, (scene_height/2 - ai_paddle.getHeight()/2));        
        
        line = new Line(scene_width/2, 15, scene_width/2, scene_height);
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
        pause_label.layoutYProperty().bind(pane.heightProperty().subtract(pause_label.heightProperty()).divide(2).subtract(125));
        pause_label.setFont(Fonts.MAIN_TITLE_FONT);
        pause_label.setStyle("-fx-text-fill: #FFFFFF;");
        pause_label.setVisible(false);
        
        resume_button.layoutXProperty().bind(pane.widthProperty().subtract(resume_button.widthProperty()).divide(2));
        resume_button.layoutYProperty().bind(pane.heightProperty().subtract(resume_button.heightProperty()).divide(2).subtract(25));
        button_style(resume_button);
        resume_button.setVisible(false);
        
        restart_button.layoutXProperty().bind(pane.widthProperty().subtract(restart_button.widthProperty()).divide(2));
        restart_button.layoutYProperty().bind(pane.heightProperty().subtract(restart_button.heightProperty()).divide(2).add(25));
        button_style(restart_button);
        restart_button.setVisible(false);
        
        sound_button.layoutXProperty().bind(pane.widthProperty().subtract(sound_button.widthProperty()).divide(2));
        sound_button.layoutYProperty().bind(pane.heightProperty().subtract(sound_button.heightProperty()).divide(2).add(75));
        button_style(sound_button);
        sound_button.setVisible(false);
        
        exit_button.layoutXProperty().bind(pane.widthProperty().subtract(exit_button.widthProperty()).divide(2));
        exit_button.layoutYProperty().bind(pane.heightProperty().subtract(exit_button.heightProperty()).divide(2).add(125));
        button_style(exit_button);
        exit_button.setVisible(false);
        
        resume_button.setOnAction(ae -> {
        	togglePause();
        });
        
        restart_button.setOnAction(ae -> {
        	resetBoard();
        	togglePause();
        	score_1.setText("0");
        	score_2.setText("0");
        });
        
        sound_button.setOnAction(ae -> {
        	sound = !sound;
        	sound_button.setText(sound ? "Sound: ON" : "Sound: OFF");
        });
        
        exit_button.setOnAction(ae -> {
			
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

    	resetBoard();
        
        pane.getChildren().addAll(ball, paddle, ai_paddle, line, score_1, score_2, start_info, pause_label, resume_button, restart_button, sound_button, exit_button);
        
        stage.getScene().setRoot(pane);
        
        Controls.setupInput(this);
    }
    
	private void button_style(Button button) {
		
		button.setFont(Fonts.SECONDARY_BUTTON_FONT);	
		button.setPrefWidth(200);
		
		button.setStyle("-fx-focus-color: transparent;");
		
		button.setOnMouseEntered(me -> {
			mousebtn_hover = true;
			button.setStyle("-fx-focus-color: transparent; -fx-text-fill: #FFFFFF; -fx-background-color: #555555;");			
		});
		
		button.setOnMouseExited(me -> {
			mousebtn_hover = false;
			if(!mousebtn_hold) {
				button.setStyle("-fx-focus-color: transparent; -fx-background-color: #FFFFFF;");
			}
		});
		
		button.setOnMousePressed(mc -> {
			mousebtn_hold = true;
			button.setStyle("-fx-focus-color: transparent; -fx-background-color: #333333; -fx-text-fill: #FFFFFF;");
		});
		
		button.setOnMouseReleased(mr -> {
			mousebtn_hold = false;
			if(mousebtn_hover) {
				button.setStyle("-fx-focus-color: transparent; -fx-text-fill: #FFFFFF; -fx-background-color: #555555;");		
			} else {
				button.setStyle("-fx-focus-color: transparent; -fx-background-color: #FFFFFF;");
			}
		});
		
	}
   
	
	
}
 