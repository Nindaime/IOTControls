/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
/**
 *
 * @author PETER-PC
 */
public abstract class AnimationSubject {
    private final Point2D contactPoint;
    private final String subjectID;
    private final Node object;
    public enum CP_Position{LEFT, RIGHT, TOP, BOTTOM, LOWERLEFT, LOWERRIGHT, UPPERRIGHT, INTERNAL}

    public AnimationSubject(String subjectName, Node simObject, CP_Position position) {
        subjectID = subjectName;
        this.object = simObject;
        Bounds coord = simObject.getBoundsInParent();
        Bounds coord1 = simObject.getBoundsInLocal();
        switch(position){
            case LEFT: 
                contactPoint = new Point2D(coord.getMinX() - 10, coord.getMinY() + coord.getHeight() / 2);
                break;
            case RIGHT: 
                contactPoint = new Point2D(coord.getMaxX() + 10, coord.getMinY() + coord.getHeight() / 2);
                break;
            case TOP: 
                contactPoint = new Point2D(coord.getMinX() + coord.getWidth() / 2, coord.getMinY() - 10);
                break;
            case BOTTOM:
                contactPoint = new Point2D(coord.getMinX() + coord.getWidth() / 2, coord.getMinY() + 10);
                break;
            case LOWERLEFT:
                contactPoint = new Point2D(coord.getMinX() - 10, coord.getMaxY() - (coord1.getMaxY() * (3/5)));
                break;
            case LOWERRIGHT:
                contactPoint = new Point2D(coord.getMaxX() + 10, coord.getMaxY() - (coord1.getMaxY() * (3/5)));
                break;
            case UPPERRIGHT:
                contactPoint = new Point2D(coord.getMaxX() + 10, coord.getMaxY() - (coord1.getMaxY() * (4/5)));
                break;
            case INTERNAL:
                contactPoint = new Point2D(coord.getMinX() + coord.getWidth() / 2, coord.getMinY() + coord.getHeight() / 2);
                break;
            default: 
                contactPoint = null;
                break;
        }
        
    }

    public Point2D getContactPoint() {
        return contactPoint;
    }

    public String getSubjectID() {
        return subjectID;
    }
        
    protected Node getObject() {
        return object;
    }
    
    /**
     *The position of a simulation object contact point
     */
}
