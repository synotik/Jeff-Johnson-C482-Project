/**
 * Controller for the Add Product screen
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.View_Controller;

import Jeff_JohnsonC482_Project.Model.Inventory;
import static Jeff_JohnsonC482_Project.Model.Inventory.getProductIdNumber;
import Jeff_JohnsonC482_Project.Model.Part;
import Jeff_JohnsonC482_Project.Model.Product;
import static Jeff_JohnsonC482_Project.View_Controller.MainScreenController.isInteger;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductScreenController implements Initializable {

    Product product = new Product();
    private int productIdNumber;
    public String selectedSearchPart;
    
    @FXML private TableView addProductExistingTableView;
    @FXML private TableColumn addProductExistingPartIdColumn;
    @FXML private TableColumn addProductExistingPartNameColumn;
    @FXML private TableColumn addProductExistingInventoryLevelColumn;
    @FXML private TableColumn addProductExistingPricePerUnitColumn;
    @FXML private TableView addProductNewTableView;
    @FXML private TableColumn addProductAddedPartIdColumn;
    @FXML private TableColumn addProductAddedPartNameColumn;
    @FXML private TableColumn addProductAddedInventoryLevelColumn;
    @FXML private TableColumn addProductAddedPricePerUnitColumn;
    @FXML private TextField addProductNameTextField;
    @FXML private TextField addProductInvTextField;
    @FXML private TextField addProductPriceTextField;
    @FXML private TextField addProductMinTextField;
    @FXML private TextField addProductMaxTextField;
    @FXML private Button addProductSaveButton;
    @FXML private Label addProductIdLabelChanger;
    @FXML private TextField addProductSearchTextField;
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        productIdNumber = getProductIdNumber();
        addProductIdLabelChanger.setText(Integer.toString(productIdNumber));
        
        addProductExistingPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addProductExistingPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductExistingInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addProductExistingPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        addProductAddedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addProductAddedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAddedInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addProductAddedPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));        

        addProductExistingTableView.setItems(Inventory.allParts);
    }
    
    @FXML public void handleSaveButton() throws IOException {
        String productName = addProductNameTextField.getText();
        String inStock = addProductInvTextField.getText();
        String price = addProductPriceTextField.getText();
        String minStock = addProductMinTextField.getText();
        String maxStock = addProductMaxTextField.getText();
        
        
        product.setProductId(productIdNumber);
        product.setName(productName);
        product.setInStock(Integer.parseInt(inStock));
        product.setPrice(Double.parseDouble(price));
        product.setMin(Integer.parseInt(minStock));
        product.setMax(Integer.parseInt(maxStock));
        
        Inventory.products.add(product);
        
        addProductSaveButton.setDisable(true);
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
    
    @FXML public void handleAddButton(ActionEvent event) {
        addProductNewTableView.setItems(product.getAssociatedParts());
        product.addAssociatedPart((Part)addProductExistingTableView.getSelectionModel().getSelectedItem());
        addProductNewTableView.refresh();
    }
    
    @FXML public void handleDeleteButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Alert");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you wish to delete?");
        Optional<ButtonType> button = alert.showAndWait();
        
        if(button.get() == ButtonType.OK) {
            Part part = (Part) addProductNewTableView.getSelectionModel().getSelectedItem();
            int index = product.getAssociatedParts().indexOf(part);
            product.removeAssociatedPart(index);
        }
    }
    
    @FXML public void handleSearchButton() {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        selectedSearchPart = addProductSearchTextField.getText();
        
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
        addProductExistingTableView.setItems(tempList);
    }
}
