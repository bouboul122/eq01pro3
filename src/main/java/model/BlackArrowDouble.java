package model;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class BlackArrowDouble extends Arrow{

	BlackArrowDouble(double xCoordinate, double yCoordinate, double xEnd, double yEnd, String mainColor,
			String borderColor, ClipboardContent content, Dragboard db) {
		super(xCoordinate, yCoordinate, xEnd, yEnd, mainColor, borderColor, content, db);
		
	}

	@Override
	public Shape createShape() {
		double angle = Math.atan2 (yEnd - yCoordinate, xEnd - xCoordinate) + Math.PI;
	   
		   double offset = 10;
		
	       double x1 = xEnd + 6 * Math.cos(angle - Math.PI/6);
	       double y1 = yEnd + 6 * Math.sin(angle - Math.PI/6);
	       double x2 = xEnd + 6 * Math.cos(angle + Math.PI/6);
	       double y2 = yEnd + 6 * Math.sin(angle + Math.PI/6);
	       
	       double x3 = xCoordinate - 6 * Math.cos(angle - Math.PI/6);
	       double y3 = yCoordinate - 6 * Math.sin(angle - Math.PI/6);
	       double x4 = xCoordinate - 6 * Math.cos(angle + Math.PI/6);
	       double y4 = yCoordinate - 6 * Math.sin(angle + Math.PI/6);
	       
	       double offsetX1 = offset * Math.cos(angle);
	       double offsetY1 = offset * Math.sin(angle);
	       double offsetX2 = offset * Math.cos(angle);
	       double offsetY2 = offset * Math.sin(angle);
	       
	       Line mainLine = new Line();
	       Line secLine = new Line();
	       Line littleLine1 = new Line();
	       Line littleLine2 = new Line();
	       Line littleLine3 = new Line();
	       Line littleLine4 = new Line();
	       
	       mainLine.setStartX(xCoordinate);
	       mainLine.setStartY(yCoordinate);
	       mainLine.setEndX(xEnd);
	       mainLine.setEndY(yEnd);
	       
	       secLine.setStartX(xCoordinate);
	       secLine.setStartY(yCoordinate);
	       secLine.setEndX(xEnd);
	       secLine.setEndY(yEnd);
	       /*
	       secLine.setStartX(xCoordinate - offsetX1);
	       secLine.setStartY(yCoordinate + offsetY1);
	       secLine.setEndX(xEnd - offsetX2);
	       secLine.setEndY(yEnd + offsetY2);
	       */
	       littleLine1.setStartX(x1);
	       littleLine1.setStartY(y1);
	       littleLine1.setEndX(xEnd);
	       littleLine1.setEndY(yEnd);
	       
	       littleLine2.setStartX(x2);
	       littleLine2.setStartY(y2);
	       littleLine2.setEndX(xEnd);
	       littleLine2.setEndY(yEnd);
	       
	       littleLine3.setStartX(x3);
	       littleLine3.setStartY(y3);
	       littleLine3.setEndX(xCoordinate);
	       littleLine3.setEndY(yCoordinate);
	       
	       littleLine4.setStartX(x4);
	       littleLine4.setStartY(y4);
	       littleLine4.setEndX(xCoordinate);
	       littleLine4.setEndY(yCoordinate);
	       /*
	       littleLine3.setStartX(x3-offsetX1);
	       littleLine3.setStartY(y3+offsetY1);
	       littleLine3.setEndX(xCoordinate - offsetX1);
	       littleLine3.setEndY(yCoordinate + offsetY1);
	       
	       littleLine4.setStartX(x4 - offsetX1);
	       littleLine4.setStartY(y4 + offsetY1);
	       littleLine4.setEndX(xCoordinate - offsetX1);
	       littleLine4.setEndY(yCoordinate + offsetY2);
	       */
	       Shape firstUnion = Shape.union(mainLine, littleLine1);
	       Shape arrowHead = Shape.union(firstUnion, littleLine2);
	       Shape arrowHeadA = Shape.union(arrowHead, littleLine3);
	       Shape finalArrow = Shape.union(arrowHeadA, littleLine4);
	       //Shape finalfinalArrow = Shape.union(finalArrow, secLine);
	       //finalfinalArrow.setFill(Color.web(this.mainColor));
	       //finalfinalArrow.setStroke(Color.web(this.borderColor));
	       
	       finalArrow.setFill(Color.web(this.mainColor));
	       finalArrow.setStroke(Color.web(this.borderColor));
	       
	       return finalArrow;
		
	}

}
