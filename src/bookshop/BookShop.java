
package bookshop;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Programmer: Abhishek Jassal
 * Program: PROG24178 OOP JAVA-2
 * @version 2.1
 * @author Abhishek
 */
public class BookShop extends Application {

    /**
     * This main method which calls the launch method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
    /**
     * This is start method which load FXML FILE 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
        Scene scene=new Scene(root);
        stage.setTitle("Book Shop");        
        stage.setScene(scene);
        stage.show();
    
}
}
