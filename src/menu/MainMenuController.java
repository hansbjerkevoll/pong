package menu;

import java.io.IOException;

import game.Pong;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import menu.settings.SettingsController;

public class MainMenuController {
	
	public enum GameType {
		SINGLE_PLAYER,
		MULTI_PLAYER
	}
	
	private Stage stage;
	
	@FXML Label title_label, version_label;
	@FXML Button single_button, multi_button, settings_button;
	
	
	public MainMenuController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		

		title_label.setFont(Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 100));	
		version_label.setFont(Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 12));
		
		Font button_font = Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 20);			
		single_button.setFont(button_font);
		multi_button.setFont(button_font);
		settings_button.setFont(button_font);
		
		single_button.setStyle("-fx-background-color: #FFFFFF;");
		multi_button.setStyle("-fx-background-color: #FFFFFF;");
		settings_button.setStyle("-fx-background-color: #FFFFFF;");
		
		button_style(single_button);
		button_style(multi_button);
		button_style(settings_button);
		
		single_button.setOnAction(ae -> {
			load_pong(GameType.SINGLE_PLAYER);
		});
		
		multi_button.setOnAction(ae -> {
			load_pong(GameType.MULTI_PLAYER);
		});
		
		settings_button.setOnAction(ae -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("settings/Settings.fxml"));
				SettingsController controller = new SettingsController(stage);
				controller.setMainMenuScene(settings_button.getScene());
				loader.setController(controller);
				Parent root = loader.load();
				Scene s = new Scene(root);
				stage.setScene(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	private void load_pong(GameType gametype) {
		Pong pong = new Pong();
		pong.start(stage, gametype);
	}
	
	private void button_style(Button button) {
		button.setOnMouseEntered(me -> {
			button.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #555555;");			
		});
		
		button.setOnMouseExited(me -> {
			button.setStyle("-fx-background-color: #FFFFFF;");
		});
		
		button.setOnMousePressed(mc -> {
			button.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFFFFF;");
		});
		
		button.setOnMouseReleased(mr -> {
			button.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #555555;");	
		});
	}

}
