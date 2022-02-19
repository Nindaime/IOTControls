/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author PETER-PC
 */
public class LectureRoomSit extends SimObject{
    private boolean vacancy;
    
    public LectureRoomSit(String subjectName, Rectangle simObject, CP_Position position) {
        super(subjectName, simObject, position);
        
    }
    
    public Animation setVacancy(Actor actor, boolean enterSit){
        TranslateTransition tT ;
//        if(!actor.intersects(this.getSit().getBoundsInLocal())){
        if(enterSit){
            tT = new TranslateTransition(Duration.millis(300), actor);
            tT.setByY(-10);
//            tT.setToY(this.getSit().getBoundsInLocal().getMaxX());
//            tT.setToX(this.getSit().getBoundsInLocal().getMaxX());
//            AnimationSequence.getAnimationPlanner().add(tT);
            vacancy = false;
        }
        else{
            tT = new TranslateTransition(Duration.millis(300), actor);
            tT.setByY(10);
//            AnimationSequence.getAnimationPlanner().add(tT);
            vacancy = true;
        }
        return tT;
    }
    
    public Rectangle getSit(){
        return (Rectangle) super.getObject();
    }
    
    public boolean getVacancy(){
        return vacancy;
    }
}
