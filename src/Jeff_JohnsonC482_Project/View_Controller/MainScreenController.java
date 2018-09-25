/**
 * Controller for the Main screen
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.View_Controller;

import Jeff_JohnsonC482_Project.Model.Inventory;
import Jeff_JohnsonC482_Project.Model.Part;
import Jeff_JohnsonC482_Project.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    public static Part partToModify;
    public static String selectedSearchPart;
    public static String selectedSearchProduct;
    public static int searchPartIndex;
    public static Product productToModify;

    @FXML public TableView<Part> mainScreenPartsTableView;
    @FXML private TableColumn<Part, Integer> mainPartsPartIdTableColumn;
    @FXML private TableColumn<Part, String> mainPartsPartNameTableColumn;
    @FXML private TableColumn<Part, Integer> mainPartsInventoryLevelTableColumn;
    @FXML private TableColumn<Part, Double> mainPartsPriceCostTableColumn;

    @FXML public TableView<Product> mainScreenProductsTableView;
    @FXML private TableColumn<Product, Integer> mainProductsProductIdTableColumn;
    @FXML private TableColumn<Product, String> mainProductsProductNameTableColumn;
    @FXML private TableColumn<Product, Integer> mainProductsInventoryLevelTableColumn;
    @FXML private TableColumn<Product, Double> mainProductsPriceCostTableColumn;

    @FXML private TextField mainScreenPartsSearchTextField;
    @FXML private TextField mainScreenProductsSearchTextField;
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        mainPartsPartIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        mainPartsPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartsInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        mainPartsPriceCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainProductsProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        mainProductsProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductsInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        mainProductsPriceCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));        

        mainScreenPartsTableView.setItems(Inventory.allParts);
        mainScreenProductsTableView.setItems(Inventory.products);
    }


    @FXML private void handleAddPartButton(ActionEvent click) throws IOException {
        Parent addPartScreenParent = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene addPartScreenScene = new Scene(addPartScreenParent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(addPartScreenScene);
        stage.show();
    }
    
    @FXML private void handleAddProductButton(ActionEvent click) throws IOException {
        Parent addPartScreenParent = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        Scene addPartScreenScene = new Scene(addPartScreenParent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(addPartScreenScene);
        stage.show();
    }
    
    @FXML private void handleModifyPartButton(ActionEvent click) throws IOException {
        partToModify = mainScreenPartsTableView.getSelectionModel().getSelectedItem();
        
        Parent addPartScreenParent = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
        Scene addPartScreenScene = new Scene(addPartScreenParent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(addPartScreenScene);
        stage.show();
        
    }
    
    @FXML private void handleModifyProductButton(ActionEvent click) throws IOException {  
        productToModify = mainScreenProductsTableView.getSelectionModel().getSelectedItem();
        
        Parent addPartScreenParent = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
        Scene addPartScreenScene = new Scene(addPartScreenParent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(addPartScreenScene);
        stage.show();
    }
    
    @FXML private void handleDeletePartButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Alert");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you wish to delete?");
        Optional<ButtonType> button = alert.showAndWait();
        
        if(button.get() == ButtonType.OK) {
            Part selectedPart = mainScreenPartsTableView.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        }
    }
    
    @FXML private void handleDeleteProductButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Alert");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you wish to delete?");
        Optional<ButtonType> button = alert.showAndWait();
        
        if(button.get() == ButtonType.OK) {
            Product selectedProduct = mainScreenProductsTableView.getSelectionModel().getSelectedItem();
            int index = Inventory.products.indexOf(selectedProduct);
            Inventory.removeProduct(index);
        }
    }  
    
    @FXML private void handlePartSearchButton() {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        selectedSearchPart = mainScreenPartsSearchTextField.getText();
        
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
        mainScreenPartsTableView.setItems(tempList);
    }
    
    @FXML private void handleProductSearchButton() {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        selectedSearchProduct = mainScreenProductsSearchTextField.getText();
        
        if (selectedSearchProduct != null && isInteger(selectedSearchProduct)) {
            for (int x = 0; x < Inventory.products.size(); x++) {
                if (Integer.parseInt(selectedSearchProduct) == Inventory.lookupProduct(x).getProductId()) {
                    tempList.add(Inventory.lookupProduct(x));
                }
            }
        } else {
            for (int x = 0; x < Inventory.products.size(); x++) {
                if (selectedSearchProduct.toLowerCase().equals(Inventory.lookupProduct(x).getName().toLowerCase())) {
                    tempList.add(Inventory.lookupProduct(x));
                }
            }
        }
        mainScreenProductsTableView.setItems(tempList);
    }
    
    public static boolean isInteger(String test) {
        try {
            Integer.parseInt(test);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    @FXML public void handleExitButton() {
        System.exit(0);
    }
}
