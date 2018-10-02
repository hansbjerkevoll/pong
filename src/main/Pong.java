package main;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pong extends Application{
	
	Stage stage;
	Pane pane = new Pane();
	
	Timeline timeline;

	ArrayList<KeyCode> keys = new ArrayList<>();
	
	Scene scene = new Scene(pane, 1080, 600, Color.BLACK);
	
	Line line = new Line(scene.getWidth()/2, 12, scene.getWidth()/2, scene.getHeight());
	
	Text score_1 = new Text("0");
	Text score_2 = new Text("0");
	
	Circle ball = new Circle(10, Color.WHITE);
	Rectangle paddle = new Rectangle(20,100, Color.WHITE);
	Rectangle ai_paddle = new Rectangle(20, 100, Color.WHITE);
	
	Vector ball_vec = new Vector(5, Math.random()*(Math.PI/4), new Random().nextBoolean(), new Random().nextBoolean());
	
	double paddle_dy = 15;
	
	boolean move_ball = false;
	
    @Override
    public void start(Stage stage) {
    	
    	this.stage = stage;
    	stage.setResizable(false);
    	
        ball.relocate((scene.getWidth()/2 - ball.getRadius()), (scene.getHeight()/2 - ball.getRadius()*2));
        paddle.relocate(30, (scene.getHeight()/2 - paddle.getHeight()/2));
        
        ai_paddle.relocate(scene.getWidth()-ai_paddle.getWidth()*2, (scene.getHeight()/2 - ai_paddle.getHeight()/2));        
        
        line.setStroke(Color.WHITE);
        line.getStrokeDashArray().addAll(5d);
        
        score_1.setX(scene.getWidth()/2 - 75);
        score_1.setY(50);
        score_1.setStroke(Color.WHITE);
        score_1.setFill(Color.WHITE);
        score_1.setFont(Font.font("Times New Roman", 50));
        
        score_2.setX(scene.getWidth()/2 + 50);
        score_2.setY(50);
        score_2.setStroke(Color.WHITE);
        score_2.setFill(Color.WHITE);
        score_2.setFont(Font.font("Times New Roman", 50));
        
        pane.getChildren().addAll(ball, paddle, ai_paddle, line, score_1, score_2);
        
        stage.setTitle("Pong");
        stage.setScene(scene);
        stage.show();
        
        
        Controls.setupInput(this);
        
        timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
        	 
        	if(move_ball) {
            	ball.setLayoutX(ball.getLayoutX() + ball_vec.x_value);
            	ball.setLayoutY(ball.getLayoutY() + ball_vec.y_value);	
        	}
            
            //Handle colisions
        	Collision.handleCollisions(this);
            // Handle user input
            Controls.handleInput(this);   
            // AI movement
           AI.ai_movement(this);
            
        }));
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
 