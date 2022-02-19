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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author PETER-PC
 */
public class GraphPanelController implements Initializable, ControlledPage {

    @FXML
    private AnchorPane graphPanel;
    
    PageController myController;

    @Override
    public void setPageParent(PageController screenParent) {
        myController = screenParent;
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void hideUtilPanel(ActionEvent e) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("FXMLSim.fxml"));
        StackPane utilPanel = (StackPane) root.getChildren().get(root.getChildren().size() - 1);
        FXMLDocumentController.ctrla.hidePane(utilPanel);
        FXMLDocumentController.ctrla.utilPanelAnim(root, utilPanel, true, e);
    }
}
