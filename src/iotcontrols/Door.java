/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author PETER-PC
 */
public class Door extends SimObject{
    
    public Door(String subjectName, Rectangle door, CP_Position position) {
        super(subjectName, door, position);
//        door = simObject;
        setDoorConfig(door);
    }
    
    public DoorConfig getDoorConfig() {
        return dConfig;
    }
    
    public Rectangle getDoor(){
        return (Rectangle) super.getObject();
    }
    
//    private final Rectangle door;
    DoorConfig dConfig;

    private void setDoorConfig(Rectangle door) {
        switch (door.getId()) {
            case "Office1Door":
                dConfig = DoorConfig.OpenLeft144N;
                break;
            case "Office2Door":
                dConfig = DoorConfig.OpenLeft144N;
                break;
            case "ToiletDoor":
                dConfig = DoorConfig.OpenLeft36N;
                break;
            case "LectureRoom1Door":
                dConfig = DoorConfig.OpenRight36P;
                break;
            case "LectureRoom2Door":
                dConfig = DoorConfig.OpenRight36P;
                break;
            case "floorPlanDoor":
                dConfig = DoorConfig.OpenMainDoor;
                break;
        }
    }
    
    public enum DoorConfig {
        OpenRight36P(36, 10),
        OpenLeft36N(36, -10),
        OpenLeft144N(144, -10),
        OpenMainDoor(0, -50);

        private final int rotateAngle, translateVar;

        DoorConfig(int x, int y) {
            rotateAngle = x;
            translateVar = y;
        }

        public int getRotateAngle() {
            return rotateAngle;
        }

        public int getTranslateVariable() {
            return translateVar;
        }
    }
}
