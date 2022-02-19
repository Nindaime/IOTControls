/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
/**
 *
 * @author PETER-PC
 */
public class Room extends AnimationSubject{
    private final Door door;
    private final ArrayList<SimObject> roomObjects = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    
    public Room(String roomName, Pane location, Door door, AnimationSubject.CP_Position position, SimObject... roomObjects) {
        super(roomName, location, position);
//        door = (Rectangle) location.lookup("#"+location.getId()+"Door");
//        setDoorConfig(door);
        this.door = door;
        this.roomObjects.addAll(FXCollections.observableArrayList(roomObjects));
        rooms.add(this);
    }
//    
//    private void setDoorConfig(Rectangle door){
//        switch(door.getId()){
//            case "Office1Door": dConfig = DoorConfig.OpenLeft144N; break;
//            case "Office2Door": dConfig = DoorConfig.OpenLeft144N; break;
//            case "ToiletDoor": dConfig = DoorConfig.OpenLeft36N; break;
//            case "LectureRoom1Door": dConfig = DoorConfig.OpenRight36P; break;
//            case "LectureRoom2Door": dConfig = DoorConfig.OpenRight36P; break;
//            case "floorPlanDoor": dConfig = DoorConfig.OpenMainDoor; break;
//        }
//    }
    
//    public DoorConfig getDoorConfig(){
//        return dConfig;
//    }
    
    public ArrayList<SimObject> getRoomObjects(){
        return roomObjects;
    }
    
    public Pane getRoom(){
        return (Pane)getObject();
    }
    
    public static Room stringToRoom(String name){
        Room rR;
        for(Room r: rooms){
            if(name.contains(r.getRoom().getId()))
                return r;
        }
        return null;
    }
    
    public Door getDoor(){
        return door;
    }

//    public enum DoorConfig{
//        OpenRight36P(36, 10),
//        OpenLeft36N(36, -10),
//        OpenLeft144N(144, -10), 
//        OpenMainDoor(0, -50);
//        
//        private final int rotateAngle, translateVar;
//        
//        private DoorConfig(int x, int y){
//            rotateAngle = x;
//            translateVar = y;
//        }
//        
//        public int getRotateAngle(){
//            return rotateAngle;
//        }
//        
//        public int getTranslateVariable(){
//            return translateVar;
//        }
//    }
}
