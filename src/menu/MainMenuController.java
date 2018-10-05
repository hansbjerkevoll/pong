package menu;

import java.io.File;
import java.net.URL;

import game.Pong;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenuController {
	
	public enum GameType {
		SINGLE_PLAYER,
		MULTI_PLAYER
	}
	
	private Stage stage;
	
	@FXML Label title_label;
	@FXML Button single_button, multi_button, settings_button;
	
	private Font pong_font, pong_title_font;
	
	public MainMenuController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		pong_font =  Font.loadFont(getClass().getResource("pong_font.ttf").toExternalForm(), 20);
		pong_title_font =  Font.loadFont(getClass().getResource("pong_font.ttf").toExternalForm(), 100);
		
		title_label.setFont(pong_title_font);
		single_button.setFont(pong_font);
		multi_button.setFont(pong_font);
		settings_button.setFont(pong_font);
		
		single_button.setOnAction(ae -> {
			load_pong(GameType.SINGLE_PLAYER);
		});
		
		multi_button.setOnAction(ae -> {
			load_pong(GameType.MULTI_PLAYER);
		});
		
		
	}
	
	private void load_pong(GameType gametype) {
		Pong pong = new Pong();
		pong.start(stage, gametype);
	}

}
