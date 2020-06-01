package model;

import javafx.scene.shape.Shape;

public interface State {
	
	
	public void setElement(String element);
	
	public ShapeEMR drawShape();
	
	public void setxBegin(double xCoord);
	public void setyBegin(double yCoord);
	public void setxEnd(double xCoord);
	public void setyEnd(double yCoord);

}
