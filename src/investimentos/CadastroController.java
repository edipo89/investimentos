package investimentos;

import investimentos.InvestimentosController;
import investimentos.model.Pessoa;
import investimentos.persist.ContaDAO;
import investimentos.persist.PessoaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.JSType.toInt32;

public class CadastroController implements Initializable {

    @FXML
    private AnchorPane pane_cadastro;
    @FXML
    private RadioButton rbtn_masculino;
    @FXML
    private RadioButton rbtn_feminino;
    @FXML
    private ComboBox<String> combo_Uf;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_dataNascimento;
    @FXML
    private TextField txt_telefone;
    @FXML
    private TextField txt_numero;
    @FXML
    private TextField txt_cep;
    @FXML
    private TextField txt_pais;
    @FXML
    private PasswordField txt_senha;
    @FXML
    private PasswordField txt_confirmacaoSenha;
    @FXML
    private Button btn_OK;
    @FXML    
    private Button btn_cancelar;
    @FXML
    private TextField txt_logradouro;
    @FXML
    private Text numeroConta;
    @FXML
    private TextField txt_disciplina;
    
    private ArrayList<Pessoa> listaPessoas;
    private Pessoa p;
    
    @FXML
    private Button btn_salvarAlteracoes;
    @FXML
    private Text text_senha;
    @FXML
    private Text text_confirmacaoSenha;
    @FXML
    private Button btn_alterarSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        ObservableList<String> options = 
                FXCollections.observableArrayList(
                    "SP","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SE","TO"
                ); 
        combo_Uf.setItems(options);
        
        btn_salvarAlteracoes.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                this.salvarAlteracoes();
            }
        });
        
        numeroConta.setVisible(false);
    }
    
    
    @FXML
    public void rbtn_masculinoSelected()
    {
        if(rbtn_masculino.isSelected())
        {
            rbtn_feminino.setSelected(false);
        }
            
    }
    
    @FXML
    public void rbtn_femininoSelected()
    {
        if(rbtn_feminino.isSelected())
        {
            rbtn_masculino.setSelected(false);
        }
    }

    
    @FXML
    public void cancelar()
    {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }
    
    private boolean usuarioExiste(String cpf)
    {
        for(Pessoa p : listaPessoas)
        {
            if(p.getCpf().equals(cpf))
            {
                return true;
            }
        }
    return false;
    }
    
    public void cadastrarUsuario(ArrayList<Pessoa> listaPessoas)
    {
        this.listaPessoas = listaPessoas;     
    } 
    
    
    @FXML
    private void salvarAlteracoes()
    {
         txt_confirmacaoSenha.setVisible(false);
         String sexo = null; 
         if(rbtn_masculino.isSelected())
         {
            sexo = "m";
         }
         if(rbtn_feminino.isSelected())
         {
            sexo = "f";
         }
         String tel = txt_telefone.getText(); 
         String logradouro = txt_logradouro.getText();
         int num = toInt32(txt_numero.getText()); 
         String cep = txt_cep.getText();
         String uf = combo_Uf.getValue(); 
         String cidade = txt_pais.getText(); 
         String disc = txt_disciplina.getText();
         if(!(!rbtn_masculino.isSelected() && !rbtn_feminino.isSelected() ||
            txt_telefone.getText().isEmpty() ||
            txt_logradouro.getText().isEmpty() ||
            txt_numero.getText().isEmpty() ||
            txt_cep.getText().isEmpty() 
            //txt_uf.getT
                 
           ))
         {
             if(txt_senha.getText().isEmpty())
             {
                 JOptionPane.showMessageDialog(null,"É necessário digitar a sua senha!");
             }
             else
             {
                if(txt_senha.getText().equals(p.getSenha()))
                {
                    p.setSexo(sexo);
                    p.setTel(tel);
                    p.setLogradouro(logradouro);
                    p.setNum(num);
                    p.setCep(cep);
                    p.setUf(uf);
                    p.setCidade(cidade);
                    
                    PessoaDAO pessoaDAO = null;
                    try {
                        pessoaDAO = new PessoaDAO();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pessoaDAO.alterar(p);            
                    JOptionPane.showMessageDialog(null,"Cadastro alterado com sucesso!");
                   cancelar();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Senha incorreta, tente novamente!");
                }
             }
         }        
        else
        {
            JOptionPane.showMessageDialog(null,"É necessário preencher todos os dados!");
        }
    }
       
    @FXML
    private void alterarSenha() throws IOException
    {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("alterarSenha.fxml"));
                Pane root = loader.load();
                AlterarSenhaController controller = (AlterarSenhaController) loader.getController();
                controller.alterarSenha(p);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.resizableProperty().setValue(false);
                stage.show();
    }
    
    public void alterarPessoa(Pessoa p,ArrayList<Pessoa> listaPessoas)
    {
        this.p = p;
        text_senha.setVisible(true);
        text_confirmacaoSenha.setVisible(false);
        btn_OK.setVisible(false);
        btn_salvarAlteracoes.setVisible(true);
        btn_alterarSenha.setVisible(true);
        txt_senha.setVisible(true);
        txt_confirmacaoSenha.setVisible(false);
        if(p.getSexo().equals("m"))
        {
            rbtn_masculino.setSelected(true);
        }
        else
        {
            rbtn_feminino.setSelected(true);
        }
        txt_nome.setDisable(true);
        combo_Uf.setValue(p.getUf());
        txt_nome.setText(p.getNome());
        txt_cpf.setText(p.getCpf());
        txt_cpf.setDisable(true);
        txt_dataNascimento.setText(p.getNasc());
        txt_dataNascimento.setDisable(true);
        txt_telefone.setText(p.getTel());
        txt_numero.setText(String.valueOf(p.getNum()));
        txt_cep.setText(p.getCep());
        txt_pais.setText(p.getCidade());
        txt_logradouro.setText(p.getLogradouro());        
    }          
    
    private boolean pessoaVazio()
    {
        return (!rbtn_masculino.isSelected() && !rbtn_feminino.isSelected()) ||            
                //!combo_Uf.isPressed() ||
                txt_nome.getText().isEmpty() ||
                txt_cpf.getText().isEmpty() ||
                txt_dataNascimento.getText().isEmpty() ||
                txt_telefone.getText().isEmpty() ||
                txt_numero.getText().isEmpty() ||
                txt_cep.getText().isEmpty() ||
                txt_pais.getText().isEmpty() ||
                txt_senha.getText().isEmpty() ||
                txt_confirmacaoSenha.getText().isEmpty() ||
                txt_logradouro.getText().isEmpty();       
    }
    
    
    @FXML
    private void CadastrarNovoUsuario(ActionEvent event) throws ParseException 
    {
                String nome = txt_nome.getText();
                String cpf = txt_cpf.getText();
                String sexo = null; 
                if(rbtn_masculino.isSelected())
                {
                    sexo = "m";
                }
                if(rbtn_feminino.isSelected())
                {
                    sexo = "f";
                }
                String nasc = txt_dataNascimento.getText();
                String tel = txt_telefone.getText(); 
                String logradouro = txt_logradouro.getText();
                int num = toInt32(txt_numero.getText()); 
                String cep = txt_cep.getText();
                String uf = combo_Uf.getValue(); 
                String pais = txt_pais.getText(); 
                String disc = txt_disciplina.getText();
                String senha = txt_senha.getText();               
                int numeroConta = Integer.parseInt(txt_cpf.getText());
                numeroConta+=1000;
                               
                if(!usuarioExiste(cpf))
                {
                    if(!pessoaVazio())
                    {
                        if(txt_senha.getText().equals(txt_confirmacaoSenha.getText()))
                        {
                            Pessoa novaPessoa = new Pessoa(nome,cpf,sexo,nasc,tel,logradouro,num,cep,uf,pais,senha,numeroConta);
                            listaPessoas.add(novaPessoa);
                            PessoaDAO pessoaDAO = null;
                            try {
                                pessoaDAO = new PessoaDAO();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            pessoaDAO.inserir(novaPessoa);                           
                            Conta conta = new Conta();
                            conta.setNumeroConta(numeroConta);
                            conta.setSaldo(0.0);
                            conta.setPoupanca(0.0);
                            conta.setCdb(0.0);
                            ContaDAO contaDAO = null;
                            try {
                                contaDAO = new ContaDAO();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            contaDAO.inserir(conta);                            
                            JOptionPane.showMessageDialog(null,"Usuário adicionado com sucesso! Conta: " + numeroConta);                                                                                   
                            cancelar();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"As senhas digitadas não são iguais!");
                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"É necessário preencher todos os dados!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Usuário já existe no sistema!");
                }

            }                                
        }                
    
