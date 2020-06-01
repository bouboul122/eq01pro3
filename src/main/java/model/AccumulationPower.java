package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AccumulationPower extends ShapeEMR{
	
	public AccumulationPower(double xCoordinate, double yCoordinate, String mainColor, String borderColor) {
		super(xCoordinate, yCoordinate, mainColor, borderColor);
	}

	@Override
	public Shape createShape() {
		Rectangle element = new Rectangle();
		element.setX(this.xCoordinate);
		element.setY(this.yCoordinate);
		element.setWidth(19.75);
		element.setHeight(40);
		element.setFill(Color.web(this.mainColor));
		element.setStroke(Color.web(this.borderColor));
		
		Line diagonal = new Line();
		diagonal.setStrokeWidth(0.25);
		diagonal.setStartX(this.xCoordinate);
		diagonal.setStartY(this.yCoordinate);
		diagonal.setEndX(this.xCoordinate + element.getWidth());
		diagonal.setEndY(this.yCoordinate + element.getHeight());
		diagonal.setFill(Color.web(this.borderColor));
		
		Shape shape = Shape.subtract(element, diagonal);
		shape.setFill(Color.web(this.mainColor));
		shape.setStroke(Color.web(this.borderColor));
		
		this.topAnchor = new double[] {this.xCoordinate + element.getWidth()/2,this.yCoordinate};
		this.bottomAnchor = new double[] {this.xCoordinate + element.getWidth()/2,this.yCoordinate + element.getHeight()};
		this.leftAnchor = new double[] {this.xCoordinate,this.yCoordinate + element.getHeight()/2};
		this.rightAnchor = new double[] {this.xCoordinate + element.getWidth(),this.yCoordinate + element.getHeight()/2};
		
		return shape;
	}
}
