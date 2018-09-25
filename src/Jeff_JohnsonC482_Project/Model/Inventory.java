/**
 * Class for part and product inventory
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partIdCount = 1000;
    private static int productIdCount = 7000;
    
    //generates sequential partIds
    public static int getPartIdNumber() {
        partIdCount++;
        return partIdCount;
    }
    
    //generates sequesntial productIds
    public static int getProductIdNumber() {
        productIdCount++;
        return productIdCount;
    }
   
    //adds a product to the running product inventory
    public void addProduct(Product product) {
        products.add(product);
    }
    
    //removes a product from the running product inventory
    public static boolean removeProduct(int product) {
        products.remove(product);
        return true;
    }
    
    //returns the index of a product from the running product inventory
    public static Product lookupProduct(int index) {
        return products.get(index);
    }
    
    //updates a product located in the running product inventory
    public void updateProduct(int index, Product product) {
        products.set(index, product);
    }
    
    //adds a part to the running part inventory
    public static void addPart(Part part) {
        allParts.add(part);        
    }
    
    //removes a part from the running part inventory
    public static boolean deletePart(Part part) {
        allParts.remove(part);
        return true;
    }
    
    //returns the index of a part from the running part inventory
    public static Part lookupPart(int index) {
        return allParts.get(index);
    }
    
    //updates a part located in the running part inventory
    public void updatePart(int index, Part part) {
        allParts.set(index, part);
    }
        
}
