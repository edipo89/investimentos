package investimentos;

import investimentos.InvestimentosController;
import investimentos.model.Pessoa;
import investimentos.persist.PessoaDAO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AlterarSenhaController implements Initializable {

    private Pessoa p;
    private String senhaAntiga;
    
    @FXML
    private PasswordField txt_senhaAntiga;
    @FXML
    private PasswordField txt_senhaNova;
    @FXML
    private PasswordField txt_confirmacao;
    @FXML
    private Button btn_OK;
    @FXML
    private Button btn_cancelar;
    @FXML
    private CheckBox chbox_mostrarSenhas;
    @FXML
    private TextField txt_antigaVisivel;
    @FXML
    private TextField txt_novaVisivel;
    @FXML
    private TextField txt_confirmacaoVisivel;
    @FXML
    private Text txt_pessoa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void alterarSenha(Pessoa p)
    {
        this.p = p;
        txt_pessoa.setText("Nome: " + p.getNome() + "\nCPF:      " + p.getCpf());
        senhaAntiga = p.getSenha();        
    }

    @FXML
    private void Ok(ActionEvent event) {
         if(!txt_senhaAntiga.getText().equals(senhaAntiga))
        {
            JOptionPane.showMessageDialog(null,"A senha digitada não confere!");
        }
        else if(txt_senhaNova.getText().equals(txt_confirmacao.getText()))
        {
            p.setSenha(txt_senhaNova.getText());
            PessoaDAO pessoaDAO = null;
            try {
                pessoaDAO = new PessoaDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pessoaDAO.alterar(p);
            JOptionPane.showMessageDialog(null,"Senha alterada com sucesso!");
             cancelar(event);
        }
        else{
            JOptionPane.showMessageDialog(null,"As senhas digitadas não são iguais");
        }
    }
       
    @FXML
    private void cancelar(ActionEvent event) {
         Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }

    @FXML
    private void mostrarSenha(ActionEvent event) {
        if(chbox_mostrarSenhas.isSelected())
        {
            txt_senhaAntiga.setVisible(false);
            txt_senhaNova.setVisible(false);
            txt_confirmacao.setVisible(false);
            txt_antigaVisivel.setText(txt_senhaAntiga.getText());
            txt_novaVisivel.setText(txt_senhaNova.getText());
            txt_confirmacaoVisivel.setText(txt_confirmacao.getText());
            txt_antigaVisivel.setVisible(true);
            txt_novaVisivel.setVisible(true);
            txt_confirmacaoVisivel.setVisible(true);
        }
        else
        {
            txt_senhaAntiga.setVisible(true);
            txt_senhaNova.setVisible(true);
            txt_confirmacao.setVisible(true);
            txt_antigaVisivel.setVisible(false);
            txt_novaVisivel.setVisible(false);
            txt_confirmacaoVisivel.setVisible(false);
        }
    }
}