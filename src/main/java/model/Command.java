package model;

import java.util.ArrayList;

import javafx.scene.shape.Shape;

public interface Command {
	
	public ShapeEMR drawShape();
	public void deleteShape();
	public void removeFromList(ArrayList<Shape> shapeList);
	public void addToList(ArrayList<Shape> shapeList);
	public void redo();

}
