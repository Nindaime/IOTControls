package iotcontrols;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PETER-PC
 */
public class AnimationSequence {
    private static ArrayList<Animation> animationPlanner;
    private static Animation currentAnimation;

    public static ArrayList<Animation> getAnimationPlanner(){
        return animationPlanner;
    }

    private static SVGDocument svgDrivePath;

    public AnimationSequence(){
//        this.car = car;
        PathParser parser = new PathParser();
        animationPlanner = new ArrayList<>();

        JavaFXPathElementHandler handler = new JavaFXPathElementHandler();
        parser.setPathHandler(handler);
        try {
            String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(xmlParser);

            File drivePath = new File("src/app-icons/NavigationMap.svg");
            svgDrivePath = f.createSVGDocument(drivePath.getAbsoluteFile().toURI().toString());
        }catch(IOException ex){ex.printStackTrace();}

    }

    public static Path getPath(String pathID){
        PathParser parser = new PathParser();
        JavaFXPathElementHandler handler = new JavaFXPathElementHandler();
        parser.setPathHandler(handler);
        String pathData, transform;

        Element selectedPath = svgDrivePath.getElementById(pathID);
        pathData = selectedPath.getAttributeNode("d").getValue();
        transform = selectedPath.getAttributeNode("transform").getValue();

        parser.parse(pathData);
        Path path = handler.getPath();

        transform = transform.replace("matrix(", "");
        transform = transform.replace(")", "");
        String[] transforms = transform.split(",");
        path.getTransforms()
                .add(Transform.affine(Double.parseDouble(transforms[0]), Double.parseDouble(transforms[1]), Double.parseDouble(transforms[2]),
                        Double.parseDouble(transforms[3]), Double.parseDouble(transforms[4]), Double.parseDouble(transforms[5])));
        return path;
    }

    public void openDoor(Room room){
        animationPlanner.add(openDoorAnim(room.getDoor().getDoor(), room.getDoor().getDoorConfig().getRotateAngle(),
                room.getDoor().getDoorConfig().getTranslateVariable()));
    }
//
    private Animation openDoorAnim(Rectangle door, double angle, int translateVar){
        TranslateTransition tT = new TranslateTransition(Duration.millis(600), door);
        if(door.getId().matches("floorPlanDoor"))
            tT.setByY(translateVar);
        else
            tT.setByX(translateVar);

        RotateTransition rT = new RotateTransition(Duration.millis(600), door);
        rT.setByAngle(angle);
        ParallelTransition pT = new ParallelTransition(tT, rT);
        pT.setDelay(Duration.millis(200));
        pT.setInterpolator(Interpolator.EASE_BOTH);

        return pT;
    }

    public void closeDoor(Room room){
        animationPlanner.add(closeDoorAnim(room.getDoor().getDoor(), room.getDoor().getDoorConfig().getRotateAngle(),
                room.getDoor().getDoorConfig().getTranslateVariable()));
    }

    private Animation closeDoorAnim(Rectangle door, double angle, int translateVar){
        TranslateTransition tT = new TranslateTransition(Duration.millis(600), door);
        if(door.getId().matches("floorPlanDoor"))
            tT.setByY(-translateVar);
        else
            tT.setByX(-translateVar);

        RotateTransition rT = new RotateTransition(Duration.millis(600), door);
        rT.setByAngle(-angle);
        ParallelTransition pT = new ParallelTransition(tT, rT);
        pT.setDelay(Duration.millis(200));
        pT.setInterpolator(Interpolator.EASE_BOTH);

        return pT;
    }
//
//    //incomplete
    public void enterOfficeChair(SimObject chair){
        TranslateTransition pChair = new TranslateTransition(Duration.millis(500), chair.getSimObject());
        pChair.setInterpolator(Interpolator.EASE_OUT);
        pChair.setByX(-15);
        animationPlanner.add(pChair);

        TranslateTransition pChair1 = new TranslateTransition(Duration.millis(500), chair.getSimObject());
        pChair1.setInterpolator(Interpolator.EASE_OUT);
        pChair1.setByX(15);
        pChair1.setDelay(Duration.millis(500));
        animationPlanner.add(pChair1);
    }

    public void toggleLight_View(Path light, Boolean turnOn){
        FadeTransition fT = new FadeTransition(Duration.millis(350), light);
        fT.setToValue((turnOn == true ? 1 : 0));
        fT.setDelay(Duration.millis(200));
        animationPlanner.add(fT);
    }

    public void trackSimMotion(Actor sim, Path cameraView, Pane office){
        Circle tracker = new Circle(8.5);
        tracker.getStyleClass().add("tracker");
        office.getChildren().add(tracker);

        while(cameraView.contains(sim.getLayoutX(), sim.getLayoutY())){
            tracker.setCenterX(sim.getCenterX());
            tracker.setCenterY(sim.getCenterY());
        }
        
        BooleanProperty simInView = new SimpleBooleanProperty(cameraView.contains(sim.getLayoutX(), sim.getLayoutY()));
        simInView.addListener(e ->{
            new Thread(() -> {
                try {
                    while(simInView.get()) {
                        Platform.runLater(() -> {
                            tracker.setCenterX(sim.getCenterX());
                            tracker.setCenterY(sim.getCenterY());
                        });
                        Thread.sleep(200);
                    }
                }
                catch (InterruptedException ex) {}
            }).start();
        });
    }
    
    //incomplete
    //always show notification
    public static void showNotification(String message, Label notifier){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            notifier.setText(message);
            notifier.setOpacity(0);
        }),
                new KeyFrame(Duration.seconds(1), new KeyValue(notifier.opacityProperty(), 1), new KeyValue(notifier.opacityProperty(), 1, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.seconds(1), new KeyValue(notifier.opacityProperty(), 1), new KeyValue(notifier.opacityProperty(), 0, Interpolator.EASE_OUT)));

        animationPlanner.add(timeline);
    }

    public static void pause(){
        if(currentAnimation != null)
            currentAnimation.pause();
    }

    public static void stop(){
        if(currentAnimation != null)
            currentAnimation.stop();
    }

    public static void play(){
        if(currentAnimation != null && currentAnimation.getStatus() == Animation.Status.PAUSED){
            currentAnimation.play();
        }else{
            currentAnimation = animationPlanner.get(0);
            for(Animation a: animationPlanner){
                int index = animationPlanner.indexOf(a);
                if(index < animationPlanner.size() - 1)
                a.setOnFinished(event -> {
                    Animation next = animationPlanner.get(index+1);
                    currentAnimation = next;
                    next.play();
                });
            }
            currentAnimation.play();
        }
    }
    
}
