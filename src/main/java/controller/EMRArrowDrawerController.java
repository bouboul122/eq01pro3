package controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import model.AccumulationPower;
import model.ArrowShapeFactory;
import model.ArrowState;
import model.RedArrow;
import model.PowerSource;
import model.ShapeEMR;
import model.ShapeState;
import model.State;

public class EMRArrowDrawerController {
	
	State state;
	Dragboard db;
	ClipboardContent content = new ClipboardContent();
	ArrayList<ShapeEMR> shapeList = new ArrayList<ShapeEMR>();
	
	PowerSource powerSource = new PowerSource(0,0,"#98FB98", "#008000");
	Shape powerSourceShape = powerSource.createShape();
	AccumulationPower accumulationPower = new AccumulationPower(0,0,"#FFD700", "#FF0000");
	Shape accPowerShape = accumulationPower.createShape();
	
	boolean firstConfirmed = false;
	boolean secondConfirmed = false;
	String arrowColor;
	ShapeEMR first;
	ShapeEMR second;
	
	@FXML
	TitledPane powerShapes;
	@FXML
	TitledPane arrowShapes;
	@FXML
	Button stateButton;
	@FXML
	Label stateLabel;
	@FXML
	Accordion shapeMenu;
	@FXML
	VBox powerVBox;
	@FXML 
	VBox arrowVBox;
	@FXML
	Pane drawingBoard;
	@FXML
	Button redArrowButton;
	@FXML
	Button blackArrowButton;
	
	
	public void initialize()
	{
		//RedArrow arrow = new RedArrow(300, 300, 350, 350, "#FF0000", "#FF0000");
		//Shape arrowShape = arrow.createShape();
		
		//Shape arrow = arrowFactory.getArrow(ArrowShapeFactory.arrows.redArrow, 300, 300, 350, 350).createShape();
		
		//drawingBoard.getChildren().add(state.drawShape().createShape());
		
		System.out.println("Initialising");
		shapeMenu.setExpandedPane(powerShapes);
		state = new ShapeState();
		System.out.println(powerShapes.isExpanded());
		
		//Adding dans les VBox
		powerVBox.setSpacing(10);
	    powerVBox.setAlignment(Pos.TOP_CENTER);
	    
		powerVBox.getChildren().add(powerSourceShape);
		powerVBox.getChildren().add(accPowerShape);

		powerSourceShape.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        db = powerSourceShape.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        content.putString("PowerSource");
		        db.setContent(content);	
		        
		        event.consume();
		    }
		});
		
		accPowerShape.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        db = accPowerShape.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        content = new ClipboardContent();
		        content.putString("AccumulationPower");
		        db.setContent(content);	
		        
		        event.consume();
		    }
		});
		
		drawingBoard.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != drawingBoard &&
		                event.getDragboard().hasString()) {
		        	System.out.println("Dragging");
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		drawingBoard.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	ShapeEMR shape;
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        boolean success = false;
		        if (db.hasString()) {
		        	if (content.getString().equals("PowerSource")) {
		        		state.setElement("PowerSource");
		        		state.setxBegin(event.getX());
		        		state.setyBegin(event.getY());
		        		shape = state.drawShape();
		        		//Shape shapeToDraw = shape.createShape();
		        		//drawingBoard.getChildren().add(shapeToDraw);
		        	} else if(content.getString().equals("AccumulationPower")) {
		        		state.setElement("AccumulationPower");
		        		state.setxBegin(event.getX());
		        		state.setyBegin(event.getY());
		        		shape = state.drawShape();
		        	}else {
		        		shape = null;
		        	}
		        	Shape shapeToDraw = shape.createShape();
	        		drawingBoard.getChildren().add(shapeToDraw);
		        	
		        	shapeList.add(shape);
		        	shapeToDraw.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							if (arrowShapes.isExpanded()) {
								if (!firstConfirmed) {
									first = shape;
									firstConfirmed = !firstConfirmed;
									System.out.println("First changed");
								} else {
									second = shape;
									System.out.println("Draw a line");
									Line line = new Line();
									if(first.getyCoordinate() > second.getyCoordinate() && (first.getyCoordinate()-second.getyCoordinate() > 30)) {
										System.out.println("First Top second bottom");
										double[] secondBottomAnchor = second.getBottomAnchor();
										double[] firstTopAnchor = first.getTopAnchor();									
										state.setElement("Red");
										state.setxBegin(firstTopAnchor[0]);
										state.setyBegin(firstTopAnchor[1]);
										state.setxEnd(secondBottomAnchor[0]);
										state.setyEnd(secondBottomAnchor[1]);
										drawingBoard.getChildren().add(state.drawShape().createShape());
										
									} else if(first.getyCoordinate() < second.getyCoordinate() && second.getyCoordinate()-first.getyCoordinate() > 30) {
										System.out.println("First bottom second top");
										double[] secondTopAnchor = second.getTopAnchor();
										double[] firstBottomAnchor = first.getBottomAnchor();
										state.setElement("Red");
										state.setxBegin(firstBottomAnchor[0]);
										state.setyBegin(firstBottomAnchor[1]);
										state.setxEnd(secondTopAnchor[0]);
										state.setyEnd(secondTopAnchor[1]);
										drawingBoard.getChildren().add(state.drawShape().createShape());
										
									} else if (first.getxCoordinate() < second.getxCoordinate()) {
										System.out.println("First right second left");
										double[] secondLeftAnchor = second.getLeftAnchor();
										double[] firstRightAnchor = first.getRightAnchor();
										state.setElement("Red");
										state.setxBegin(firstRightAnchor[0]);
										state.setyBegin(firstRightAnchor[1]);
										state.setxEnd(secondLeftAnchor[0]);
										state.setyEnd(secondLeftAnchor[1]);
										drawingBoard.getChildren().add(state.drawShape().createShape());
										
									} else if(first.getxCoordinate() > second.getxCoordinate()) {
										System.out.println("First left second right");
										double[] secondRightAnchor = second.getRightAnchor();
										double[] firstLeftAnchor = first.getLeftAnchor();
										state.setElement("Red");
										state.setxBegin(firstLeftAnchor[0]);
										state.setyBegin(firstLeftAnchor[1]);
										state.setxEnd(secondRightAnchor[0]);
										state.setyEnd(secondRightAnchor[1]);
										drawingBoard.getChildren().add(state.drawShape().createShape());
									} 
									
									firstConfirmed = false;
									secondConfirmed = false;
								}
							
								System.out.println(event.getX());
								System.out.println(event.getY());
							}
						}	
		        	});

		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
	}
	
	
	@FXML
	public void goToPowerState()
	{
		state = new ShapeState();
		stateLabel.setText("State: Shape State");
		System.out.println("State: Shape State");
	}
	
	@FXML
	public void goToArrowState()
	{
		state = new ArrowState();
		stateLabel.setText("State: Arrow State");
		System.out.println("State: Arrow State");
	}
	
	@FXML
	public void drawRed() {
		if (arrowShapes.isExpanded()) {
		stateLabel.setText("State: Red Arrow");
		arrowColor = "Red";
		}
	}
	
	@FXML
	public void drawBlack() {
		if (arrowShapes.isExpanded()) {
			stateLabel.setText("State: Black Arrow");
			arrowColor = "Black";
		}
	}
}
