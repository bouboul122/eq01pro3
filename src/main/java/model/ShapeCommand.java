package model;

import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ShapeCommand implements Command{
	
	Pane drawingBoard;
	String arrowColor;
	ShapeFactory shapeFactory;
	Stack<ShapeEMR> children;
	double xBegin;
	double xEnd;
	double yBegin;
	double yEnd;
	
	ShapeCommand(Pane drawingBoard, Stack<ShapeEMR> children){
		this.drawingBoard = drawingBoard;
		this.shapeFactory = new ShapeFactory();
		this.children = children;
	}
	
	@Override
	public ShapeEMR drawShape() {
		
		if (arrowColor.equals("PowerSource")) {
			return shapeFactory.getShape(ShapeFactory.eshape.sourcePower, this.xBegin, this.yBegin, "#98FB98", "#008000");
		}else {
			return shapeFactory.getShape(ShapeFactory.eshape.accumulationPower, this.xBegin, this.yBegin, "#FFD700", "#FF0000");
		}
	}
	
	@Override
	public Shape deleteShape(Shape shape) {

		drawingBoard.getChildren().remove(shape);
		return shape;
	}

}
