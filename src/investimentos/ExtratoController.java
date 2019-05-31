/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investimentos;

import investimentos.persist.ExtratoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Édipo
 */
public class ExtratoController implements Initializable {

    @FXML
    Text numeroConta;
    @FXML
    Text txtSaldo;          
    @FXML
    private Button btnSair;        
    @FXML
    private TableView<Extrato> tableview_extrato;
    private final ObservableList<Extrato> e = FXCollections.observableArrayList();
    private Conta conta;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    
    public void ExtratoController(Conta conta) throws ClassNotFoundException, SQLException
    {
        this.conta = conta;
        numeroConta.setText(String.valueOf(conta.getNumeroConta()));                                                        
        txtSaldo.setText(String.valueOf(conta.getSaldo()));                  
            
        ExtratoDAO extratoDAO = new ExtratoDAO();
        ArrayList<Extrato> extratos = extratoDAO.getExtrato(conta.getNumeroConta());
        
        TableColumn<Extrato, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setPrefWidth(125);
        dataColumn.setCellValueFactory( new PropertyValueFactory<>("data") );
        dataColumn.setResizable(false);
        dataColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Extrato, String> tipoColumn = new TableColumn<>("Ação");
        tipoColumn.setPrefWidth(60);
        tipoColumn.setCellValueFactory( new PropertyValueFactory<>("tipo") );
        tipoColumn.setResizable(false);
        tipoColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Extrato, String> investimentoColumn = new TableColumn<>("Investimento");
        investimentoColumn.setPrefWidth(80);
        investimentoColumn.setCellValueFactory( new PropertyValueFactory<>("investimento") );
        investimentoColumn.setResizable(false);
        investimentoColumn.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Extrato, String> valorColumn = new TableColumn<>("Valor");
        valorColumn.setPrefWidth(60);
        valorColumn.setCellValueFactory( new PropertyValueFactory<>("valor")) ;
        valorColumn.setResizable(false);
        valorColumn.setStyle("-fx-alignment: CENTER;");
    
        for(int i = 0 ; i < extratos.size() ; i++)
        {
            e.add(extratos.get(i));   
        }
        
        tableview_extrato.setItems(e);
        tableview_extrato.getColumns().addAll(dataColumn,tipoColumn,investimentoColumn,valorColumn);
    }
  
    @FXML
    public void sair()
    {
        Stage stage = (Stage) btnSair.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }
    
    
    
                            
}
