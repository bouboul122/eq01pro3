package model;

import javafx.scene.shape.Shape;

public interface Command {
	
	public ShapeEMR drawShape();
	public Shape deleteShape(Shape shape);

}
