/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import controller.page.ControlledPage;
import controller.page.PageController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.ToggleSwitch;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author PETER-PC
 */
public class SimulationPlannerController implements Initializable, ControlledPage {

    @FXML
    private TextField txtFAnimation;

    PageController myController;

    @FXML
    private AnchorPane simulationPlannerPanel;
    @FXML
    private HBox activityList;
    @FXML
    private Button btnHideUtilPanel;

    @Override
    public void setPageParent(PageController screenParent) {
        myController = screenParent;
    }

    private static final String[] list = {
            "Lecturer1 Leave Toilet",
            "Lecturer1 go to Toilet From Corridor",
            "Lecturer1 go to Toilet From Office1",
            "Lecturer1 Observe lecture in LectureRoom1",
            "Lecturer1 end lecture",
            "Lecturer1 go to Office1",
            "Lecturer1 Leave Office1",
            "Lecturer1 enter premise floorPlan",
            "Lecturer1 leave premise floorPlan",
            "Lecturer1 Toggle Tv switch in Office1",
            "Lecturer1 Toggle AC in LectureRoom1",
            "Lecturer1 Toggle Projector in LectureRoom1",
            "Lecturer1 Toggle Wi-Fi",
            "Lecturer1 Toggle Bulb",
            "Lecturer1 Toggle PC",
            "Lecturer1 Set Pc to Sleep",
            "Lecturer1 Wake Pc",
            "Lecturer1 Set Pc Usage to low",
            "Lecturer1 Set Pc Usage to High",
            "Lecturer1 Set Ac temperature low",
            "Lecturer1 Set Ac temperature High",
            "Lecturer1 Toggle Temperature Sensor in Office1",
            "Lecturer1 Set Temperature Sensor for Low Temperature in LectureRoom1",
            "Lecturer1 Set Temperature Sensor for Mid Temperature in LectureRoom1",
            "Lecturer1 Set Temperature Sensor for High Temperature in LectureRoom1",
            "Lecturer1 Toggle Temperature Sensor in LecturerRoom 1",
            "Students1 attend lectures in LectureRoom1",
            "Students1 Leave LectureRoom1",
            "Lecturer2 Leave Toilet",
            "Lecturer2 go to Toilet From Corridor",
            "Lecturer2 go to Toilet From Office2",
            "Lecturer2 Observe lecture in LectureRoom2",
            "Lecturer2 end lecture",
            "Lecturer2 go to Office2",
            "Lecturer2 Leave Office2",
            "Lecturer2 enter premise floorPlan",
            "Lecturer2 leave premise floorPlan",
            "Lecturer2 Toggle Tv switch",
            "Lecturer2 Toggle AC in LectureRoom1",
            "Lecturer2 Toggle Projector",
            "Lecturer2 Toggle Wi-Fi",
            "Lecturer2 Toggle Bulb",
            "Lecturer2 Toggle PC",
            "Lecturer2 Set Pc to Sleep",
            "Lecturer2 Wake Pc",
            "Lecturer2 Set Pc Usage to low",
            "Lecturer2 Set Pc Usage to High",
            "Lecturer2 Set Ac temperature low",
            "Lecturer2 Set Ac temperature High",
            "Lecturer2 Toggle Temperature Sensor in Office2",
            "Lecturer2 Set Temperature Sensor for Low Temperature in LectureRoom2",
            "Lecturer2 Set Temperature Sensor for Mid Temperature in LectureRoom2",
            "Lecturer2 Set Temperature Sensor for High Temperature in LectureRoom2",
            "Lecturer2 Toggle Temperature Sensor in LecturerRoom2",
            "Students2 attend lectures in LectureRoom2",
            "Students2 Leave LectureRoom2"};

    public static final ArrayList<String> suggestionProvider = new ArrayList<>(FXCollections.observableArrayList(list));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        activityList.getChildren().clear();
        IOTControls.animationSequence = new AnimationSequence();


        TextFields.bindAutoCompletion(txtFAnimation, suggestionProvider);

        tsInverter.setLayoutX(75);
        tsInverter.setLayoutY(60);
        tsInverter.setSelected(false);

        tsMain.setLayoutX(420);
        tsMain.setLayoutY(60);
        tsMain.setSelected(true);

        simulationPlannerPanel.getChildren().addAll(tsMain, tsInverter);
    }

    ToggleSwitch tsInverter = new ToggleSwitch("On");
    ToggleSwitch tsMain = new ToggleSwitch("On");

    Activity activity;
    AnimationSequence animationSequence;


    @FXML
    public void addActivity(ActionEvent e){
        if(Activity.simulationWindow == null){
            activity = new Activity();
            System.out.println("Created Activity Object");
        }
        if(AnimationSequence.getAnimationPlanner() == null){
            animationSequence = new AnimationSequence();
            System.out.println("Created Animation Sequence Instance");
        }

        int activityIndex = suggestionProvider.indexOf(txtFAnimation.getText());
//        Activity.animateText(txtFAnimation, .getText());

        System.out.println("Index: "+activityIndex);

        Label activity = new Label("Activity"+(++activityIndex));
        activity.setTooltip(new Tooltip(txtFAnimation.getText()));
        activity.setCursor(Cursor.HAND);

        Label notifier = (Label) IOTControls.root.lookup("#lblNotifier");
        notifier.setText(txtFAnimation.getText());
        System.out.println("Notification: "+notifier.getText());
        AnimationSequence.showNotification(txtFAnimation.getText(), notifier);


        activity.getStyleClass().add("activity-"+(activityIndex < 21 ? "1" : "2"));
        Animation animation = Activity.getAnimation(txtFAnimation.getText());
        IOTControls.animationSequence.getAnimationPlanner().add(animation);
        activityList.getChildren().add(activity);
        txtFAnimation.setText("");
    }
    
    //yet to be tested
    @FXML
    public void hideUtilPanel(ActionEvent e) throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("FXMLSim.fxml"));
        StackPane utilPanel = (StackPane)root.getChildren().get(root.getChildren().size() - 1);
        FXMLDocumentController.ctrla.hidePane(utilPanel);
        FXMLDocumentController.ctrla.utilPanelAnim(root, utilPanel, true, e);
    }
    
}
