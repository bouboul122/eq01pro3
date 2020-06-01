package model;


public abstract class Arrow extends ShapeEMR{
	
	double xEnd;
	double yEnd;
	
	Arrow(double xCoordinate, double yCoordinate, double xEnd, double yEnd, String mainColor, String borderColor){
		super(xCoordinate, yCoordinate, mainColor, borderColor);
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
}
