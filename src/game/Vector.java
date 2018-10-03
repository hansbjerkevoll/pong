package game;

public class Vector {
	
	double abs_value;
	double x_value;
	double y_value;
	double angle; // Radians
	
	public Vector(double abs_value, double angle, boolean x_dir, boolean y_dir) {	
		this.abs_value = abs_value;
		this.angle = angle;
		this.x_value = Math.cos(angle) * abs_value;
		this.y_value = Math.sin(angle) * abs_value;
		
		if(x_dir) x_value *= -1;
		if(y_dir) y_value *= -1;
		
	}
	
	public void setAngle(double angle) {
		this.angle = angle;		
		this.x_value = Math.cos(angle) * abs_value;
		this.y_value = Math.sin(angle) * abs_value;
	}

}
