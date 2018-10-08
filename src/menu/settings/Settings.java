package menu.settings;

public class Settings {

	/*
	 * Video settings
	 */
	private String screen_width;
	private String screen_height;
	
	/**
	 * Gameplay Settings
	 */
	private String paddle_size;
	private String paddle_speed;
	private String ball_speed;
	private String ai_difficulty;

	public Settings(String screen_width, String screen_height, String paddle_size, String paddle_speed, String ball_speed, String ai_difficulty) {
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		this.paddle_size = paddle_size;
		this.paddle_speed = paddle_speed;
		this.ball_speed = ball_speed;
		this.ai_difficulty = ai_difficulty;
	}
	
	public String getScreen_width() {
		return screen_width;
	}

	public void setScreen_width(String screen_width) {
		this.screen_width = screen_width;
	}

	public String getScreen_height() {
		return screen_height;
	}

	public void setScreen_height(String screen_height) {
		this.screen_height = screen_height;
	}

	public String getPaddle_size() {
		return paddle_size;
	}

	public void setPaddle_size(String paddle_size) {
		this.paddle_size = paddle_size;
	}

	public String getPaddle_speed() {
		return paddle_speed;
	}

	public void setPaddle_speed(String paddle_speed) {
		this.paddle_speed = paddle_speed;
	}

	public String getBall_speed() {
		return ball_speed;
	}

	public void setBall_speed(String ball_speed) {
		this.ball_speed = ball_speed;
	}

	public String getAi_difficulty() {
		return ai_difficulty;
	}

	public void setAi_difficulty(String ai_difficulty) {
		this.ai_difficulty = ai_difficulty;
	}
	
	
}
