package investimentos;

import investimentos.MainApp;
import static investimentos.MainApp.notifyAllListeners;
import static investimentos.MainApp.sceneInvestimento;
import static investimentos.MainApp.sceneRoot;
import static investimentos.MainApp.stage;
import investimentos.model.Pessoa;
import investimentos.persist.ContaDAO;
import investimentos.persist.PessoaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

public class RootLayoutController {

        @FXML
        AnchorPane pane;
                       
        @FXML
        Button btnComparativo;
        
        @FXML
        PieChart pieChart;  
        @FXML
        Text numeroConta;
        @FXML
        Button btnSair;
        @FXML
        Text txtSaldo;
        @FXML
        Button btnAplicar;
        @FXML
        Button btnResgatar;
        @FXML
        Text txt_total;
                
        private Pessoa p = new Pessoa();
        private ArrayList<Pessoa> listaPessoas;
	private static String screen = "Simulador";
        private Conta conta = new Conta();        
        
        public void rootLayoutController(Pessoa p,ArrayList<Pessoa> listaPessoas)
        {
            this.p = p;
            this.listaPessoas = listaPessoas;
            numeroConta.setText(String.valueOf(p.getNumeroConta()));                                                        
            ContaDAO contaDAO = null;
             try {
            contaDAO = new ContaDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
            }                        
            try {                   
            conta = contaDAO.getConta(p.getNumeroConta());                    
            } catch (SQLException ex) {
                Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            txtSaldo.setText(String.valueOf(conta.getSaldo()));        
            pane.getChildren().remove(pieChart);    
            pieChart = new PieChart();                        
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Poupança", conta.getPoupanca()),
            new PieChart.Data("CDB", conta.getCdb()));
            pieChart.setData(pieChartData);
            pieChart.setMaxWidth(300);
            pieChart.setMaxHeight(300);
            pieChart.setTranslateX(250);
            pieChart.setTranslateY(120);
            pieChart.setTitle("Meus Investimentos");
            pieChart.setLabelsVisible(true);
            pieChartData.forEach(data ->
            data.nameProperty().bind(
                            Bindings.concat(
                            data.getName(), "R$  ", data.pieValueProperty(),"  "
                    )
                )
            );
        
            pane.getChildren().add(pieChart);                 
            Double total = conta.getSaldo() + conta.getPoupanca() + conta.getCdb();
            txt_total.setText(total.toString());
            
        }               
        
        public void atualiza(){                          
        
        ContaDAO contaDAO = null;
        try {
        contaDAO = new ContaDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        try {                   
        conta = contaDAO.getConta(p.getNumeroConta());                    
        } catch (SQLException ex) {
            Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtSaldo.setText(String.valueOf(conta.getSaldo()));        
        pane.getChildren().remove(pieChart);    
        pieChart = new PieChart();                        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Poupança", conta.getPoupanca()),
        new PieChart.Data("CDB", conta.getCdb()));
        pieChart.setData(pieChartData);
        pieChart.setMaxWidth(300);
        pieChart.setMaxHeight(300);
        pieChart.setTranslateX(250);
        pieChart.setTranslateY(120);
        pieChart.setTitle("Meus Investimentos");
        pieChart.setLabelsVisible(true);
        pieChartData.forEach(data ->
        data.nameProperty().bind(
                        Bindings.concat(
                        data.getName(), "R$  ", data.pieValueProperty(),"  "
                )
        )
        );
        
        pane.getChildren().add(pieChart);                 
        Double total = conta.getSaldo() + conta.getPoupanca() + conta.getCdb();
        txt_total.setText(total.toString());
            
        }               
        
        public void initialize(){
        
        //atualiza();           
        
        pane.setOnMouseMoved( k -> this.atualiza());
        
        /*btnComparativo.setOnKeyPressed(k -> 
        {
                final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
                if (ENTER.match(k)) {
                    
                        
                        this.changeScreen(event);                     
                        Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                });*/        
        }
        
        /*public void RootLayoutController(PieChart pieChart)
        {
            this.pieChart = pieChart;
        }*/

    @FXML
    public void Extrato()throws IOException, ClassNotFoundException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Extrato.fxml"));
        Pane root = loader.load();
        ExtratoController controller = (ExtratoController) loader.getController();        
        controller.ExtratoController(conta);        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Extrato de Movimentações");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    } 
        
    @FXML
    public void Aplicar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AplicacaoResgate.fxml"));
        Pane root = loader.load();
        AplicacaoResgateController controller = (AplicacaoResgateController) loader.getController();
        controller.AplicacaoResgateController(conta,"Aplicar");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Aplicar um novo valor");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    @FXML
    public void Resgatar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AplicacaoResgate.fxml"));
        Pane root = loader.load();
        AplicacaoResgateController controller = (AplicacaoResgateController) loader.getController();
        controller.AplicacaoResgateController(conta,"Resgatar");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Resgatar um novo valor");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
        
    
    @FXML
    public void alterarCadastro() throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
        Pane root = loader.load();
        CadastroController controller = (CadastroController) loader.getController();
        controller.alterarPessoa(p,listaPessoas);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Alterar cadastro");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
        
        
        @FXML
	private void changeScreen(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Investimento.fxml"));
            Pane root = loader.load();
            InvestimentoController controller = (InvestimentoController) loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.centerOnScreen();                 		
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.resizableProperty().setValue(false);
            stage.show();
            
                /*//Tela de simulacao
		Parent single = FXMLLoader.load(getClass().getResource("Investimento.fxml"));
		sceneInvestimento = new Scene(single);                                                           
		stage.setScene(sceneInvestimento);
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

				//	screen = "Root";

					stage.setTitle("Simulador de Investimentos");
					stage.setScene(scene);
					notifyAllListeners(screen, null);
					break;

				default:
					stage.close();
					Platform.exit();
					System.exit(0);
				}

			}
		});
		//String cmd = ((Button) event.getSource()).getText();
		//MainApp.changeScreen(cmd);
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
	*/}
        
    @FXML
    public void sair()
    {        
        if (JOptionPane.showConfirmDialog(
                null, 
                "Você deseja realmente sair? ","Fechar",
                        JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION) 
        {                   
            Stage stage = (Stage) btnSair.getScene().getWindow(); //Obtendo a janela atual
            stage.close(); //Fechando o Stage
        }
    }
}
