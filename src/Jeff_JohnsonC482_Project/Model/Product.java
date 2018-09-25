/**
 * Class for product object
 * Created by Jeff Johnson 09/2018
 */
package Jeff_JohnsonC482_Project.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private SimpleIntegerProperty productId, inStock, min, max;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    
    //constructor
    public Product() {
        productId = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        inStock = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    //productId getter
    public int getProductId() {
        return productId.getValue();
    }

    //productId setter
    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    //inventory level getter
    public int getInStock() {
        return inStock.getValue();
    }
    
    //inventory level setter
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    //minimum inventory getter
    public int getMin() {
        return min.getValue();
    }
    
    //minimum inventory setter
    public void setMin(int min) {
        this.min.set(min);
        
    }

    //maximum inventory getter
    public int getMax() {
        return max.getValue();
    }

    //maximum inventory setter
    public void setMax(int max) {
        this.max.set(max);
    }

    //product name getter
    public String getName() {
        return name.getValue();
    }

    //product name setter
    public void setName(String name) {
        this.name.set(name);
    }

    //product price getter
    public double getPrice() {
        return price.getValue();
    }

    //product price setter
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    //add parts to the specific product
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }
    
    //remove parts from the specific product
    public boolean removeAssociatedPart(int part) {
        this.associatedParts.remove(part);
        return true;
    }
    
    //look for parts in the specific product
    public Part lookupAssociatedPart(int part) {
        return associatedParts.get(part);
    }

    //return list of parts 
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    
}
