package investimentos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {
        
    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Investimentos.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Investimentos");
        primaryStage.resizableProperty().setValue(false);        
        primaryStage.show();
    }
    
     public static void main(String[] args)
     {   
      launch(args);
     } 
}
