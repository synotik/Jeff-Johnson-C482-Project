/**
 * Class for the In-house part
 * Created by Jeff Johnson 09/2018
 */

package Jeff_JohnsonC482_Project.Model;

import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part{
  
    private SimpleIntegerProperty machineId;

    //constructor
    public Inhouse() {
        super();
        machineId = new SimpleIntegerProperty();
    }
    
    //getter for the machineId which is specific to Inhouse parts    
    public int getMachineId() {
        return machineId.getValue();
    }

    //setter for the machineId which is specific to Inhouse parts
    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }
}
