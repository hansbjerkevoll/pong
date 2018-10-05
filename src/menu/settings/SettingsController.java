package menu.settings;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingsController {	
	
	Stage stage;
	Scene mainmenu_scene;
	
	@FXML Label title_label, version_label;
	@FXML Button paddle_button, ball_button, ai_button, screen_button, save_button, back_button;
	
	private List<String> paddle_speed = Arrays.asList("SLOW", "MEDIUM", "FAST");
	private int paddle_index = 0;
	private List<String> ball_speed = Arrays.asList("SLOW", "MEDIUM", "FAST");
	private int ball_index = 0;
	private List<String> ai_difficulty = Arrays.asList("EASY", "MEDIUM", "EXPERT", "IMPOSSIBLE");
	private int ai_index = 0;
	private List<String> screen_res = Arrays.asList("960x540", "1280x720", "1600x900", "1920x1080", "2560x1440", "3840x2160");
	private int screen_index = 0;
	
	Font title_font = Font.loadFont(getClass().getResourceAsStream("../pong_font.ttf"), 60);
	Font button_font = Font.loadFont(getClass().getResourceAsStream("../pong_font.ttf"), 15);
	Font version_font = Font.loadFont(getClass().getResourceAsStream("../pong_font.ttf"), 12);
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public SettingsController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		title_label.setFont(title_font);
		version_label.setFont(version_font);
		
		style_button(paddle_button);
		style_button(ball_button);
		style_button(ai_button);
		style_button(screen_button);
		style_button(save_button);
		style_button(back_button);
		
		back_button.setOnAction(ae -> {
			((Stage) back_button.getScene().getWindow()).setScene(mainmenu_scene);
		});
	}
	
	private void style_button(Button button) {
		
		button.setFont(button_font);
		
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

	public void setMainMenuScene(Scene scene) {
		this.mainmenu_scene = scene;
	}
}
