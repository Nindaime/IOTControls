package iotcontrols;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PETER-PC
 */
public class Actor extends Circle{
    public static ArrayList<Actor> actors = new ArrayList<>();
    private Point2D location;
    public enum ActorType{student,lecturer}

    public ActorType simType;
    private final String actorName;

    public Actor(String actorName, double x, double y){
        location = new Point2D(x,y);
        setCenterX(x);
        setCenterY(y);
        this.actorName = actorName;
        if(actorName.contains("Lecturer"))
            simType = ActorType.lecturer;
        else
            simType = ActorType.student;
        
        setRadius(5);
        this.getStyleClass().add(simType.toString());

        actors.add(this);
    }
    
    public static ArrayList<Actor> getActors(String name){
        ArrayList<Actor> aListActor = new ArrayList<>();
        for(Actor a: actors)
            if(name.contains(a.getId()))
                aListActor.add(a);
        //test
        return aListActor;
    }

    public static Actor getActor(String actorName){
        for(Actor a: actors)
            if(actorName.matches(a.getActorName()))
                return a;
        return null;
    }

    public String getActorName(){
        return actorName;
    }

    public void setLocation(double x, double y){
        location = new Point2D(x,y);
    }
    
    public Point2D getLocation(){
        return location;
    }
    
}
