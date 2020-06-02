package model;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class PowerSource extends ShapeEMR{
	
	public PowerSource(double xCoordinate, double yCoordinate, String mainColor, String borderColor, ClipboardContent content, Dragboard db) {
		super(xCoordinate, yCoordinate, mainColor, borderColor, content, db);
	}

	@Override
	public Shape createShape() {
		
		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(this.xCoordinate);
		ellipse.setCenterY(this.yCoordinate);
		ellipse.setRadiusX(30);
		ellipse.setRadiusY(15);
		ellipse.setFill(Color.web(this.mainColor));
		ellipse.setStroke(Color.web(this.borderColor));
		
		this.topAnchor = new double[] {this.xCoordinate,this.yCoordinate - ellipse.getRadiusY()};
		this.bottomAnchor = new double[] {this.xCoordinate,this.yCoordinate + ellipse.getRadiusY()};
		this.leftAnchor = new double[] {this.xCoordinate - ellipse.getRadiusX(),this.yCoordinate};
		this.rightAnchor = new double[] {this.xCoordinate + ellipse.getRadiusX(),this.yCoordinate};
		
		ellipse.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				 /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        db = ellipse.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */;
		        content.putString("PowerSource");
		        db.setContent(content);
		        
		        event.consume();
				
			}
			
		});
		
		
		return ellipse;
	}

}
