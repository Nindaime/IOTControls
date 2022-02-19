/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import javafx.scene.Node;

/**
 *
 * @author PETER-PC
 */
public class Event extends AnimationSubject{
    
    public Event(String subjectName, Node interactingObject, AnimationSubject.CP_Position position) {
        super(subjectName, interactingObject, position);
    }
    
}
