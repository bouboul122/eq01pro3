package model;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele de coneption Factory avec Arrowm, ShapeEMR, ShapeFactory, ArrowFactory et toutes les
 * formes conctretes
 * 
 * Elle Fait aussi partie du modele dans MVC
 */

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

		        db = ellipse.startDragAndDrop(TransferMode.ANY);
		        

		        content.putString("PowerSource");
		        db.setContent(content);
		        
		        event.consume();
				
			}
			
		});
		
		
		return ellipse;
	}

}
