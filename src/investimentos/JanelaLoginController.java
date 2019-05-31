package investimentos;

import investimentos.model.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class JanelaLoginController implements Initializable {

    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_senha;
    @FXML
    private Button btn_OK;
    @FXML
    private Button btn_cancelar;

    private String usuario;
    private String senha;
    
    private boolean existe = false;
    private String logou;
    
    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        
    @FXML
    private CheckBox chbox_mostrarSenha;
    @FXML
    private TextField txt_senhaVisivel;
    
    @FXML
    public void mostrarSenha()
    {
        if(chbox_mostrarSenha.isSelected())
        {
            txt_senha.setVisible(false);
            txt_senhaVisivel.setText(txt_senha.getText());
            txt_senhaVisivel.setVisible(true);
        }
        else
        {
            txt_senha.setVisible(true);
            txt_senhaVisivel.setVisible(false);
        }
    }
    
    public void JanelaLoginController(ArrayList<Pessoa> listaPessoas)
    {
            this.listaPessoas = listaPessoas;
            usuario = txt_usuario.getText();          
    }
    
    public void login(Pessoa p) throws IOException
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
            Pane root = loader.load();
            RootLayoutController controller = (RootLayoutController) loader.getController();                                               
            controller.rootLayoutController(p,listaPessoas);
            Scene scene = new Scene(root);                                    
            Stage stage = new Stage();                        
            stage.setScene(scene);            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.resizableProperty().setValue(false);
            stage.show();
    }

     
    @FXML
    public String OK() throws IOException
    {
            if(txt_usuario.getText().isEmpty() || txt_senha.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"É necessário preencher todos os dados!");
                logou = "não";
                return logou;
            }
            else
            {
                usuario = txt_usuario.getText();
                senha = txt_senha.getText();
                    for(Pessoa p : listaPessoas) 
                        {
                            if (p.getCpf().equals(usuario)) 
                            {
                                existe = true;
                                if (p.getSenha().equals(senha)) 
                                {        
                                    this.login(p);                                                                                
                                    Stage stage = (Stage) btn_OK.getScene().getWindow(); //Obtendo a janela atual
                                    stage.close(); //Fechando o Stage         
                                } else 
                                {                                    
                                    logou = "não";
                                    JOptionPane.showMessageDialog(null,"Senha inválida!!!");
                                }
                            } 
                        }
                    }     
                    if(!existe) 
                    {                                                                 
                        JOptionPane.showMessageDialog(null,"Usuário não existe!!!");
                        logou = "não";
                    }                                       
            return logou;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        txt_senha.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                try {
                    this.OK();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        txt_usuario.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                try {
                    this.OK();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn_OK.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                try {
                    this.OK();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn_cancelar.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                this.cancelar();
            }
        });
        
    }  
    
    @FXML
    public void cancelar()
    {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }
}
