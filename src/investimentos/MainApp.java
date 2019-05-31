package investimentos;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

         
        @FXML
        AnchorPane pane;
        
        @FXML
        BorderPane root;
    
	// instanciando stage
	public static Stage stage;

	// instanciando os scenes
	public static Scene sceneRoot;
	public static Scene sceneInvestimento;

	private static String screen = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		
            stage = primaryStage;
		
                // Titulo
                stage.setTitle("Simulador de Investimentos");                
 		// Tela inicial                
                                                            
		root = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
                
                /*PieChart pieChart = new PieChart();       
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Poupança", 13), 
                new PieChart.Data("CDB", 25), 
                new PieChart.Data("Ações", 22));        
                pieChart.setData(pieChartData); 
                pieChart.setLabelLineLength(200);
                pieChart.setTitle("Meus Investimentos");
                pieChart.setLabelsVisible(true);
                
                
                root.getChildren().add(pieChart);
               */
                        
                sceneRoot = new Scene(root);
                
		// Tela de simulacao
		Parent single = FXMLLoader.load(getClass().getResource("view/Investimento.fxml"));
		sceneInvestimento = new Scene(single);                                                           
		stage.setScene(sceneRoot);
		stage.setResizable(false);
		stage.centerOnScreen();                 
		stage.show();

		// Fechando as janelas
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				t.consume();

				switch (screen) {

				case "Simulador":

					screen = "Root";

					stage.setTitle("Simulador de Investimentos");
					stage.setScene(sceneRoot);
					notifyAllListeners(screen, null);
					break;

				default:
					stage.close();
					Platform.exit();
					System.exit(0);
				}

			}
		});

	}
        
	// Metodos para a navegacao entre as janelas
	public static void changeScreen(String scr) {
		changeScreen(scr, null);
	}

	public static void changeScreen(String scr, Object userData) {

		stage.setScene(sceneInvestimento);
                stage.centerOnScreen();                 
		screen = scr;

		stage.setTitle(scr);
		notifyAllListeners(scr, userData);

	}

	public static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
	public static interface OnChangeScreen {
		void onScreenChanged(String newScreen, Object userData);
	}

	public static void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}

	public static void notifyAllListeners(String newScreen, Object userData) {
		for (OnChangeScreen l : listeners)
			l.onScreenChanged(newScreen, userData);
	}
        
       

}
