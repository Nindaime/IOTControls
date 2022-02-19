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
public class SimObject extends AnimationSubject{
    public Point2D[] escapePoints = new Point2D[4];

    public SimObject(String subjectName, Node simObject, AnimationSubject.CP_Position position) {
        super(subjectName, simObject, position);
        Bounds b = simObject.getBoundsInParent();
        escapePoints[0] = new Point2D(b.getMinX() - 8, b.getMinY() - 8);
        escapePoints[1] = new Point2D(b.getMaxX() + 8, b.getMinY() - 8);
        escapePoints[2] = new Point2D(b.getMinX() - 8, b.getMaxY() + 8);
        escapePoints[3] = new Point2D(b.getMaxX() + 8, b.getMaxY() + 8);
    }
    
    protected Node getSimObject(){
        return getObject();
    }
    
}
