/**
 * Controller for the Add Parts screen
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.View_Controller;

import Jeff_JohnsonC482_Project.Model.Inhouse;
import Jeff_JohnsonC482_Project.Model.Inventory;
import static Jeff_JohnsonC482_Project.Model.Inventory.getPartIdNumber;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartScreenController implements Initializable {

    private boolean isSelected = true;
    private int partIdNumber;
    
    @FXML private Label addPartIdLabelChanger;
    @FXML private TextField addPartNameTextBox;
    @FXML private TextField addPartInvTextBox;
    @FXML private TextField addPartPriceCostTextBox;
    @FXML private TextField addPartMinTextBox;
    @FXML private TextField addPartMaxTextBox;
    @FXML private TextField addPartChangingTextBox;
    @FXML private Label addPartChangingLabel;
    @FXML private RadioButton addPartInhouseRadioButton;
    @FXML private RadioButton addPartOutsourcedRadioButton;
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        addPartInhouseRadioButton.setToggleGroup(tg);
        addPartOutsourcedRadioButton.setToggleGroup(tg);
        addPartInhouseRadioButton.setSelected(true);
        
        partIdNumber = getPartIdNumber();
        addPartIdLabelChanger.setText(Integer.toString((partIdNumber)));
    }
    
    @FXML private boolean handleInhouseRadioButton() {
        isSelected = true;
        addPartChangingLabel.setText("Machine ID");
        addPartChangingTextBox.setPromptText("Machine ID");
        return isSelected;        
    }
    
    @FXML private boolean handleOutsourcedRadioButton() {
        isSelected = false;
        addPartChangingLabel.setText("Company Name");
        addPartChangingTextBox.setPromptText("Company Name");
        return isSelected;        
    }
    
    @FXML private void handleCancelButton(ActionEvent event) {
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
    
    @FXML private void handleSaveButton(ActionEvent event) throws IOException{
        String partId = Integer.toString(partIdNumber);
        String partName = addPartNameTextBox.getText();
        String inStock = addPartInvTextBox.getText();
        String price = addPartPriceCostTextBox.getText();
        String minStock = addPartMinTextBox.getText();
        String maxStock = addPartMaxTextBox.getText();
        String changingStat = addPartChangingTextBox.getText();        
        
        try {
            if (Integer.parseInt(inStock) > Integer.parseInt(maxStock) || Integer.parseInt(inStock) < Integer.parseInt(minStock)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("The inventory value is either higher than the maximum allowed or lower than the minimum allowed.");
                alert.showAndWait();
            } else {
            
                if(isSelected) {  
                    Inhouse inhouse = new Inhouse();
                    inhouse.setPartId(Integer.parseInt(partId));
                    inhouse.setName(partName);            
                    inhouse.setInStock(Integer.parseInt(inStock));
                    inhouse.setPrice(Double.parseDouble(price));
                    inhouse.setMin(Integer.parseInt(minStock));
                    inhouse.setMax(Integer.parseInt(maxStock));
                    inhouse.setMachineId(Integer.parseInt(changingStat));
            
                    Inventory.addPart(inhouse);
                } else {
                    Outsourced outsourced = new Outsourced();
                    outsourced.setPartId(Integer.parseInt(partId));
                    outsourced.setName(addPartNameTextBox.getText());
                    outsourced.setInStock(Integer.parseInt(addPartInvTextBox.getText()));
                    outsourced.setPrice(Double.parseDouble(addPartPriceCostTextBox.getText()));
                    outsourced.setMin(Integer.parseInt(addPartMinTextBox.getText()));
                    outsourced.setMax(Integer.parseInt(addPartMaxTextBox.getText()));
                    outsourced.setCompanyName(addPartChangingTextBox.getText());

                    Inventory.addPart(outsourced);
                }
        
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }
        }    
        catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Adding Part");
            alert.setContentText("All fields are required.");
            alert.showAndWait();
        }
    }
}
