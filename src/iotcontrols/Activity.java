package iotcontrols;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import controller.page.PageController;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PETER-PC
 */
public class Activity{
    private Label label;
    public static AnchorPane simulationWindow;
    private static Path pathCorridor, pathToClassroom1, pathToClassroom1A11, pathToClassroom1A12, pathToClassroom1B11, pathToClassroom1B12,
            pathToClassroom1A21, pathToClassroom1A22, pathToClassroom1A31, pathToClassroom1A32, pathToClassroom1B21, pathToClassroom1B22,
            pathToClassroom1B31, pathToClassroom1B32, pathToClassroom2A11, pathToClassroom2B11, pathCorridorToToilet, pathToiletSink,
            pathToOffice1, pathToOffice2, pathToOffice1Front, pathToOffice1Sit, pathToOffice2Front, pathToOffice2Sit, pathToiletToSinkFromWaterCloset,
            pathToiletWaterCloset, pathToClassroom2, pathToClassroom2A12, pathToClassroom2B12, pathToClassroom2Aisle,
            pathToClassroom2AisleRow2, pathToClassroom2AisleRow3, pathToClassroom2B21, pathToClassroom2B22, pathToClassroom2A22, pathToClassroom2A21,
            pathToClassroom2A32, pathToClassroom2A31, pathToClassroom2B31, pathToClassroom2B32, pathToClassroom2Front1, pathToClassroom2Front2,
            pathToClassroom1AisleRow2, pathToClassroom1AisleRow3, pathToClassroom1Front1, pathToClassroom1Front2;
    private static Actor lecturer1, lecturer2;
    private static HashMap<String, Transition> transitionHashMap;

    public Activity(){
        simulationWindow = (AnchorPane) IOTControls.root.lookup("#floorPlan");
        loadPaths();
        transitionHashMap = new HashMap<>();
    }

    private void loadPaths(){
        pathCorridor = AnimationSequence.getPath("pathCorridor");
        pathToClassroom1 = AnimationSequence.getPath("pathToClassroom1");
        pathToClassroom1A11 = AnimationSequence.getPath("pathToClassroom1A11");
        pathToClassroom1A12 = AnimationSequence.getPath("pathToClassroom1A12");
        pathToClassroom1A21 = AnimationSequence.getPath("pathToClassroom1A21");
        pathToClassroom1A22 = AnimationSequence.getPath("pathToClassroom1A22");
        pathToClassroom1A31 = AnimationSequence.getPath("pathToClassroom1A31");
        pathToClassroom1A32 = AnimationSequence.getPath("pathToClassroom1A32");
        pathToClassroom1B11 = AnimationSequence.getPath("pathToClassroom1B11");
        pathToClassroom1B12 = AnimationSequence.getPath("pathToClassroom1B12");
        pathToClassroom1B21 = AnimationSequence.getPath("pathToClassroom1B21");
        pathToClassroom1B22 = AnimationSequence.getPath("pathToClassroom1B22");
        pathToClassroom1B31 = AnimationSequence.getPath("pathToClassroom1B31");
        pathToClassroom1B32 = AnimationSequence.getPath("pathToClassroom1B32");
        pathCorridorToToilet = AnimationSequence.getPath("pathCorridorToToilet");
        pathToiletSink = AnimationSequence.getPath("pathToiletSink");
        pathToiletToSinkFromWaterCloset = AnimationSequence.getPath("pathToiletToSink");
//        pathToiletToSinkFromWaterCloset = AnimationSequence.getPath("pathToiletToSinkFromWaterCloset");
        pathToOffice1 = AnimationSequence.getPath("pathToOffice1");
        pathToOffice2 = AnimationSequence.getPath("pathToOffice2");
        pathToiletWaterCloset = AnimationSequence.getPath("pathToiletWaterCloset");
        pathToOffice1Front = AnimationSequence.getPath("pathToOffice1Front");
        pathToOffice1Sit = AnimationSequence.getPath("pathToOffice1Sit");
        pathToOffice2Front = AnimationSequence.getPath("pathToOffice2Front");
        pathToOffice2Sit = AnimationSequence.getPath("pathToOffice2Sit");
        pathToClassroom2 = AnimationSequence.getPath("pathToClassroom2");
        pathToClassroom2A11 = AnimationSequence.getPath("pathToClassroom2A11");
        pathToClassroom2A12 = AnimationSequence.getPath("pathToClassroom2A12");
        pathToClassroom2B11 = AnimationSequence.getPath("pathToClassroom2B11");
        pathToClassroom2B12 = AnimationSequence.getPath("pathToClassroom2B12");
        pathToClassroom2Aisle = AnimationSequence.getPath("pathToClassroom2Aisle");
        pathToClassroom2AisleRow2 = AnimationSequence.getPath("pathToClassroom2AisleRow2");
        pathToClassroom2AisleRow3 = AnimationSequence.getPath("pathToClassroom2AisleRow3");
        pathToClassroom2B21 = AnimationSequence.getPath("pathToClassroom2B21");
        pathToClassroom2B22 = AnimationSequence.getPath("pathToClassroom2B22");
        pathToClassroom2A22 = AnimationSequence.getPath("pathToClassroom2A22");
        pathToClassroom2A21 = AnimationSequence.getPath("pathToClassroom2A21");
        pathToClassroom2A32 = AnimationSequence.getPath("pathToClassroom2A32");
        pathToClassroom2A31 = AnimationSequence.getPath("pathToClassroom2A31");
        pathToClassroom2B31 = AnimationSequence.getPath("pathToClassroom2B31");
        pathToClassroom2B32 = AnimationSequence.getPath("pathToClassroom2B32");
        pathToClassroom2Front1 = AnimationSequence.getPath("pathToClassroom2Front1");
        pathToClassroom2Front2 = AnimationSequence.getPath("pathToClassroom2Front2");
        pathToClassroom1AisleRow2 = AnimationSequence.getPath("pathToClassroom1AisleRow2");
        pathToClassroom1AisleRow3 = AnimationSequence.getPath("pathToClassroom1AisleRow3");
        pathToClassroom1Front1 = AnimationSequence.getPath("pathToClassroom1Front1");
        pathToClassroom1Front2 = AnimationSequence.getPath("pathToClassroom1Front2");
        lecturer1 = null;
        lecturer2 = null;
    }

    public Label getLabel(){
        return label;
    }

    public void setLabel(String activityName){
        label.setText(activityName);
    }

    
    //build method for ensuring animations are ordered properly so as to not trigger error
    //construct animation from string
    public final static Transition getAnimation(String activityName) {
        Transition transition = null;
        switch(activityName){
            case "Lecturer1 Leave Toilet":
                if(lecturer1.intersects(pathToiletWaterCloset.getLayoutBounds()))
                    transition = new PathTransition(Duration.seconds(3), pathToiletWaterCloset, lecturer1);
                else
                    transition = new PathTransition(Duration.seconds(3), pathToiletSink, lecturer1);
                transition.setRate(-1);
                transitionHashMap.put("Lecturer1 Leave Toilet", transition);
                break;
            case "Lecturer1 go to Toilet From Corridor":
                transition = new PathTransition(Duration.seconds(3), pathCorridorToToilet, lecturer1);
                transitionHashMap.put("Lecturer1 go to Toilet From Corridor", transition);
                break;
            case "Lecturer1 go to Toilet From Office1":
//                transitionHashMap.put("Lecturer1 go to Toilet From Office1", transition);
                break;
            case "Lecturer1 Observe lecture in LectureRoom1":
                transition = new SequentialTransition(new PathTransition(Duration.seconds(1.5), pathToClassroom1Front1, lecturer1),
                        new PathTransition(Duration.seconds(1.5), pathToClassroom1Front2, lecturer1), new PauseTransition(Duration.seconds(3)));
                transition.setAutoReverse(true);
                transition.setCycleCount(6);
                transitionHashMap.put("Lecturer1 Observe lecture in LectureRoom1", transition);
                break;
            case  "Lecturer1 end lecture":

//                transitionHashMap.put("Lecturer1 Observe lecture in LectureRoom1", transition);
                break;
            case  "Lecturer1 go to Office1":
                transition = new PathTransition(Duration.seconds(3), pathToOffice1, lecturer1);
                ((PathTransition)transition).setRate(-1);
                transitionHashMap.put("Lecturer1 go to Office1", transition);
                break;
            case "Lecturer1 Leave Office1":
//                transition = new PathTransition(Duration.seconds(1), pathToOffice1, actor);
                transition = transitionHashMap.get("Lecturer1 go to Office1");
                transition.setRate(-1);
                break;
            case  "Lecturer1 enter premise floorPlan":
                MoveTo corridor = (MoveTo) pathCorridor.getElements().get(0);
                lecturer1 = new Actor("Lecturer1", corridor.getX(), corridor.getY());
                if(!simulationWindow.getChildren().contains(lecturer1))
                    simulationWindow.getChildren().add(lecturer1);
                lecturer1.setOpacity(0);
                transition = new ParallelTransition(new Timeline(new KeyFrame(Duration.millis(100), new KeyValue(lecturer1.opacityProperty(), 0),
                        new KeyValue(lecturer1.opacityProperty(), 1))),
                        new PathTransition(Duration.seconds(3), pathCorridor, lecturer1));
                transitionHashMap.put("Lecturer1 enter premise floorPlan", transition);
                break;
            case "Lecturer1 leave premise floorPlan":
//                transition = new PathTransition(Duration.seconds(1), pathCorridor, actor);
                transition = transitionHashMap.get("Lecturer1 leave premise floorPlan");
                transition.setRate(-1);
                transition.setOnFinished(e -> {
                    simulationWindow.getChildren().remove(lecturer1);
                });
                break;
            case "Lecturer1 Toggle Tv switch in Office1":
                    Path Office1TvView = (Path) IOTControls.root.lookup("#Office1TvView");
                    transition = new FadeTransition(Duration.seconds(3), Office1TvView);
                    ((FadeTransition)transition).setToValue(0.5);
                break;
            case "Lecturer1 Toggle AC in LectureRoom1":
                break;
            case "Lecturer1 Toggle Projector in LectureRoom1":
                break;
            case "Lecturer1 Toggle Wi-Fi":
                break;
            case "Lecturer1 Toggle Bulb":
                break;
            case "Lecturer1 Toggle PC":
                break;
            case "Lecturer1 Set Pc to Sleep":
                break;
            case "Lecturer1 Wake Pc":
                break;
            case "Lecturer1 Set Pc Usage to low":
                break;
            case "Lecturer1 Set Pc Usage to High":
                break;
            case "Lecturer1 Set Ac temperature low":
                break;
            case "Lecturer1 Set Ac temperature High":
                break;
            case "Lecturer1 Toggle Temperature Sensor in Office1":
                break;
            case "Lecturer1 Set Temperature Sensor for Low Temperature in LectureRoom1":
                break;
            case "Lecturer1 Set Temperature Sensor for Mid Temperature in LectureRoom1":
                break;
            case "Lecturer1 Set Temperature Sensor for High Temperature in LectureRoom1":
                break;
            case "Lecturer1 Toggle Temperature Sensor in LecturerRoom 1":
                break;
            case "Students1 attend lectures in LectureRoom1":
                MoveTo corridor2 = (MoveTo) pathCorridor.getElements().get(0);
                Actor student1 = new Actor("Student1", corridor2.getX(), corridor2.getY()); student1.setOpacity(0);
                Actor student2 = new Actor("Student2", corridor2.getX(), corridor2.getY()); student2.setOpacity(0);
                Actor student3 = new Actor("Student3", corridor2.getX(), corridor2.getY()); student3.setOpacity(0);
                Actor student4 = new Actor("Student4", corridor2.getX(), corridor2.getY()); student4.setOpacity(0);
                Actor student5 = new Actor("Student5", corridor2.getX(), corridor2.getY()); student5.setOpacity(0);
                Actor student6 = new Actor("Student6", corridor2.getX(), corridor2.getY()); student6.setOpacity(0);
                Actor student7 = new Actor("Student7", corridor2.getX(), corridor2.getY()); student7.setOpacity(0);
                Actor student8 = new Actor("Student8", corridor2.getX(), corridor2.getY()); student8.setOpacity(0);
                Actor student9 = new Actor("Student9", corridor2.getX(), corridor2.getY()); student9.setOpacity(0);
                Actor student10 = new Actor("Student10", corridor2.getX(), corridor2.getY()); student10.setOpacity(0);
                Actor student11 = new Actor("Student11", corridor2.getX(), corridor2.getY()); student11.setOpacity(0);
                Actor student12 = new Actor("Student11", corridor2.getX(), corridor2.getY()); student11.setOpacity(0);
                simulationWindow.getChildren().addAll(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10, student11, student12);
                SequentialTransition student1Path = new SequentialTransition(student1,
                        new Timeline(new KeyFrame(Duration.millis(100),
                                new KeyValue(student1.opacityProperty(), 0),
                                new KeyValue(student1.opacityProperty(), 1))),
                        getPathTransition(3,pathCorridor,1),
                        getPathTransition(1, pathToClassroom1, -1),
                        getPathTransition(3, pathToClassroom1A11, 1));

                SequentialTransition student2Path = new SequentialTransition(student2,
                        new Timeline(new KeyFrame(Duration.millis(100),
                                new KeyValue(student1.opacityProperty(), 0),
                                new KeyValue(student1.opacityProperty(), 1))),
                        getPathTransition(3, pathCorridor, 1),
                        getPathTransition(1, pathToClassroom1, -1),
                        getPathTransition(3, pathToClassroom1A12, 1));
                student2Path.setDelay(Duration.seconds(.5));

                SequentialTransition student3Path = new SequentialTransition(student3,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B11));
                student3Path.setDelay(Duration.seconds(1));

                SequentialTransition student4Path = new SequentialTransition(student4,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B12));
                student4Path.setDelay(Duration.seconds(1.5));

                SequentialTransition student5Path = new SequentialTransition(student5,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A21));
                student5Path.setDelay(Duration.seconds(2));

                SequentialTransition student6Path = new SequentialTransition(student6,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A22));
                student6Path.setDelay(Duration.seconds(2.5));

                SequentialTransition student7Path = new SequentialTransition(student7,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B21));
                student7Path.setDelay(Duration.seconds(3));

                SequentialTransition student8Path = new SequentialTransition(student8,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B22));
                student8Path.setDelay(Duration.seconds(4));

                SequentialTransition student9Path = new SequentialTransition(student9,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A31));
                student9Path.setDelay(Duration.seconds(4.5));

                SequentialTransition student10Path = new SequentialTransition(student10,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A32));
                student10Path.setDelay(Duration.seconds(5));

                SequentialTransition student11Path = new SequentialTransition(student11,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B31));
                student11Path.setDelay(Duration.seconds(5.5));

                SequentialTransition student12Path = new SequentialTransition(student12,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B32));
                student12Path.setDelay(Duration.seconds(6));

                transition = new ParallelTransition(student1Path, student2Path, student3Path, student4Path, student5Path, student6Path, student7Path, student8Path, student9Path,
                        student10Path, student11Path, student12Path);
                transitionHashMap.put("Students1 attend lectures in LectureRoom1", transition);
                break;
            case "Students1 Leave LectureRoom1":
                Actor outStudent1 = Actor.getActor("Student1");
                Actor outStudent2 = Actor.getActor("Student2");
                Actor outStudent3 = Actor.getActor("Student3");
                Actor outStudent4 = Actor.getActor("Student4");
                Actor outStudent5 = Actor.getActor("Student5");
                Actor outStudent6 = Actor.getActor("Student6");
                Actor outStudent7 = Actor.getActor("Student7");
                Actor outStudent8 = Actor.getActor("Student8");
                Actor outStudent9 = Actor.getActor("Student9");
                Actor outStudent10 = Actor.getActor("Student10");
                Actor outStudent11 = Actor.getActor("Student11");
                Actor outStudent12 = Actor.getActor("Student12");

                SequentialTransition outStudent1Path = new SequentialTransition(outStudent1,
                        getPathTransition(3, pathCorridor, 1),
                        getPathTransition(3, pathToClassroom1, -1),
                        getPathTransition(3, pathToClassroom1A11, 1));
                outStudent1Path.setRate(-1);
                outStudent1Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent1Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent1));

                SequentialTransition outStudent2Path = new SequentialTransition(outStudent2,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A12));
                outStudent2Path.setDelay(Duration.seconds(.5));
                outStudent2Path.setRate(-1);
                outStudent2Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent2Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent2));

                SequentialTransition outStudent3Path = new SequentialTransition(outStudent3,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B11));
                outStudent3Path.setDelay(Duration.seconds(1));
                outStudent3Path.setRate(-1);
                outStudent3Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent3Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent3));

                SequentialTransition outStudent4Path = new SequentialTransition(outStudent4,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B12));
                outStudent4Path.setDelay(Duration.seconds(1.5));
                outStudent4Path.setRate(-1);
                outStudent4Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent4Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent4));

                SequentialTransition outStudent5Path = new SequentialTransition(outStudent5,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A21));
                outStudent5Path.setDelay(Duration.seconds(2));
                outStudent5Path.setRate(-1);
                outStudent5Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent5Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent5));

                SequentialTransition outStudent6Path = new SequentialTransition(outStudent6,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A22));
                outStudent6Path.setDelay(Duration.seconds(2.5));
                outStudent6Path.setRate(-1);
                outStudent6Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent6Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent6));

                SequentialTransition outStudent7Path = new SequentialTransition(outStudent7,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B21));
                outStudent7Path.setDelay(Duration.seconds(3));
                outStudent7Path.setRate(-1);
                outStudent7Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent7Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent7));

                SequentialTransition outStudent8Path = new SequentialTransition(outStudent8,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B22));
                outStudent8Path.setDelay(Duration.seconds(4));
                outStudent8Path.setRate(-1);
                outStudent8Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent8Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent8));

                SequentialTransition outStudent9Path = new SequentialTransition(outStudent9,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A31));
                outStudent9Path.setDelay(Duration.seconds(4.5));
                outStudent9Path.setRate(-1);
                outStudent9Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent9Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent9));

                SequentialTransition outStudent10Path = new SequentialTransition(outStudent10,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1A32));
                outStudent10Path.setDelay(Duration.seconds(5));
                outStudent10Path.setRate(-1);
                outStudent10Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent10Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent10));
                SequentialTransition outStudent11Path = new SequentialTransition(outStudent11,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B31));
                outStudent11Path.setDelay(Duration.seconds(5.5));
                outStudent11Path.setRate(-1);
                outStudent11Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent11Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent11));

                SequentialTransition outStudent12Path = new SequentialTransition(outStudent12,
                        new PathTransition(Duration.seconds(3), pathCorridor),
                        new PathTransition(Duration.seconds(3), pathToClassroom1),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow2),
                        new PathTransition(Duration.seconds(3), pathToClassroom1AisleRow3),
                        new PathTransition(Duration.seconds(3), pathToClassroom1B32));
                outStudent12Path.setDelay(Duration.seconds(6));
                outStudent12Path.setRate(-1);
                outStudent12Path.getChildren().forEach(animation -> animation.setRate(-1));
                outStudent12Path.setOnFinished(event -> simulationWindow.getChildren().remove(outStudent12));
                transition = new ParallelTransition(outStudent1Path, outStudent2Path, outStudent3Path, outStudent4Path, outStudent5Path, outStudent6Path, outStudent7Path, outStudent8Path, outStudent9Path,
                        outStudent10Path, outStudent11Path, outStudent12Path);
                break;
            case "Lecturer2 Leave Toilet":
                break;
            case "Lecturer2 go to Toilet From Corridor":
                break;
            case "Lecturer2 go to Toilet From Office2":
                break;
            case "Lecturer2 Observe lecture in LectureRoom2":
                break;
            case "Lecturer2 end lecture":
                break;
            case "Lecturer2 go to Office2":
                break;
            case "Lecturer2 Leave Office2":
                break;
            case "Lecturer2 enter premise floorPlan":
                Path pEnterPremise = AnimationSequence.getPath("pathCorridor");
                MoveTo start = (MoveTo) pEnterPremise.getElements().get(0);
                Path path = new Path();
                path.getElements().addAll(new LineTo(start.getX(), start.getY()), new ClosePath());
                transition = new PathTransition(Duration.seconds(3), path, lecturer2);
                break;
            case "Lecturer2 leave premise floorPlan":
                break;
            case "Lecturer2 Toggle Tv switch":
                break;
            case "Lecturer2 Toggle AC in LectureRoom1":
                break;
            case "Lecturer2 Toggle Projector":
                break;
            case "Lecturer2 Toggle Wi-Fi":
                break;
            case "Lecturer2 Toggle Bulb":
                break;
            case "Lecturer2 Toggle PC":
                break;
            case "Lecturer2 Set Pc to Sleep":
                break;
            case "Lecturer2 Wake Pc":
                break;
            case "Lecturer2 Set Pc Usage to low":
                break;
            case "Lecturer2 Set Pc Usage to High":
                break;
            case "Lecturer2 Set Ac temperature low":
                break;
            case "Lecturer2 Set Ac temperature High":
                break;
            case "Lecturer2 Toggle Temperature Sensor in Office2":
                break;
            case "Lecturer2 Set Temperature Sensor for Low Temperature in LectureRoom2":
                break;
            case "Lecturer2 Set Temperature Sensor for Mid Temperature in LectureRoom2":
                break;
            case "Lecturer2 Set Temperature Sensor for High Temperature in LectureRoom2":
                break;
            case "Lecturer2 Toggle Temperature Sensor in LecturerRoom2":
                break;
            case "Students2 attend lectures in LectureRoom2":
                break;
            case "Students2 Leave LectureRoom2":
                break;
            default:
                break;
        }
        
        return transition;
    }

    public static PathTransition getPathTransition(double seconds, Path path, int rate){
        PathTransition pathTransition = new PathTransition(Duration.seconds(seconds), path);
        pathTransition.setRate(rate);
        return pathTransition;
    }

}
