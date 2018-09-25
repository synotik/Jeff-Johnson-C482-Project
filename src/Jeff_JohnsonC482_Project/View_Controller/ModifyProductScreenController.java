/**
 * Controller for the Modify Product screen
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.View_Controller;

import Jeff_JohnsonC482_Project.Model.Inventory;
import Jeff_JohnsonC482_Project.Model.Part;
import Jeff_JohnsonC482_Project.Model.Product;
import static Jeff_JohnsonC482_Project.View_Controller.MainScreenController.isInteger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyProductScreenController implements Initializable {
   
    Product product = MainScreenController.productToModify;
    String selectedSearchPart;
    
    @FXML private TextField modifyProductIdTextField;
    @FXML private TextField modifyProductNameTextField;
    @FXML private TextField modifyProductInvTextField;
    @FXML private TextField modifyProductPriceTextField;
    @FXML private TextField modifyProductMinTextField;
    @FXML private TextField modifyProductMaxTextField;
    @FXML private TableView modifyProductNewTableView;
    @FXML private TableView modifyProductExistingTableView;
    @FXML private TableColumn modifyProductNewPartIdColumn;
    @FXML private TableColumn modifyProductExistingPartNameColumn;
    @FXML private TableColumn modifyProductInventoryLevelColumn;
    @FXML private TableColumn modifyProductPricePerUnitColumn;
    @FXML private TableColumn modifyProductExistingPartIdColumn;
    @FXML private TableColumn modifyProductNewPartNameColumn;
    @FXML private TableColumn modifyProductExistingInventoryLevelColumn;
    @FXML private TableColumn modifyProductExistingPricePerUnitColumn;
    @FXML private TextField modifyProductSearchTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modifyProductIdTextField.setText(Integer.toString(MainScreenController.productToModify.getProductId()));
        modifyProductNameTextField.setText(MainScreenController.productToModify.getName());
        modifyProductInvTextField.setText(Integer.toString(MainScreenController.productToModify.getInStock()));
        modifyProductPriceTextField.setText(Double.toString(MainScreenController.productToModify.getPrice()));
        modifyProductMinTextField.setText(Integer.toString(MainScreenController.productToModify.getMin()));
        modifyProductMaxTextField.setText(Integer.toString(MainScreenController.productToModify.getMax()));

        modifyProductNewPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyProductNewPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        modifyProductPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductExistingPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyProductExistingPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductExistingInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        modifyProductExistingPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));   
        
        modifyProductNewTableView.setItems(MainScreenController.productToModify.getAssociatedParts());
        modifyProductExistingTableView.setItems(Inventory.allParts);
    }

    @FXML public void handleSaveButton() {
        int index = Inventory.products.indexOf(MainScreenController.productToModify);
        
        product.setProductId(Integer.parseInt(modifyProductIdTextField.getText()));
        product.setName(modifyProductNameTextField.getText());
        product.setInStock(Integer.parseInt(modifyProductInvTextField.getText()));
        product.setPrice(Double.parseDouble(modifyProductPriceTextField.getText()));
        product.setMin(Integer.parseInt(modifyProductMinTextField.getText()));
        product.setMax(Integer.parseInt(modifyProductMaxTextField.getText()));
        
        Inventory.products.set(index, product);
    }
    
    @FXML public void handleAddButton() {
        modifyProductNewTableView.setItems(product.getAssociatedParts());
        product.addAssociatedPart((Part)modifyProductExistingTableView.getSelectionModel().getSelectedItem());
        modifyProductNewTableView.refresh();
    }
    
    @FXML public void handleDeleteButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Alert");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you wish to delete?");
        Optional<ButtonType> button = alert.showAndWait();
        
        if(button.get() == ButtonType.OK) {
            Part part = (Part) modifyProductNewTableView.getSelectionModel().getSelectedItem();
            int index = product.getAssociatedParts().indexOf(part);
            product.removeAssociatedPart(index);
        }
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

    @FXML public void handleSearchButton() {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        selectedSearchPart = modifyProductSearchTextField.getText();
        
        if (selectedSearchPart != null && isInteger(selectedSearchPart)) {
            for (int x = 0; x < Inventory.allParts.size(); x++) {
                if (Integer.parseInt(selectedSearchPart) == Inventory.lookupPart(x).getPartId()) {
                    tempList.add(Inventory.lookupPart(x));
                }
            }
        } else {    
            for (int x= 0; x < Inventory.allParts.size(); x++) {
                if (selectedSearchPart.toLowerCase().equals(Inventory.lookupPart(x).getName().toLowerCase())) {
                    tempList.add(Inventory.lookupPart(x));
                }
            }
            
        }
        modifyProductExistingTableView.setItems(tempList);
    }
}
