package model;

import java.util.ArrayList;

import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele de coneption command avec ShapeCommand, ArrowCommand
 * 
 * Elle Fait aussi partie du modele dans MVC
 */

public interface Command {
	
	public ShapeEMR drawShape();
	public void deleteShape();
	public void removeFromList(ArrayList<Shape> shapeList);
	public void addToList(ArrayList<Shape> shapeList);
	public void redo();

}
