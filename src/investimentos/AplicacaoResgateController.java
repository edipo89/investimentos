/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investimentos;

import investimentos.persist.ContaDAO;
import investimentos.persist.ExtratoDAO;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Édipo
 */
public class AplicacaoResgateController implements Initializable {

    @FXML
    private TextField txt_saldo;
    @FXML
    private TextField txt_valor;
    @FXML
    private TextField txt_poupanca;
    @FXML
    private TextField txt_cdb;
    @FXML
    private Text txt_aplicacao;  
    @FXML
    private Text txt_resgate;
    @FXML
    private ComboBox combo_invest;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_ok;
    
    private Conta conta;
    private String acao;
    @FXML
    private TextField txt_senhaVisivel;
    
    public void AplicacaoResgateController(Conta conta, String acao)
    {            
            this.conta = conta;            
            this.acao = acao;            
            ObservableList<String> options = 
                FXCollections.observableArrayList("Poupanca","CDB"); 
        combo_invest.setItems(options);
        txt_saldo.setText(conta.getSaldo().toString());
        if(acao.equals("Aplicar"))
        {          
            txt_aplicacao.setVisible(true);
        }
        else
        {
            txt_resgate.setVisible(true);
        }
        txt_poupanca.setText(conta.getPoupanca().toString());
        txt_cdb.setText(conta.getCdb().toString());
    }
    
     
    @FXML
    public void OK() throws ClassNotFoundException
    {
        
            ContaDAO contaDAO = new ContaDAO();

            if(txt_valor.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"É necessário preencher um valor!");                
            }            
            else
            {
                if(acao.equals("Aplicar"))
                {
                    if((conta.getSaldo()-Double.parseDouble(txt_valor.getText())) <= 0 ){
                        JOptionPane.showMessageDialog(null,"Saldo insuficiente. Preencha outro valor menor!");                
                    }
                    else{
                        if(combo_invest.getValue().equals("Poupanca"))
                        {
                            Conta alterada = new Conta();
                            alterada.setNumeroConta(conta.getNumeroConta());
                            alterada.setSaldo(conta.getSaldo()-Double.parseDouble(txt_valor.getText()));
                            alterada.setPoupanca(conta.getPoupanca()+Double.parseDouble(txt_valor.getText()));
                            alterada.setCdb(conta.getCdb());                        
                            contaDAO.alterar(alterada);
                            JOptionPane.showMessageDialog(null,"Aplicação feita com sucesso!");
                            
                            Extrato extrato = new Extrato();
                            ExtratoDAO extratoDAO = new ExtratoDAO();
                            Date dataAtual = new Date();
                            extrato.setConta(conta.getNumeroConta());
                            extrato.setData(dataAtual.toString());
                            extrato.setTipo(acao);
                            extrato.setInvestimento("Poupanca");                            
                            extrato.setValor(Double.parseDouble(txt_valor.getText()));                                                
                            extratoDAO.inserir(extrato);
                        }
                        else{
                            Conta alterada = new Conta();
                            alterada.setNumeroConta(conta.getNumeroConta());
                            alterada.setSaldo(conta.getSaldo()-Double.parseDouble(txt_valor.getText()));
                            alterada.setPoupanca(conta.getPoupanca());
                            alterada.setCdb(conta.getCdb()+Double.parseDouble(txt_valor.getText()));                        
                            contaDAO.alterar(alterada);
                            JOptionPane.showMessageDialog(null,"Aplicação feita com sucesso!");
                            
                            Extrato extrato = new Extrato();
                            ExtratoDAO extratoDAO = new ExtratoDAO();
                            Date dataAtual = new Date();
                            extrato.setData(dataAtual.toString());
                            extrato.setConta(conta.getNumeroConta());                            
                            extrato.setTipo(acao);
                            extrato.setInvestimento("CDB");                            
                            extrato.setValor(Double.parseDouble(txt_valor.getText()));                                                
                            extratoDAO.inserir(extrato);
                        }
                        this.cancelar();
                    }
                }
                else
                {
                       if(
                               conta.getCdb()- Double.parseDouble(txt_cdb.getText()) < 0 
                               ||
                               conta.getPoupanca() - Double.parseDouble(txt_poupanca.getText()) < 0                                
                         )                           
                        {
                            JOptionPane.showMessageDialog(null,"Saldo insuficiente para resgate. Preencha outro valor menor!");                
                        }
                    else{
                        if(combo_invest.getValue().equals("Poupanca"))
                        {
                            Conta alterada = new Conta();
                            alterada.setNumeroConta(conta.getNumeroConta());
                            alterada.setSaldo(conta.getSaldo()+Double.parseDouble(txt_valor.getText()));
                            alterada.setPoupanca(conta.getPoupanca()-Double.parseDouble(txt_valor.getText()));
                            alterada.setCdb(conta.getCdb());                        
                            contaDAO.alterar(alterada);
                            JOptionPane.showMessageDialog(null,"Resgate feito com sucesso!");  
                            
                            Extrato extrato = new Extrato();
                            ExtratoDAO extratoDAO = new ExtratoDAO();
                            Date dataAtual = new Date();
                            extrato.setData(dataAtual.toString());
                            extrato.setConta(conta.getNumeroConta());                            
                            extrato.setTipo(acao);
                            extrato.setInvestimento("Poupanca");                            
                            extrato.setValor(Double.parseDouble(txt_valor.getText()));                                                
                            extratoDAO.inserir(extrato);
                        }
                        else{
                            Conta alterada = new Conta();
                            alterada.setNumeroConta(conta.getNumeroConta());
                            alterada.setSaldo(conta.getSaldo()+Double.parseDouble(txt_valor.getText()));
                            alterada.setPoupanca(conta.getPoupanca());
                            alterada.setCdb(conta.getCdb()-Double.parseDouble(txt_valor.getText()));                        
                            contaDAO.alterar(alterada);
                            JOptionPane.showMessageDialog(null,"Resgate feito com sucesso!");
                            
                            Extrato extrato = new Extrato();
                            ExtratoDAO extratoDAO = new ExtratoDAO();
                            Date dataAtual = new Date();
                            extrato.setData(dataAtual.toString());
                            extrato.setConta(conta.getNumeroConta());                            
                            extrato.setTipo(acao);
                            extrato.setInvestimento("CDB");                            
                            extrato.setValor(Double.parseDouble(txt_valor.getText()));                                                
                            extratoDAO.inserir(extrato);
                        }
                        this.cancelar();
                    }
                }
            }                            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        
    }  
    
    @FXML
    public void cancelar()
    {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }
    
}
