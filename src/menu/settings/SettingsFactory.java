package menu.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsFactory {
	
	private static final String APP_FOLDER_NAME = ".pong";
	private static final String SETTINGS_NAME = "settings.properties";
	
	/*
	 * Video Settings
	 */
	private static final String KEY_SCREEN_WIDTH = "screen_width";
	private static final String KEY_SCREEN_HEIGHT = "screen_height";
	
	/**
	 * Gameplay Settings
	 */
	private static final String KEY_PADDLE_SIZE = "paddle_size";
	private static final String KEY_PADDLE_SPEED = "paddle_speed";
	private static final String KEY_BALL_SPEED = "ball_speed";
	private static final String KEY_AI_DIFFICULTY = "ai_difficulty";
	
	
	private static Settings settings = null;
	private static File settingsFile = null;

	public static Settings getSettings() {
		if (settings == null) {
			File settingsFile = getSettingsFile();
			if (!settingsFile.exists()) {
				return null;
			}
			Properties p = new Properties();
			try (InputStream is = new FileInputStream(settingsFile)) {
				p.load(is);
				String screen_width = p.getProperty(KEY_SCREEN_WIDTH);
				String screen_height = p.getProperty(KEY_SCREEN_HEIGHT);
				String paddle_size = p.getProperty(KEY_PADDLE_SIZE);
				String paddle_speed = p.getProperty(KEY_PADDLE_SPEED);
				String ball_speed = p.getProperty(KEY_BALL_SPEED);
				String ai_difficulty = p.getProperty(KEY_AI_DIFFICULTY);
				settings = new Settings(screen_width, screen_height, paddle_size, paddle_speed, ball_speed, ai_difficulty);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return settings;
	}
	
	public static void saveSettings(Settings new_settings) {
		settings = new_settings;
	}
	
	public static void saveSettingsLocalFile(Settings settings) throws IOException {
		File logincredFile = getSettingsFile();
		File parent = logincredFile.getParentFile(); 
		if (parent != null && !parent.exists()) {
			if (!parent.mkdirs()) {
				throw new IOException("Couldn't create directory");
			}
		}
		
		try (FileOutputStream fos = new FileOutputStream(logincredFile)){
			Properties p = new Properties();
			if(settings.getScreen_width() != null) p.setProperty(KEY_SCREEN_WIDTH, settings.getScreen_width());
			if(settings.getScreen_height() != null) p.setProperty(KEY_SCREEN_HEIGHT, settings.getScreen_height());
			if(settings.getPaddle_size() != null) p.setProperty(KEY_PADDLE_SIZE, settings.getPaddle_size());
			if(settings.getPaddle_size() != null) p.setProperty(KEY_PADDLE_SPEED, settings.getPaddle_speed());
			if(settings.getBall_speed() != null) p.setProperty(KEY_BALL_SPEED, settings.getBall_speed());
			if(settings.getAi_difficulty() != null) p.setProperty(KEY_AI_DIFFICULTY, settings.getAi_difficulty());
			
			p.store(fos, " Pong Settings:");
		}
		
		saveSettings(settings);
	}
	
	public static void deleteFromDisk() {
		File settingsFile = getSettingsFile();
		if (settingsFile.exists() && !settingsFile.isDirectory()) {
			settingsFile.delete();
		}
	}
	
	public static boolean fileExists() {
		File settingsFile = getSettingsFile();
		return settingsFile.exists() && !settingsFile.isDirectory();
	}
	
	private static File getSettingsFile() {
		if (settingsFile == null) {
			String userhome = System.getProperty("user.home");
			Path appFolder = Paths.get(userhome, APP_FOLDER_NAME);
			settingsFile = appFolder.resolve(SETTINGS_NAME).toFile();
		}
		return settingsFile;
	}
	
	public static void setSettingsFile(File newFile) {
		settingsFile = newFile;
	}

}
