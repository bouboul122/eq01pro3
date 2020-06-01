package model;

 

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

 

public class RedArrow extends Arrow{

 

    public RedArrow(double xBegin, double yBegin, double xEnd, double yEnd, String mainColor, String borderColor) {
        super(xBegin, yBegin, xEnd, yEnd, mainColor, borderColor);
    }
    
    final double ARROWHEAD_ANGLE = Math.toRadians(20);
    final double ARROWHEAD_LENGTH = 10;    
    @Override
    public Shape createShape() {
    	
    	
    	
       double angle = Math.atan2 (yEnd - yCoordinate, xEnd - xCoordinate) + Math.PI;
     
       double x1 = xEnd + 6 * Math.cos(angle - Math.PI/6);
       double y1 = yEnd + 6 * Math.sin(angle - Math.PI/6);
       double x2 = xEnd + 6 * Math.cos(angle + Math.PI/6);
       double y2 = yEnd + 6 * Math.sin(angle + Math.PI/6);
       
       Line mainLine = new Line();
       Line littleLine1 = new Line();
       Line littleLine2 = new Line();
       mainLine.setStartX(xCoordinate);
       mainLine.setStartY(yCoordinate);
       mainLine.setEndX(xEnd);
       mainLine.setEndY(yEnd);
       
       littleLine1.setStartX(x1);
       littleLine1.setStartY(y1);
       littleLine1.setEndX(xEnd);
       littleLine1.setEndY(yEnd);
       
       littleLine2.setStartX(x2);
       littleLine2.setStartY(y2);
       littleLine2.setEndX(xEnd);
       littleLine2.setEndY(yEnd);
       
       Shape firstUnion = Shape.union(mainLine, littleLine1);
       Shape arrow = Shape.union(firstUnion, littleLine2);
       arrow.setFill(Color.web(this.mainColor));
       arrow.setStroke(Color.web(this.borderColor));
       
       return arrow;
                
    }
    
}
    