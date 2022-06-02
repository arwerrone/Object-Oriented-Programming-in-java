package workshop3;

public class Triangle extends GeometricObject{
	private double side1 = 1.0, side2 = 1.0, side3 = 1.0;
	
	public Triangle() {
		//since creating a Triangle is setting values to 1, I don`t think is necessary to re-write it
	};
	
	public Triangle(double side1, double side2, double side3) throws TriangleException {
		this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        //task2
        checkTriangle();
	};
	
    public double getSide1() {
        return side1;
    };
    
    public double getSide2() {
        return side2;
    };
    
    public double getSide3() {
        return side3;
    };
	
	 @Override
	 public double getArea() {
		 double s = ((side1 + side2 + side3) / 2);
		 double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3) );
		 return area;
	 };

	 @Override
	 public double getPerimeter() {
		 double p = side1 + side2 + side3;
		 return p;
	 };
	 
	 public String toString() {
		 String s1 = "The triangle have the colour of: "+ this.getColor() + "\nIs it filled? " + this.getFilled();
		 String s2 = "\nSide 1: " + side1 + "\nSide 2: " + side2 + "\nSide 3: " + side3;
		 String s3 = "\nArea: " + this.getArea() + "\nPerimeter: " + this.getPerimeter();
		 return s1 + s2 + s3;
	 };
	 
	 /*TASK 2 STARTS HERE*/
	 
	 public void checkTriangle() throws TriangleException {
		 if ((this.side1 + this.side2 > this.side3) && (this.side1 + this.side3 > this.side2) && (this.side2 + this.side3 > this.side1)) {}
		 else {
			 throw new TriangleException(this.side1, this.side2, this.side3);
		 }
	 };
	
	 @SuppressWarnings("serial") // to suppress the warning :D
	public class TriangleException extends Exception {
		 private double exSide1, exSide2, exSide3;
		 
		 public TriangleException(double exS1, double exS2, double exS3) {
			 this.exSide1 = exS1;
			 this.exSide2 = exS2;
			 this.exSide3 = exS3;
		 };
		 
		 public double getExSide1() {return exSide1;};
		 
		 public double getExSide2() {return exSide2;};
		 
		 public double getExSide3() {return exSide3;};
		 
		 public double getExArea() {
			 double s = ((exSide1 + exSide2 + exSide3) / 2);
			 double area = Math.sqrt(s * (s - exSide1) * (s - exSide2) * (s - exSide3) );
			 return area;
		 };
		 
		 public double getExPerimeter() {
			 double p = exSide1 + exSide2 + exSide3;
			 return p;
		 };
		 
		 public String toExString() {
			 String s1 = "This Triangle does not follow the rule, but here is the results:";
			 String s2 = "\nSide 1: " + exSide1 + "\nSide 2: " + exSide2 + "\nSide 3: " + exSide3;
			 String s3 = "\nArea: " + this.getExArea() + "\nPerimeter: " + this.getExPerimeter();
			 return s1 + s2 + s3;
		 };
		 
	 }
	 
	 
	 
}