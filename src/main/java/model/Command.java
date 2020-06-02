package model;

import javafx.scene.shape.Shape;

public interface Command {
	
	public ShapeEMR drawShape();
	public void deleteShape();

}
