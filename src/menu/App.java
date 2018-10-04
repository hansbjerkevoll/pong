package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
    public static void main(String[] args) {
        launch();
    }

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		MainMenuController controller = new MainMenuController(stage);
		loader.setController(controller);
		Parent root = loader.load();
		Scene s = new Scene(root);
		stage.setScene(s);
		stage.setTitle("PONG");
		stage.setResizable(false);
		stage.show();		
	}

}
