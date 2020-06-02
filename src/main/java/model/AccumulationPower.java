package model;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AccumulationPower extends ShapeEMR{
	
	public AccumulationPower(double xCoordinate, double yCoordinate, String mainColor, String borderColor, ClipboardContent content, Dragboard db) {
		super(xCoordinate, yCoordinate, mainColor, borderColor, content, db);
	}

	@Override
	public Shape createShape() {
		Rectangle element = new Rectangle();
		element.setX(this.xCoordinate);
		element.setY(this.yCoordinate);
		element.setWidth(19.75);
		element.setHeight(40);
		element.setFill(Color.web(this.mainColor));
		element.setStroke(Color.web(this.borderColor));
		
		Line diagonal = new Line();
		diagonal.setStrokeWidth(0.25);
		diagonal.setStartX(this.xCoordinate);
		diagonal.setStartY(this.yCoordinate);
		diagonal.setEndX(this.xCoordinate + element.getWidth());
		diagonal.setEndY(this.yCoordinate + element.getHeight());
		diagonal.setFill(Color.web(this.borderColor));
		
		Shape shape = Shape.subtract(element, diagonal);
		shape.setFill(Color.web(this.mainColor));
		shape.setStroke(Color.web(this.borderColor));
		
		this.topAnchor = new double[] {this.xCoordinate + element.getWidth()/2,this.yCoordinate};
		this.bottomAnchor = new double[] {this.xCoordinate + element.getWidth()/2,this.yCoordinate + element.getHeight()};
		this.leftAnchor = new double[] {this.xCoordinate,this.yCoordinate + element.getHeight()/2};
		this.rightAnchor = new double[] {this.xCoordinate + element.getWidth(),this.yCoordinate + element.getHeight()/2};
		
		shape.setOnDragDetected( new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				 /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        db = shape.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        content.putString("AccumulationPower");
		        db.setContent(content);
		        
		        event.consume();
				
			}
			
		});
		
		return shape;
	}
}
