package menu.settings.gameplay;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import globals.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import menu.settings.Settings;
import menu.settings.SettingsFactory;
import menu.settings.SettingsMenuController;

public class GameplaySettingsController {	
	
	Stage stage;
	
	@FXML Label title_label, version_label;
	@FXML Button size_button, paddle_button, ball_button, ai_button, save_button, back_button;
	
	Settings current_settings;
	
	private List<String> paddle_size_string_list = Arrays.asList("SMALL", "MEDIUM", "LARGE");
	private List<String> paddle_size_list = Arrays.asList("10", "7.5", "5");
	private int size_index = 1;
	private String paddle_size_string = "MEDIUM"; 
	
	private List<String> paddle_speed_string_list = Arrays.asList("SLOW", "MEDIUM", "FAST");
	private List<String> paddle_speed_list = Arrays.asList("5", "10", "15");
	private int paddle_index = 1;
	private String paddle_speed_string = "MEDIUM"; 
	
	private List<String> ball_speed_string_list = Arrays.asList("SLOW", "MEDIUM", "FAST", "EXTREME");
	private List<String> ball_speed_list = Arrays.asList("10", "15", "20", "30");
	private int ball_index = 1;
	private String ball_speed_string = "MEDIUM"; 
	
	private List<String> ai_difficulty_string_list = Arrays.asList("EASY", "MEDIUM", "EXPERT", "IMPOSSIBLE");
	private int ai_index = 3;
	private String ai_difficulty = "IMPOSSIBLE"; 

	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public GameplaySettingsController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		save_button.setDisable(true);
		
		current_settings = SettingsFactory.getSettings();
		
		if(current_settings != null) {
			if(paddle_size_list.contains(current_settings.getPaddle_size())) {
				size_index = paddle_size_list.indexOf(current_settings.getPaddle_size());
				size_button.setText("PADDLE SIZE - " + paddle_size_string_list.get(size_index));
			}
			if(paddle_speed_list.contains(current_settings.getPaddle_speed())) {
				paddle_index = paddle_speed_list.indexOf(current_settings.getPaddle_speed());
				paddle_button.setText("PADDLE SPEED - " + paddle_speed_string_list.get(paddle_index));
			}
			if(ball_speed_list.contains(current_settings.getBall_speed())) {
				ball_index = ball_speed_list.indexOf(current_settings.getBall_speed());
				ball_button.setText("BALL SPEED - " + ball_speed_string_list.get(ball_index));
			}
			if(ai_difficulty_string_list.contains(current_settings.getAi_difficulty())) {
				ai_index = ai_difficulty_string_list.indexOf(current_settings.getAi_difficulty());
				ai_button.setText("AI DIFFICULTY - " + ai_difficulty_string_list.get(ai_index));
			}
			
		}
		
		title_label.setFont(Fonts.SECONDARY_TITLE_FONT);
		version_label.setFont(Fonts.VERSION_FONT);
		
		style_button(size_button);
		style_button(paddle_button);
		style_button(ball_button);
		style_button(ai_button);
		style_button(save_button);		
		style_button(back_button);
		
		size_button.setOnAction(ae -> {
			size_index = (size_index + 1) % paddle_size_string_list.size();
			paddle_size_string = paddle_size_string_list.get(size_index);
			size_button.setText("PADDLE SIZE - " + paddle_size_string);
			save_button.setDisable(false);
		});
		
		paddle_button.setOnAction(ae -> {
			paddle_index = (paddle_index + 1) % paddle_speed_string_list.size();
			paddle_speed_string = paddle_speed_string_list.get(paddle_index);
			paddle_button.setText("PADDLE SPEED - " + paddle_speed_string);
			save_button.setDisable(false);
		});
		
		ball_button.setOnAction(ae -> {
			ball_index = (ball_index + 1) % ball_speed_string_list.size();
			ball_speed_string = ball_speed_string_list.get(ball_index);
			ball_button.setText("BALL SPEED - " + ball_speed_string);
			save_button.setDisable(false);
		});
		
		ai_button.setOnAction(ae -> {
			ai_index = (ai_index + 1) % ai_difficulty_string_list.size();
			ai_difficulty = ai_difficulty_string_list.get(ai_index);
			ai_button.setText("AI DIFFICULTY - " + ai_difficulty);
			save_button.setDisable(false);
		});
		
		save_button.setOnAction(ae -> {	
			
			save_button.setDisable(true);
			
			String screen_width = current_settings != null ? current_settings.getScreen_width() : null;
			String screen_height = current_settings != null ? current_settings.getScreen_height() : null;
			String paddle_size = paddle_size_list.get(size_index);
			String paddle_speed = paddle_speed_list.get(paddle_index);
			String ball_speed = ball_speed_list.get(ball_index);
			
			Settings new_settings = new Settings(screen_width, screen_height, paddle_size, paddle_speed, ball_speed, ai_difficulty);
			try {
				SettingsFactory.saveSettingsLocalFile(new_settings);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		back_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../SettingsMenu.fxml"));
				SettingsMenuController controller = new SettingsMenuController(stage);
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
