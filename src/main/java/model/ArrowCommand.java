package model;

import java.util.Stack;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ArrowCommand implements Command{
	
	Pane drawingBoard;
	String arrowColor;
	ArrowShapeFactory arrowFactory;
	Stack<ShapeEMR> children;
	double xBegin;
	double xEnd;
	double yBegin;
	double yEnd;
	
	ArrowCommand(Pane drawingBoard, Stack<ShapeEMR> children, ClipboardContent content, Dragboard db){
		this.drawingBoard = drawingBoard;
		this.arrowFactory = new ArrowShapeFactory(content, db);
		this.children = children;
	}
	
	@Override
	public ShapeEMR drawShape() {
		
		if (arrowColor.equals("Red")) {
			return arrowFactory.getArrow(ArrowShapeFactory.arrows.redArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}else {
			return arrowFactory.getArrow(ArrowShapeFactory.arrows.blackArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}
	}
	
	@Override
	public void deleteShape() {

		drawingBoard.getChildren().remove(drawingBoard.getChildren().size() -1);
	}

}
