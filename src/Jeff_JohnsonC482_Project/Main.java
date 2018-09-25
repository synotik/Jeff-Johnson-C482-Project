/*
* Created by Jeff Johnson on 9/4/2018
 */
package Jeff_JohnsonC482_Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {        
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/MainScreen.fxml"));
        Scene scene = new Scene(root, 1150, 500);        
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Inventory Management System");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //method inside in the Application class sets up app as a
        //JavaFX application, then calls the start method
        launch(args);
    }
    
   
    
}
