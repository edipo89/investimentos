package investimentos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import investimentos.CadastroController;
import investimentos.JanelaLoginController;
import investimentos.model.Pessoa;
import investimentos.persist.PessoaDAO;

public class InvestimentosController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private Button btn_novoUsuario;
    @FXML
    private Button btn_sair;

    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private String senha;
    
    private final String IMG_URL = "images.png";
      
    @FXML
    private AnchorPane pane_login;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PessoaDAO pessoaDAO = null;
        try {
            pessoaDAO = new PessoaDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
        
        try {
            listaPessoas = pessoaDAO.getPessoa();
        } catch (SQLException ex) {
            Logger.getLogger(InvestimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        
        btn_login.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                try {
                    this.login();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn_novoUsuario.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                try {
                    this.novoUsuario();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn_sair.setOnKeyPressed(k -> 
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {
                this.sair();
            }
        });
        
        pane_login.setOnKeyPressed(k -> 
        {
            final KeyCombination ESCAPE = new KeyCodeCombination(KeyCode.ESCAPE);
            if (ESCAPE.match(k)) {
                this.sair();
            }
        });
    }
        
    @FXML
    public void login() throws IOException
    {
        boolean existe = false;
        if (listaPessoas.isEmpty()) 
        {
            System.out.println("Ainda não há usuários cadastrados");
        } 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JanelaLogin.fxml"));
        Pane root = loader.load();
        JanelaLoginController controller = (JanelaLoginController) loader.getController();
        controller.JanelaLoginController(listaPessoas);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
    
    @FXML
    public void novoUsuario() throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
        Pane root = loader.load();
        CadastroController controller = (CadastroController) loader.getController();
        controller.cadastrarUsuario(listaPessoas);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cadastrar novo usuário");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
    
    @FXML
    public void sair()
    {
        if (JOptionPane.showConfirmDialog(
                null, 
                "Você deseja realmente sair? ","Fechar",
                        JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION) 
        {
            Platform.exit();
        }
    }
}
        