/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotcontrols;

import controller.page.ControlledPage;
import controller.page.PageController;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author PETER-PC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public void showUtilityPanelSim(ActionEvent e){
        ctrla.utilPanelAnim(floorPlan, utilPanel, false, e);
        utilPanel.setPage(simulationPlanner);
    }
    
    @FXML
    public void showUtilityPanelEStat(ActionEvent e){
        ctrla.utilPanelAnim(floorPlan, utilPanel, false, e);
        utilPanel.setPage(graph);
    }
    
    @FXML
    public void hideUtilPanel(ActionEvent e){
        ctrla.hidePane(utilPanel);
        ctrla.utilPanelAnim(floorPlan, utilPanel, true, e);
    }
    
    @FXML
    private TextField txtFAnimation;
    @FXML
    private AnchorPane simulationPlannerPanel;
    @FXML
    private AnchorPane controlPanel;
    @FXML
    private Button close;
    @FXML
    private Button simPlanner;
    @FXML
    private Button eStats;
    @FXML
    private Button stop;
    @FXML
    private Button pause;
    @FXML
    private Button play;
    @FXML
    private AnchorPane floorPlan;
    @FXML
    private Pane Office1;
//    @FXML
//    private Group Office1Camera;
    @FXML
    private Rectangle Office1Table;
    @FXML
    private Path Office1CameraView;
    @FXML
    private Group Office1Chair;
    @FXML
    private Path Office1Light;
    @FXML
    private Rectangle Office1AC;
//    @FXML
//    private Rectangle Office1TV;
    @FXML
    private Rectangle Office1Door;
    @FXML
    private Path Office1TvView;
    @FXML
    private Path Office1PcView;
    @FXML
    private Pane Office2;
//    @FXML
//    private Group Office2Camera;
    @FXML
    private Rectangle Office2Table;
    @FXML
    private Path Office2CameraView;
    @FXML
    private Group Office2Chair;
    @FXML
    private Path Office2Light;
    @FXML
    private Rectangle Office2AC;
//    @FXML
//    private Rectangle Office2TV;
    @FXML
    private Rectangle Office2Door;
    @FXML
    private Path Office2TvView;
    @FXML
    private Path Office2PcView;
    @FXML
    private Pane Toilet;
    @FXML
    private Group WaterCloset;
    @FXML
    private Group ToiletSink;
    @FXML
    private Path ToiletLight;
    @FXML
    private Path ToiletCameraView;
    @FXML
    private Rectangle ToiletDoor;
    @FXML
    private Pane LectureRoom1;
    @FXML
    private Rectangle LectureRoom1Door;
    @FXML
    private Rectangle LectRoom1Chair02;
    @FXML
    private Rectangle LectRoom1Chair01;
    @FXML
    private Rectangle LectRoom1Chair12;
    @FXML
    private Rectangle LectRoom1Chair11;
    @FXML
    private Rectangle LectRoom1Chair10;
    @FXML
    private Rectangle LectRoom1Chair00;
    @FXML
    private Rectangle LectRoom1Projector;
    @FXML
    private Path LectRoom1Light00;
    @FXML
    private Path LectRoom1Light01;
    @FXML
    private Path LectRoom1Light10;
    @FXML
    private Path LectRoom1Light11;
    @FXML
    private Path LectRoom1ProjectorView;
    @FXML
    private Pane LectureRoom2;
    @FXML
    private Rectangle LectureRoom2Door;
    @FXML
    private Rectangle LectRoom2Chair02;
    @FXML
    private Rectangle LectRoom2Chair01;
    @FXML
    private Rectangle LectRoom2Chair12;
    @FXML
    private Rectangle LectRoom2Chair11;
    @FXML
    private Rectangle LectRoom2Chair10;
    @FXML
    private Rectangle LectRoom2Chair00;
    @FXML
    private Rectangle LectRoom2Projector;
    @FXML
    private Path LectRoom2Light00;
    @FXML
    private Path LectRoom2Light01;
    @FXML
    private Path LectRoom2Light10;
    @FXML
    private Path LectRoom2Light11;
    @FXML
    private Path LectRoom2ProjectorView;
    @FXML
    private Rectangle floorPlanDoor;

    static ControlPanelAnim ctrla = new ControlPanelAnim();
    
    PageController utilPanel;

    public static String graph = "GraphPage";
    public static String graphFile = "graphPanel.fxml";
    public static String simulationPlanner = "SimulationPlannerPage";
    public static String simulationPlannerFile = "simulationPlanner.fxml";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilPanel = new PageController();
        utilPanel.loadPage(graph, graphFile);
        utilPanel.loadPage(simulationPlanner, simulationPlannerFile);
        utilPanel.setLayoutY(420);
        
        utilPanel.setPage(simulationPlanner);
        
        // TODO
        ctrla.setPanel(controlPanel);
        
        floorPlan.getChildren().add(utilPanel);

        //converting javafx nodes on scene to Simulation objects
        Door o1Door = new Door(Office1Door.getId(), Office1Door, AnimationSubject.CP_Position.LEFT);
        SimObject o1Table = new SimObject(Office1Table.getId(), Office1Table, AnimationSubject.CP_Position.INTERNAL);
        SimObject o1Chair = new SimObject(Office1Chair.getId(), Office1Chair, AnimationSubject.CP_Position.TOP);
        IoTDevice o1AC = new IoTDevice(Office1AC.getId(), Office1AC, AnimationSubject.CP_Position.BOTTOM);
        IoTDevice o1TV = new IoTDevice(Office1TvView.getId(), Office1TvView, AnimationSubject.CP_Position.LEFT);
        IoTDevice o1Pc = new IoTDevice(Office1PcView.getId(), Office1PcView, AnimationSubject.CP_Position.LEFT);
        IoTDevice o1Light = new IoTDevice(Office1Light.getId(), Office1Light, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice o1Camera = new IoTDevice(Office1CameraView.getId(), Office1CameraView, AnimationSubject.CP_Position.INTERNAL);
        SimObject[] o1Objects = {o1Table, o1Chair, o1AC, o1TV, o1Pc, o1Light, o1Camera};
        Room o1Room = new Room(Office1.getId(), Office1, o1Door, AnimationSubject.CP_Position.LOWERRIGHT, o1Objects);
        
        Door o2Door = new Door(Office2Door.getId(), Office2Door, AnimationSubject.CP_Position.LEFT);
        SimObject o2Table = new SimObject(Office2Table.getId(), Office2Table, AnimationSubject.CP_Position.INTERNAL);
        SimObject o2Chair = new SimObject(Office2Chair.getId(), Office2Chair, AnimationSubject.CP_Position.TOP);
        IoTDevice o2AC = new IoTDevice(Office2AC.getId(), Office2AC, AnimationSubject.CP_Position.BOTTOM);
        IoTDevice o2TV = new IoTDevice(Office2TvView.getId(), Office2TvView, AnimationSubject.CP_Position.LEFT);
        IoTDevice o2Pc = new IoTDevice(Office2PcView.getId(), Office2PcView, AnimationSubject.CP_Position.LEFT);
        IoTDevice o2Light = new IoTDevice(Office2Light.getId(), Office2Light, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice o2Camera = new IoTDevice(Office2CameraView.getId(), Office2CameraView, AnimationSubject.CP_Position.INTERNAL);
        SimObject[] o2Objects = {o2Table, o2Chair, o2AC, o2TV, o2Pc, o2Light, o2Camera};
        Room o2Room = new Room(Office2.getId(), Office2, o2Door, AnimationSubject.CP_Position.LOWERRIGHT, o2Objects);
        
        Door tDoor = new Door(ToiletDoor.getId(), ToiletDoor, AnimationSubject.CP_Position.LEFT);
        SimObject tSink = new SimObject(ToiletSink.getId(), ToiletSink, AnimationSubject.CP_Position.TOP);
        SimObject wCloset = new SimObject(WaterCloset.getId(), WaterCloset, AnimationSubject.CP_Position.RIGHT);
        IoTDevice tLight = new IoTDevice(ToiletLight.getId(), ToiletLight, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice tCamera = new IoTDevice(ToiletCameraView.getId(), ToiletCameraView, AnimationSubject.CP_Position.INTERNAL);
        SimObject[] tObjects = {tSink, wCloset, tLight, tCamera};
        Room tRoom = new Room(Toilet.getId(), Toilet, tDoor, AnimationSubject.CP_Position.UPPERRIGHT, tObjects);
        
        Door lR1Door = new Door(LectureRoom1Door.getId(), LectureRoom1Door, AnimationSubject.CP_Position.RIGHT);
        SimObject lR1Projector = new SimObject(LectRoom1Projector.getId(), LectRoom1Projector, AnimationSubject.CP_Position.INTERNAL);
        LectureRoomSit lR1Sit00 = new LectureRoomSit(LectRoom1Chair00.getId(), LectRoom1Chair00, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR1Sit01 = new LectureRoomSit(LectRoom1Chair01.getId(), LectRoom1Chair01, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR1Sit02 = new LectureRoomSit(LectRoom1Chair02.getId(), LectRoom1Chair02, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR1Sit10 = new LectureRoomSit(LectRoom1Chair10.getId(), LectRoom1Chair10, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR1Sit11 = new LectureRoomSit(LectRoom1Chair11.getId(), LectRoom1Chair11, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR1Sit12 = new LectureRoomSit(LectRoom1Chair12.getId(), LectRoom1Chair12, AnimationSubject.CP_Position.BOTTOM);
        IoTDevice lR1Light00 = new IoTDevice(LectRoom1Light00.getId(), LectRoom1Light00, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR1Light01 = new IoTDevice(LectRoom1Light01.getId(), LectRoom1Light01, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR1Light10 = new IoTDevice(LectRoom1Light10.getId(), LectRoom1Light10, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR1Light11 = new IoTDevice(LectRoom1Light11.getId(), LectRoom1Light11, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR1ProjectorView = new IoTDevice(LectRoom1ProjectorView.getId(), LectRoom1ProjectorView, AnimationSubject.CP_Position.INTERNAL);
        SimObject[] lR1Objects = {lR1Projector, lR1Sit00, lR1Sit01, lR1Sit02, lR1Sit10, lR1Sit11, lR1Sit12, 
            lR1Light00, lR1Light01, lR1Light10, lR1Light11, lR1ProjectorView};
        Room lR1Room = new Room(LectureRoom1.getId(), LectureRoom1, lR1Door, AnimationSubject.CP_Position.UPPERRIGHT, lR1Objects);
        
        Door lR2Door = new Door(LectureRoom2Door.getId(), LectureRoom2Door, AnimationSubject.CP_Position.RIGHT);
        SimObject lR2Projector = new SimObject(LectRoom2Projector.getId(), LectRoom2Projector, AnimationSubject.CP_Position.INTERNAL);
        LectureRoomSit lR2Sit00 = new LectureRoomSit(LectRoom2Chair00.getId(), LectRoom2Chair00, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR2Sit01 = new LectureRoomSit(LectRoom2Chair01.getId(), LectRoom2Chair01, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR2Sit02 = new LectureRoomSit(LectRoom2Chair02.getId(), LectRoom2Chair02, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR2Sit10 = new LectureRoomSit(LectRoom2Chair10.getId(), LectRoom2Chair10, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR2Sit11 = new LectureRoomSit(LectRoom2Chair11.getId(), LectRoom2Chair11, AnimationSubject.CP_Position.BOTTOM);
        LectureRoomSit lR2Sit12 = new LectureRoomSit(LectRoom2Chair12.getId(), LectRoom2Chair12, AnimationSubject.CP_Position.BOTTOM);
        IoTDevice lR2Light00 = new IoTDevice(LectRoom2Light00.getId(), LectRoom2Light00, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR2Light01 = new IoTDevice(LectRoom2Light01.getId(), LectRoom2Light01, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR2Light10 = new IoTDevice(LectRoom2Light10.getId(), LectRoom2Light10, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR2Light11 = new IoTDevice(LectRoom2Light11.getId(), LectRoom2Light11, AnimationSubject.CP_Position.INTERNAL);
        IoTDevice lR2ProjectorView = new IoTDevice(LectRoom2ProjectorView.getId(), LectRoom2ProjectorView, AnimationSubject.CP_Position.INTERNAL);
        SimObject[] lR2Objects = {lR2Projector, lR2Sit00, lR2Sit01, lR2Sit02, lR2Sit10, lR2Sit11, lR2Sit12, 
            lR2Light00, lR2Light01, lR2Light10, lR2Light11, lR2ProjectorView};
        Room lR2Room = new Room(LectureRoom2.getId(), LectureRoom2, lR2Door, AnimationSubject.CP_Position.UPPERRIGHT, lR2Objects);
        
        Door fPDoor = new Door(floorPlanDoor.getId(), floorPlanDoor, AnimationSubject.CP_Position.RIGHT);
        SimObject corridorAnchor = new SimObject(controlPanel.getId(), controlPanel, AnimationSubject.CP_Position.INTERNAL);
//        SimObject office1 = new SimObject
        SimObject[] fPObjects = {corridorAnchor};
        Room fPRoom = new Room(floorPlan.getId(), floorPlan, fPDoor, AnimationSubject.CP_Position.INTERNAL, fPObjects);

    }
    
    public void showSimulationPlanner(){
        
    }
    
    public void showEnergyStats(){
        
    }
    
    @FXML
    public void hideControlP(MouseEvent e){
        ctrla.ctrlPSAnimation();
    }
    
    @FXML
    public void stopBtnAction(ActionEvent e){
        ctrla.action(e);
    }
    
    @FXML
    public void pauseBtnAction(ActionEvent e){
        ctrla.action(e);
    }
    
    @FXML
    public void closeBtnAction(ActionEvent e){
        ctrla.action(e);
    }
    
    @FXML
    public void simPlannerBtnAction(ActionEvent e){
        ctrla.action(e);
    }
    
    @FXML
    public void playBtnAction(ActionEvent e){
        if(ctrla.ctrlState.equals("Revealed"))
            ctrla.action(e);
    }
    
    @FXML
    public void eStatBtnAction(ActionEvent e){
        ctrla.action(e);
    }
    
    @FXML
    public void playBtnM_Action(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY){
            if (ctrla.ctrlState.equals("Hidden")) 
                ctrla.revealActionAnimation();
            else {
//                System.out.println("State: Hidden control buttons");
                ctrla.hideActionAnimation();
            }

        }
    }
    
    @FXML
    public void ctrolPM_Action(MouseEvent e){
        if(ctrla.ctrlState.equals("Hidden"))
            ctrla.ctrolPHAnimation();
    }
    
//    Window drag feature
    double yOffset = 0;
    double xOffset = 0;
    
    @FXML
    private void requestfocus(MouseEvent event) {
        floorPlan.requestFocus();
    }

    @FXML
    private void determine(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    @FXML
    private void pick(MouseEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        ((Node) event.getSource()).setCursor(Cursor.CLOSED_HAND);
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    private void drop(MouseEvent event) {
        ((Node) event.getSource()).setCursor(Cursor.OPEN_HAND);
    }

}
