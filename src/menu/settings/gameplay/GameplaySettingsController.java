package menu.settings.gameplay;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import globals.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import menu.settings.SettingsController;

public class GameplaySettingsController {	
	
	Stage stage;
	
	@FXML Label title_label, version_label;
	@FXML Button size_button, paddle_button, ball_button, ai_button, back_button;
	
	private List<String> paddle_size_list = Arrays.asList("SMALL", "MEDIUM", "LARGE");
	private int size_index = 0;
	private String paddle_size = "SMALL"; 
	
	private List<String> paddle_speed_list = Arrays.asList("SLOW", "MEDIUM", "FAST");
	private int paddle_index = 0;
	private String paddle_speed = "SLOW"; 
	
	private List<String> ball_speed_list = Arrays.asList("SLOW", "MEDIUM", "FAST");
	private int ball_index = 0;
	private String ball_speed = "SLOW"; 
	
	private List<String> ai_difficulty_list = Arrays.asList("EASY", "MEDIUM", "EXPERT", "IMPOSSIBLE");
	private int ai_index = 0;
	private String ai_difficulty = "EASY"; 

	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public GameplaySettingsController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		title_label.setFont(Fonts.SECONDARY_TITLE_FONT);
		version_label.setFont(Fonts.VERSION_FONT);
		
		style_button(size_button);
		style_button(paddle_button);
		style_button(ball_button);
		style_button(ai_button);
		style_button(back_button);
		
		size_button.setOnAction(ae -> {
			size_index = (size_index + 1) % paddle_size_list.size();
			paddle_size = paddle_size_list.get(size_index);
			size_button.setText("PADDLE SIZE - " + paddle_size);
		});
		
		paddle_button.setOnAction(ae -> {
			paddle_index = (paddle_index + 1) % paddle_speed_list.size();
			paddle_speed = paddle_speed_list.get(paddle_index);
			paddle_button.setText("PADDLE SPEED - " + paddle_speed);
		});
		
		ball_button.setOnAction(ae -> {
			ball_index = (ball_index + 1) % ball_speed_list.size();
			ball_speed = ball_speed_list.get(ball_index);
			ball_button.setText("BALL SPEED - " + ball_speed);
		});
		
		ai_button.setOnAction(ae -> {
			ai_index = (ai_index + 1) % ai_difficulty_list.size();
			ai_difficulty = ai_difficulty_list.get(ai_index);
			ai_button.setText("AI DIFFICULTY - " + ai_difficulty);
		});
		
		back_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../Settings.fxml"));
				SettingsController controller = new SettingsController(stage);
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void style_button(Button button) {
		
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
