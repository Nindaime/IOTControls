/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.page;

/**
 *
 * @author USER
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import System.Biometric.Project;
import iotcontrols.FXMLDocumentController;
import javafx.util.Duration;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
/**
 *
 * @author PETER
 */
public class PageController extends StackPane{
    public static HashMap<String, Pane> screens = new HashMap<>();
    public static StackPane childrenNodes = new StackPane();
    
    public PageController(){
        super();
    }
    
    public void addPage(String name, Pane page){
        screens.put(name, page);
    }
    
    public Pane getPage(String name){
        return screens.get(name);
    }
    
    public boolean loadPage(String name, String resource){
        try{
            FXMLLoader pageLoader = new FXMLLoader(FXMLDocumentController.class.getResource(resource));
            
            Pane loadPage = pageLoader.load();
            ControlledPage controlledPage = pageLoader.getController();
            controlledPage.setPageParent(this);
            addPage(name, loadPage);
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setPage(final String name){
        if(screens.get(name) != null){
            final DoubleProperty opacity = opacityProperty();
            
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(Duration.millis(400), (ActionEvent t) -> {
                            getChildren().remove(0);                    //remove the displayed screen
                            getChildren().add(0, screens.get(name));     //add the screen
                            getChildren().get(0).requestFocus();
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(Duration.millis(200), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                }, new KeyValue(opacity, 0.0)));
                fade.play();
                
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(Duration.millis(200), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }
    
    public boolean unloadScreen(String name){
        if(screens.remove(name) == null){
            System.out.println("Screen doesn't exist");
            return false;
        }
        else
            return true;
    }
    
   
}

