package globals;

import javafx.scene.text.Font;

public class Fonts {
	
	private static final String font_file = "Square.ttf";
	
	public static final Font MAIN_TITLE_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 120);
	public static final Font SECONDARY_TITLE_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 80);
	
	public static final Font MAIN_BUTTON_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 25);
	public static final Font SECONDARY_BUTTON_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 20);
	
	public static final Font VERSION_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 15);

	public static final Font SCORE_FONT = Font.loadFont(Fonts.class.getResourceAsStream(font_file), 80);
	
}
