package menu;

import game.Pong;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	
	private Stage stage;
	
	@FXML Button single_button;
	
	public MainMenuController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		single_button.setOnAction(ae -> {
			Pong pong = new Pong();			
			pong.start(stage);
		});
		
		
	}

}
