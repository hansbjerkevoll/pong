package menu;

import java.io.InputStream;
import game.Pong;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
		
		InputStream url = getClass().getResourceAsStream("pong_font.ttf");
		
		System.out.println(url);
		
		Font pong_font = Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 20);
		
		title_label.setFont(Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 100));		
		single_button.setFont(pong_font);
		multi_button.setFont(pong_font);
		settings_button.setFont(pong_font);
		version_label.setFont(Font.loadFont(getClass().getResourceAsStream("pong_font.ttf"), 12));
		
		single_button.setStyle("-fx-background-color: #FFFFFF;");
		multi_button.setStyle("-fx-background-color: #FFFFFF;");
		settings_button.setStyle("-fx-background-color: #FFFFFF;");
		
		single_button.setOnAction(ae -> {
			load_pong(GameType.SINGLE_PLAYER);
		});
		
		multi_button.setOnAction(ae -> {
			load_pong(GameType.MULTI_PLAYER);
		});
		
		button_style(single_button);
		button_style(multi_button);
		button_style(settings_button);
		
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
	}

}
