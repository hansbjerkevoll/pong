package menu.settings;

import java.io.IOException;

import globals.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import menu.MainMenuController;
import menu.settings.gameplay.GameplaySettingsController;
import menu.settings.video.VideoSettingsController;

public class SettingsMenuController {
	
	Stage stage;
	
	@FXML Label title_label, version_label;
	@FXML Button video_button, gameplay_button, back_button;
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public SettingsMenuController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		title_label.setFont(Fonts.SECONDARY_TITLE_FONT);
		video_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		gameplay_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		back_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		version_label.setFont(Fonts.VERSION_FONT);
		
		video_button.setStyle("-fx-background-color: #FFFFFF;");
		gameplay_button.setStyle("-fx-background-color: #FFFFFF;");
		back_button.setStyle("-fx-background-color: #FFFFFF;");
		
		video_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("video/VideoSettings.fxml"));
				VideoSettingsController controller = new VideoSettingsController(stage);
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);;
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		gameplay_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay/GameplaySettings.fxml"));
				GameplaySettingsController controller = new GameplaySettingsController(stage);
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);;
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		back_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainMenu.fxml"));
				MainMenuController controller = new MainMenuController(stage);
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		style_button(video_button);
		style_button(gameplay_button);
		style_button(back_button);
		
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
