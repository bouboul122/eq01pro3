package model;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Shape;

public class ShapeFactory {
	
	ClipboardContent content;
	Dragboard db;
	
	public enum eshape {
		sourcePower,
		accumulationPower,
	};
	
	public ShapeFactory(ClipboardContent content, Dragboard db) {
		this.content = content;
		this.db = db;
		
	}
	
	public ShapeEMR getShape(eshape element, double xCoord, double yCoord, String mainColor, String borderColor)
	{
		
		ShapeEMR shape;
		//Shape shape;
		switch(element) {
		case sourcePower:
			shape = (new PowerSource(xCoord, yCoord, mainColor, borderColor, content, db));//.createShape();	
			break;
		case accumulationPower:
			shape = (new AccumulationPower(xCoord, yCoord, mainColor, borderColor, content, db));//.createShape();
			break;
		default:
			shape = null;
		}
		
		
		return shape;
	}

}
