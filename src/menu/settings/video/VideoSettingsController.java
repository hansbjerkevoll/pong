package menu.settings.video;

import java.io.IOException;

import globals.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menu.settings.Settings;
import menu.settings.SettingsFactory;
import menu.settings.SettingsMenuController;

public class VideoSettingsController {
	
	Stage stage;
	
	Settings current_settings;
	
	@FXML Label title_label, width_label, height_label, version_label;
	@FXML TextField width_field, height_field;
	@FXML Button save_button, back_button;
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public VideoSettingsController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		

		current_settings = SettingsFactory.getSettings();
		
		title_label.setFont(Fonts.SECONDARY_TITLE_FONT);
		width_label.setFont(Fonts.SECONDARY_BUTTON_FONT);
		height_label.setFont(Fonts.SECONDARY_BUTTON_FONT);
		version_label.setFont(Fonts.VERSION_FONT);
		
		width_field.setFont(Fonts.SECONDARY_BUTTON_FONT);
		height_field.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
		save_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		back_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
		style_button(save_button);
		style_button(back_button);
		
		width_field.setText(Integer.toString((int)stage.getScene().getWidth()));
		height_field.setText(Integer.toString((int)stage.getHeight()));
		
		save_button.setDisable(true);
		
		width_field.textProperty().addListener((obs, oldv, newv) -> {
			save_button.setDisable(false);
			 if (!newv.matches("\\d*")) {
		            width_field.setText(newv.replaceAll("[^\\d]", ""));
		        }
		});
		
		height_field.textProperty().addListener((obs, oldv, newv) -> {
			save_button.setDisable(false);
			 if (!newv.matches("\\d*")) {
		            height_field.setText(newv.replaceAll("[^\\d]", ""));
		        }
		});
		
		save_button.setOnAction(ae -> {	
			
			save_button.setDisable(true);
			
			String screen_width = width_field.getText();
			String screen_height = height_field.getText();
			String paddle_size = current_settings != null ? current_settings.getPaddle_size() : null;
			String paddle_speed = current_settings != null ? current_settings.getPaddle_speed() : null;
			String ball_speed = current_settings != null ? current_settings.getBall_speed() : null;
			String ai_difficulty = current_settings != null ? current_settings.getAi_difficulty() : null;
			
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
