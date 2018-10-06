package menu;

import java.io.IOException;

import game.PongGame;
import globals.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import menu.settings.SettingsController;

public class MainMenuController {
	
	public enum GameType {
		SINGLE_PLAYER,
		MULTI_PLAYER
	}
	
	private Stage stage;
	
	@FXML VBox root;
	@FXML Label title_label, version_label;
	@FXML Button single_button, multi_button, settings_button, exit_button;
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public MainMenuController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {

		title_label.setFont(Fonts.MAIN_TITLE_FONT);	
		version_label.setFont(Fonts.VERSION_FONT);		
		single_button.setFont(Fonts.MAIN_BUTTON_FONT);
		multi_button.setFont(Fonts.MAIN_BUTTON_FONT);
		settings_button.setFont(Fonts.MAIN_BUTTON_FONT);
		
		single_button.setStyle("-fx-background-color: #FFFFFF;");
		multi_button.setStyle("-fx-background-color: #FFFFFF;");
		settings_button.setStyle("-fx-background-color: #FFFFFF;");
		
		style_button(single_button);
		style_button(multi_button);
		style_button(settings_button);
		style_button(exit_button);
		
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
				loader.setController(controller);
				Parent root = loader.load();
				stage.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		exit_button.setOnAction(ae -> {
			stage.close();
		});
		
	}
	
	private void load_pong(GameType gametype) {
		PongGame pong = new PongGame();
		pong.start(stage, gametype);
	}
	
	private void style_button(Button button) {
		
		button.setFont(Fonts.MAIN_BUTTON_FONT);
		
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
