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
public class IoTDevice extends SimObject{
    Double powerRating;
    boolean state;
    
    public IoTDevice(String subjectName, Node object, AnimationSubject.CP_Position position){
        super(subjectName, object, position);
        state = false;
    }
    
    public boolean toggle(){
        state = !state;
        return state;
    }
    
    public boolean getState(){
        return state;
    }
    
    public void sleep(){}
    
//    public static IoTDevice stringToRoom(String name) {
//        Room rR;
//        for (Room r : rooms) {
//            if (name.contains(r.getRoom().getId())) {
//                return r;
//            }
//        }
//        return null;
//    }
        
}
