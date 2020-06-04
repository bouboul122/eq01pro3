package model;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele de coneption Factory avec ShapeEMR, ArrowFactory et toutes les
 * formes conctretes
 * 
 * Elle Fait aussi partie du modele dans MVC
 */

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
		switch(element) {
		case sourcePower:
			shape = (new PowerSource(xCoord, yCoord, mainColor, borderColor, content, db));	
			break;
		case accumulationPower:
			shape = (new AccumulationPower(xCoord, yCoord, mainColor, borderColor, content, db));
			break;
		default:
			shape = null;
		}
		
		
		return shape;
	}

}
