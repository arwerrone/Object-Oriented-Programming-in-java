package workshop3;

public abstract class GeometricObject{
	
	private String color = "white";
	private boolean filled;
	
	protected GeometricObject() {};
	
	protected GeometricObject(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
		//setColor(color);
		//setFilled(filled);
		
	};
	
	public String getColor() {
		return color;
	};
	
	public boolean getFilled() {
		return filled;
	};
	
	public void setColor(String color) {
		this.color = color;
	};
	
	public void setFilled(boolean filled) {
		this.filled = filled;
	};
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
}