package model;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Shape;

public class ArrowShapeFactory {
	ShapeEMR shape;
	ClipboardContent content;
	Dragboard db;
	
	public enum arrows {
		redArrow, 
		blackArrow};
		
	public ArrowShapeFactory(ClipboardContent content, Dragboard db) {
		this.content = content;
		this.db = db;
	}
	
	public ShapeEMR getArrow(arrows arrow, double xBegin, double yBegin, double xEnd, double yEnd) {
		switch(arrow) {
		case redArrow:
			shape = new RedArrow(xBegin, yBegin, xEnd, yEnd, "#FF0000", "#FF0000", content, db);
			break;
		case blackArrow:
			shape = new BlackArrowDouble(xBegin, yBegin, xEnd, yEnd, "#000000", "#000000", content, db);
			break;
		default:
			shape = null;		
		}	
		return shape;
	}

}
