package iotcontrols;


import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
public class ControlPanelAnim {
    double[] btnCoord = {0, 60, 120, 180, 240, 300};
    AnchorPane cPanel;
    String ctrlState = "";
    
    public void setPanel(AnchorPane cPanel){
        this.cPanel = cPanel;
        cPanel.setPrefHeight(50);
        for(Node e: cPanel.getChildren()){
            if(cPanel.getChildren().indexOf(e) != 5)
                e.setVisible(false);
        }
        ctrolPHAnimation();    
        ctrlState = "Hidden";
    }
    public void revealActionAnimation(){
        ParallelTransition p = new ParallelTransition(revealButtons(), ctrlPRevealAnimation(), ctrlPRevealAnimation1());
        p.setInterpolator(Interpolator.EASE_BOTH);
        p.play();
        ctrlState = "Revealed";
    }
    public void hideActionAnimation(){
        ParallelTransition p = new ParallelTransition(hideButtons(), ctrlPHideAnimation(), ctrlPHideAnimation1());
        p.setInterpolator(Interpolator.EASE_BOTH);
        p.play();
        ctrlState = "Hidden";
    }
    
    public Animation revealButtons(){
        ParallelTransition p = new ParallelTransition();
        for(Node n: cPanel.getChildren()){
//            System.out.println(n.getId()+" index "+ cPanel.getChildren().indexOf(n));
            if(cPanel.getChildren().indexOf(n) != 5)
                n.setVisible(true);
            p.getChildren().add(btnRevealAnimation(cPanel.getChildren().indexOf(n)));
        }
        return p;
    }
        
    public Animation btnRevealAnimation(int btnIndex){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), 
                new KeyValue(cPanel.getChildren().get(btnIndex).translateYProperty(), btnCoord[btnIndex])));
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(250), 
                new KeyValue(cPanel.getChildren().get(btnIndex).opacityProperty(), 1, Interpolator.EASE_BOTH)));
        t1.play();
        return t;
    }

    public Animation hideButtons(){
        ParallelTransition p = new ParallelTransition();
        for(Node n: cPanel.getChildren())
            p.getChildren().add(btnHideAnimation(cPanel.getChildren().indexOf(n)));
        
        return p;
    }

    public Animation btnHideAnimation(int btnIndex){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), 
                new KeyValue(cPanel.getChildren().get(btnIndex).translateYProperty(), 0)));
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(250), 
                new KeyValue(cPanel.getChildren().get(btnIndex).opacityProperty(), 0, Interpolator.EASE_BOTH)));
        if(btnIndex != 5)
            t1.play();
        
        return t;
    }
    
    private final int hHeight = 423;
    private final int rHeight = 621;

    public void utilPanelAnim(Pane rootNode, StackPane s, Boolean hide, ActionEvent e) {
        Stage stage = ((Stage)((Node) e.getSource()).getScene().getWindow());

        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), (ActionEvent a) -> {
            if(hide){
                hidePane(s); 
                s.setVisible(false);
            }
            else{
                s.setVisible(true);
                revealPane(s);
            }
        }, new KeyValue(rootNode.prefHeightProperty(), (hide ? hHeight : rHeight), Interpolator.EASE_BOTH)));
        
        t.setDelay(Duration.millis(200));
        t.play();
        
        //if hide is false i.e. to reveal pane & vice versa
        if(hide)
            stage.setHeight(hHeight);
        else
            stage.setHeight(rHeight);
    }
    
    public void revealPane(StackPane s){
        FadeTransition f = new FadeTransition(Duration.millis(250), s);
        f.setToValue(1);
        f.setInterpolator(Interpolator.EASE_BOTH);
        f.setDelay(Duration.millis(300));
        f.play();
    }
    
    public void hidePane(StackPane s){
        FadeTransition f = new FadeTransition(Duration.millis(250), s);
        f.setToValue(0);
        f.setInterpolator(Interpolator.EASE_BOTH);
        f.play();
    }
    
    public Animation ctrlPRevealAnimation(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), new KeyValue(cPanel.translateYProperty(), -300)));
        return t;
    }
    
    public Animation ctrlPRevealAnimation1(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), new KeyValue(cPanel.prefHeightProperty(), 350)));
        return t;
    }

    public Animation ctrlPHideAnimation(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), new KeyValue(cPanel.translateYProperty(), 0)));
        return t;
    }
    
    public Animation ctrlPHideAnimation1(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(250), new KeyValue(cPanel.prefHeightProperty(), 50)));
        return t;
    }
    
    public void ctrlPSAnimation(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(200), new KeyValue(cPanel.opacityProperty(), 1, Interpolator.EASE_BOTH)));
        t.play();
    }
    
    public void ctrolPHAnimation(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(200), new KeyValue(cPanel.opacityProperty(), 0.5, Interpolator.EASE_BOTH)));
        t.play();
    }

    HBox activityList;
    
    public void action(ActionEvent e){
        switch(((Button)e.getSource()).getId()){
            case "play": System.out.println("Animation is playing");
                AnimationSequence.play();
                System.out.println("Printing animation sequence");
                AnimationSequence.getAnimationPlanner().forEach(animation -> System.out.println("animation1: "+animation));
                hideActionAnimation();
                break;
            case "pause": System.out.println("Animation is paused");
                AnimationSequence.pause();
                break;
            case "stop": System.out.println("Animation is stopped");
                AnimationSequence.stop();
                AnimationSequence.getAnimationPlanner().clear();
                Activity.simulationWindow.getChildren().removeAll(Actor.actors);
                activityList = (HBox) IOTControls.root.lookup("#activityList");
                activityList.getChildren().clear();
                break;
            case "eStats": System.out.println("EStat Button ActionEvent ");
                break;
            case "simPlanner": System.out.println("SimPlanner Button ActionEvent ");
                break;
            case "close": System.out.println("Close Button ActionEvent ");
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.close();
                break;
        }
        
    }
    
}
