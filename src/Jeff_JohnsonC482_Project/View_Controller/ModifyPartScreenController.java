/**
 * Controller for the Modify Parts screen
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.View_Controller;

import Jeff_JohnsonC482_Project.Model.Inhouse;
import Jeff_JohnsonC482_Project.Model.Inventory;
import Jeff_JohnsonC482_Project.Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyPartScreenController implements Initializable {
           
    @FXML private Label modifyPartIdLabelChanger;
    @FXML private TextField modifyPartNameTextBox;
    @FXML private TextField modifyPartInvTextBox;
    @FXML private TextField modifyPartPriceCostTextBox;
    @FXML private TextField modifyPartMinTextBox;
    @FXML private TextField modifyPartMaxTextBox;
    @FXML private RadioButton modifyPartInhouseRadioButton;
    @FXML private RadioButton modifyPartOutsourcedRadioButton;
    @FXML private Label modifyPartChangingLabel;
    @FXML private TextField modifyPartChangingTextBox;
      
    @Override public void initialize(URL url, ResourceBundle rb) {       
        modifyPartIdLabelChanger.setText(Integer.toString(MainScreenController.partToModify.getPartId()));
        modifyPartNameTextBox.setText(MainScreenController.partToModify.getName());
        modifyPartInvTextBox.setText(Integer.toString(MainScreenController.partToModify.getInStock()));
        modifyPartPriceCostTextBox.setText(Double.toString(MainScreenController.partToModify.getPrice()));
        modifyPartMinTextBox.setText(Integer.toString(MainScreenController.partToModify.getMin()));
        modifyPartMaxTextBox.setText(Integer.toString(MainScreenController.partToModify.getMax()));
        if (MainScreenController.partToModify instanceof Inhouse) {
            Inhouse tempPart = (Inhouse) MainScreenController.partToModify;
            modifyPartChangingTextBox.setText(Integer.toString(tempPart.getMachineId()));
            modifyPartInhouseRadioButton.setSelected(true);
            modifyPartChangingLabel.setText("Machine ID");
        } else {
            Outsourced tempPart = (Outsourced) MainScreenController.partToModify;
            modifyPartChangingTextBox.setText(tempPart.getCompanyName());
            modifyPartOutsourcedRadioButton.setSelected(true);
            modifyPartChangingLabel.setText("Company Name");
        }
    }
    
    @FXML public void handleSaveButton(ActionEvent event) throws IOException{
         if (MainScreenController.partToModify instanceof Inhouse) {
            int index = Inventory.allParts.indexOf(MainScreenController.partToModify);
            
            Inhouse tempPart = (Inhouse) MainScreenController.partToModify;
            tempPart.setPartId(Integer.parseInt(modifyPartIdLabelChanger.getText()));
            tempPart.setName(modifyPartNameTextBox.getText());
            tempPart.setInStock(Integer.parseInt(modifyPartInvTextBox.getText()));
            tempPart.setPrice(Double.parseDouble(modifyPartPriceCostTextBox.getText()));
            tempPart.setMin(Integer.parseInt(modifyPartMinTextBox.getText()));
            tempPart.setMax(Integer.parseInt(modifyPartMaxTextBox.getText()));
            tempPart.setMachineId(Integer.parseInt(modifyPartChangingTextBox.getText()));
            
            Inventory.allParts.set(index, tempPart);
        } else {
            int index = Inventory.allParts.indexOf(MainScreenController.partToModify);
            
            Outsourced tempPart = (Outsourced) MainScreenController.partToModify;
            tempPart.setPartId(Integer.parseInt(modifyPartIdLabelChanger.getText()));
            tempPart.setName(modifyPartNameTextBox.getText());
            tempPart.setInStock(Integer.parseInt(modifyPartInvTextBox.getText()));
            tempPart.setPrice(Double.parseDouble(modifyPartPriceCostTextBox.getText()));
            tempPart.setMin(Integer.parseInt(modifyPartMinTextBox.getText()));
            tempPart.setMax(Integer.parseInt(modifyPartMaxTextBox.getText()));
            tempPart.setCompanyName(modifyPartChangingTextBox.getText());
            
            Inventory.allParts.set(index, tempPart);
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();        
    }

    @FXML public void handleCancelButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Alert");
        alert.setHeaderText("Cancel?");
        alert.setContentText("Are you sure you wish to cancel?");
        Optional<ButtonType> button = alert.showAndWait();
        
        if(button.get() == ButtonType.OK) {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }    
}
