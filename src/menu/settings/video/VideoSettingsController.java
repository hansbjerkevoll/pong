package menu.settings.video;

import globals.Fonts;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VideoSettingsController {
	
	Stage stage;
	Scene mainmenuscene;
	
	@FXML Label title_label, width_label, height_label, version_label;
	@FXML TextField width_field, height_field;
	@FXML Button back_button;
	
	private boolean mousebtn_hover = false;
	private boolean mousebtn_hold = false;
	
	public VideoSettingsController(Stage stage) {
		this.stage = stage;
	}
	
	public void initialize() {
		
		title_label.setFont(Fonts.SECONDARY_TITLE_FONT);
		width_label.setFont(Fonts.SECONDARY_BUTTON_FONT);
		height_label.setFont(Fonts.SECONDARY_BUTTON_FONT);
		version_label.setFont(Fonts.VERSION_FONT);
		
		width_field.setFont(Fonts.SECONDARY_BUTTON_FONT);
		height_field.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
		back_button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
		style_button(back_button);
		
		width_field.setText("1080");
		height_field.setText("720");
		
		width_field.textProperty().addListener((obs, oldv, newv) -> {
			 if (!newv.matches("\\d*")) {
		            width_field.setText(newv.replaceAll("[^\\d]", ""));
		        }
		});
		
		back_button.setOnAction(ae -> {
			((Stage) back_button.getScene().getWindow()).setScene(mainmenuscene);
		});
		
	}
	
	private void style_button(Button button) {
		
		button.setFont(Fonts.SECONDARY_BUTTON_FONT);
		
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
		this.mainmenuscene = scene;
	}

}
