package controller;

import java.util.ArrayList;
import java.util.Stack;

import javafx.event.Event;
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
import model.ArrowCommand;
import model.ArrowShapeFactory;
import model.Command;
import model.RedArrow;
import model.ShapeCommand;
import model.PowerSource;
import model.ShapeEMR;
import model.State;

public class EMRUndoDrawerController {
	
	ShapeCommand shapeCommand;
	ArrowCommand arrowCommand;
	
	Dragboard db;
	ClipboardContent content = new ClipboardContent();
	ArrayList<Shape> shapeList = new ArrayList<Shape>();
	Stack<Command> doneCommands= new Stack<Command>();
	Stack<Command> deletedCommands= new Stack<Command>();
	
	PowerSource powerSource = new PowerSource(0,0,"#98FB98", "#008000", content, db);
	Shape powerSourceShape = powerSource.createShape();
	AccumulationPower accumulationPower = new AccumulationPower(0,0,"#FFD700", "#FF0000", content, db);
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
		
		System.out.println("Initialising");
		shapeMenu.setExpandedPane(powerShapes);
		//state = new ShapeState();
		System.out.println(powerShapes.isExpanded());
		
		//Adding dans les VBox
		powerVBox.setSpacing(10);
	    powerVBox.setAlignment(Pos.TOP_CENTER);
	    
		powerVBox.getChildren().add(powerSourceShape);
		powerVBox.getChildren().add(accPowerShape);
		
		drawingBoard.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getGestureSource() != drawingBoard &&
		                event.getDragboard().hasString()) {
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		drawingBoard.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	Shape shapeDrew;
		    	ShapeEMR shapeEMR;
		        db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		        	if (content.getString().contentEquals("PowerSource"))
		        	{
		        		System.out.println("PowerSource");
		        		shapeCommand = new ShapeCommand("PowerSource",drawingBoard, content, db);
		        		shapeCommand.setxBegin(event.getX());
		        		shapeCommand.setyBegin(event.getY());
		        		shapeEMR = shapeCommand.drawShape();
		        		doneCommands.add(shapeCommand);
		        		shapeDrew = shapeCommand.getShapeDrew();
		        		shapeList.add(shapeDrew);
		        	} else if(content.getString().contentEquals("AccumulationPower"))
		        	{
		        		System.out.println("AccumulationPower");
		        		shapeCommand = new ShapeCommand("AccumulationPower",drawingBoard, content, db);
		        		shapeCommand.setxBegin(event.getX());
		        		shapeCommand.setyBegin(event.getY());
		        		shapeEMR = shapeCommand.drawShape();
		        		doneCommands.add(shapeCommand);
		        		shapeDrew = shapeCommand.getShapeDrew();
		        		shapeList.add(shapeDrew);
		        	}else {
		        		shapeDrew = null;
		        		shapeEMR = null;
		        	}
		        	
		        	shapeDrew.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							if (arrowShapes.isExpanded()){
								arrowCommand = new ArrowCommand(drawingBoard, arrowColor, content, db);
								if (!firstConfirmed) {
									System.out.println("First Confirmed");
									first = shapeEMR;
									firstConfirmed = !firstConfirmed;
								}else {
									System.out.println("second Confirmed");
									second = shapeEMR;
									if(first.getyCoordinate() > second.getyCoordinate() && (first.getyCoordinate()-second.getyCoordinate() > 30)) {
										System.out.println("First Top second bottom");
										double[] secondBottomAnchor = second.getBottomAnchor();
										double[] firstTopAnchor = first.getTopAnchor();									
										arrowCommand.setxBegin(firstTopAnchor[0]);
										arrowCommand.setyBegin(firstTopAnchor[1]);
										arrowCommand.setxEnd(secondBottomAnchor[0]);
										arrowCommand.setyEnd(secondBottomAnchor[1]);
										arrowCommand.drawShape();
										shapeList.add(arrowCommand.getShapeDrew());
										doneCommands.add(arrowCommand);
										
									} else if(first.getyCoordinate() < second.getyCoordinate() && second.getyCoordinate()-first.getyCoordinate() > 30) {
										System.out.println("First bottom second top");
										double[] secondTopAnchor = second.getTopAnchor();
										double[] firstBottomAnchor = first.getBottomAnchor();
										arrowCommand.setxBegin(firstBottomAnchor[0]);
										arrowCommand.setyBegin(firstBottomAnchor[1]);
										arrowCommand.setxEnd(secondTopAnchor[0]);
										arrowCommand.setyEnd(secondTopAnchor[1]);
										arrowCommand.drawShape();
										shapeList.add(arrowCommand.getShapeDrew());
										doneCommands.add(arrowCommand);
										
									} else if (first.getxCoordinate() < second.getxCoordinate()) {
										System.out.println("First right second left");
										double[] secondLeftAnchor = second.getLeftAnchor();
										double[] firstRightAnchor = first.getRightAnchor();
										arrowCommand.setxBegin(firstRightAnchor[0]);
										arrowCommand.setyBegin(firstRightAnchor[1]);
										arrowCommand.setxEnd(secondLeftAnchor[0]);
										arrowCommand.setyEnd(secondLeftAnchor[1]);
										arrowCommand.drawShape();
										shapeList.add(arrowCommand.getShapeDrew());
										doneCommands.add(arrowCommand);
										
									} else if(first.getxCoordinate() > second.getxCoordinate()) {
										System.out.println("First left second right");
										double[] secondRightAnchor = second.getRightAnchor();
										double[] firstLeftAnchor = first.getLeftAnchor();
										arrowCommand.setxBegin(firstLeftAnchor[0]);
										arrowCommand.setyBegin(firstLeftAnchor[1]);
										arrowCommand.setxEnd(secondRightAnchor[0]);
										arrowCommand.setyEnd(secondRightAnchor[1]);
										arrowCommand.drawShape();
										shapeList.add(arrowCommand.getShapeDrew());
										doneCommands.add(arrowCommand);
									} 
									
									firstConfirmed = false;
									secondConfirmed = false;
									
								}
								
								System.out.println("Clicked!");
							
							}
							
						}
		        		
		        	});
		        	
		           success = true;
		           
		        }
		        
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
	}
	
	@FXML public void redArrowButtonClicked()
	{
		if(arrowShapes.isExpanded()) {
			this.arrowColor = "Red";
		}
	}
	
	@FXML public void blackArrowClicked()
	{
		if(arrowShapes.isExpanded()) {
			this.arrowColor = "Black";
		}
	}
	
	@FXML
	public void undoButtonClicked()
	{
		if(powerShapes.isExpanded()) {
			if(doneCommands.size() > 0) {
			Command commandToUndo = doneCommands.pop();
			commandToUndo.deleteShape();
			deletedCommands.add(commandToUndo);
			
			}
			else {
				System.out.println("Stack empty");
			}
		}
	}
	
	@FXML
	public void redoButtonClicked()
	{
		if(powerShapes.isExpanded()) {
			if(deletedCommands.size() > 0) {
			Command commandToUndo = deletedCommands.pop();
			commandToUndo.drawShape();
			doneCommands.add(commandToUndo);
			
			}
			else {
				System.out.println("Stack empty");
			}
		}
	}
	
	
	
}