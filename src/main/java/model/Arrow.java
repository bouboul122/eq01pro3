package model;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;

/*
 * Cette classe fait partie du modele de coneption Factory avec ShapeEMR, ShapeFactory, ArrowFactory et toutes les
 * formes conctretes
 * 
 * Elle Fait aussi partie du modele dans MVC
 */

public abstract class Arrow extends ShapeEMR{
	
	double xEnd;
	double yEnd;
	
	Arrow(double xCoordinate, double yCoordinate, double xEnd, double yEnd, String mainColor, String borderColor, ClipboardContent content, Dragboard db){
		super(xCoordinate, yCoordinate, mainColor, borderColor, content, db);
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
}
