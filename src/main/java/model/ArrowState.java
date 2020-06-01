package model;

import javafx.scene.shape.Shape;

public class ArrowState implements State{
	
	ArrowShapeFactory arrowShapeFactory = new ArrowShapeFactory();
	
	double xBegin;
	double yBegin;
	double xEnd;
	double yEnd;
	
	String element;
	
	public double getxBegin() {
		return xBegin;
	}

	public void setxBegin(double xBegin) {
		this.xBegin = xBegin;
	}

	public double getyBegin() {
		return yBegin;
	}

	public void setyBegin(double yBegin) {
		this.yBegin = yBegin;
	}

	public double getxEnd() {
		return xEnd;
	}

	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	public double getyEnd() {
		return yEnd;
	}

	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}
	
	public void setElement(String color) {
		this.element = color;
	}
	

	public ShapeEMR drawShape() {
		ShapeEMR arrow;
		if (element.equals("Red")) {
			arrow = arrowShapeFactory.getArrow(ArrowShapeFactory.arrows.redArrow, xBegin, yBegin, xEnd, yEnd);
		} else {
			arrow = arrowShapeFactory.getArrow(ArrowShapeFactory.arrows.blackArrow, xBegin, yBegin, xEnd, yEnd);
		}
		return arrow;
	}
	
	@Override
	public String toString() {
		return "Arrow State";
	}

}
