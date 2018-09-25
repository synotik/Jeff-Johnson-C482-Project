/**
 * Class for the Outsourced part
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.Model;

import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {
    
    private SimpleStringProperty companyName;
    
    //constructor
    public Outsourced() {
        companyName = new SimpleStringProperty();
    }
    
    //getter for the Company Name which is specific to Outsourced parts
    public String getCompanyName() {
        return companyName.getValue();
    }
    
    //setter for the Company Name which is specific to Outsourced parts
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}
