package model;

import javafx.scene.shape.Shape;

public class ShapeState implements State{
	
	String element;
	double xBegin;
	double yBegin;
	double xEnd;
	double yEnd;
	
	
	ShapeFactory shapeFactory = new ShapeFactory();

	@Override
	public ShapeEMR drawShape() {
		if (element.equals("PowerSource")) {
			return shapeFactory.getShape(ShapeFactory.eshape.sourcePower, xBegin, yBegin, "#98FB98",  "#008000");
		} else if (element.contentEquals("AccumulationPower")) {
			return shapeFactory.getShape(ShapeFactory.eshape.accumulationPower, xBegin, yBegin, "#FFD700",  "#FF0000");
		} else {
			return null;
		}
	}
	

	public void setElement(String element)
	{
		this.element = element;
	}
	
	public void setxBegin(double xCoord) {
		this.xBegin = xCoord;
	}
	
	public void setxEnd(double xCoord) {
		this.xEnd = xCoord;
	}
	
	public void setyBegin(double yCoord) {
		this.yBegin = yCoord;
	}
	
	public void setyEnd(double yCoord) {
		this.yEnd = yCoord;
	}

}
