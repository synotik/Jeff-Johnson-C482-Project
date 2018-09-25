/**
 * Class for part parent 
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Part {
    
    private SimpleIntegerProperty partId, inStock, min, max;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
        
    //constructor
    public Part() {
        partId = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        inStock = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    //partId getter
    public int getPartId() {
        return partId.getValue();
    }

    //partId setter
    public void setPartId(int partId) {
        this.partId.set(partId);
    }

    //part name getter
    public String getName() {
        return name.getValue();
    }

    //part name setter
    public void setName(String name) {
        this.name.set(name);
    }

    //inventory level getter
    public int getInStock() {
        return inStock.getValue();
    }

    //inventory level setter
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    //part price getter
    public double getPrice() {
        return price.getValue();
    }

    //part price setter
    public void setPrice(double price) {
        this.price.set(price);
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

}
