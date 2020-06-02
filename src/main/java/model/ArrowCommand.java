package model;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ArrowCommand implements Command{
	
	Pane drawingBoard;
	String arrowColor = "Red";
	ArrowShapeFactory arrowFactory;
	Shape shapeDrew;

	double xBegin;
	double xEnd;
	double yBegin;
	double yEnd;
	
	public ArrowCommand(Pane drawingBoard, String arrowColor, ClipboardContent content, Dragboard db){
		this.drawingBoard = drawingBoard;
		this.arrowFactory = new ArrowShapeFactory(content, db);
		this.arrowColor = arrowColor;
	}
	
	@Override
	public ShapeEMR drawShape() {
		ShapeEMR shape = null;
		if (arrowColor.equals("Red")) {
			shape =  arrowFactory.getArrow(ArrowShapeFactory.arrows.redArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}else {
			shape =  arrowFactory.getArrow(ArrowShapeFactory.arrows.blackArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}
		
		this.shapeDrew = shape.createShape();
		drawingBoard.getChildren().add(this.shapeDrew);
		return shape;
	}
	
	@Override
	public void deleteShape() {

		drawingBoard.getChildren().remove(drawingBoard.getChildren().size() -1);
	}
	
	@Override
	public void removeFromList(ArrayList<Shape> shapeList) {
		shapeList.remove(this.shapeDrew);
		
	}

	@Override
	public void addToList(ArrayList<Shape> shapeList) {
		shapeList.add(this.shapeDrew);
		
	}
	
	@Override
	public void redo()
	{
		drawingBoard.getChildren().add(this.shapeDrew);
	}
	
	public double getxBegin() {
		return xBegin;
	}

	public void setxBegin(double xBegin) {
		this.xBegin = xBegin;
	}

	public double getxEnd() {
		return xEnd;
	}

	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	public double getyBegin() {
		return yBegin;
	}

	public void setyBegin(double yBegin) {
		this.yBegin = yBegin;
	}

	public double getyEnd() {
		return yEnd;
	}

	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}

	public Shape getShapeDrew() {
		return shapeDrew;
	}

	public void setShapeDrew(Shape shapeDrew) {
		this.shapeDrew = shapeDrew;
	}


}
