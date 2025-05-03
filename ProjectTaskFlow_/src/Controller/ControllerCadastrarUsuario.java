package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import Util.NivelValidator;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ControllerCadastrarUsuario {

    @FXML
    private AnchorPane TelaUsuario;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCliente;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btMain;

    @FXML
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TextField txtNivel;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    void ActionCliente(ActionEvent event) {

    }

    @FXML
    void ActionFuncionario(ActionEvent event) {

    }

    @FXML
    void ActionMain(ActionEvent event) {

    }

    @FXML
    void ActionProduto(ActionEvent event) {

    }

    @FXML
    void ActionSair(ActionEvent event) {

    }

    @FXML
    void actionCadastrar(ActionEvent event) {
      
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        String nome = txtNome.getText();
        String senha = txtSenha.getText();
        String nivel = txtNivel.getText();
        
        if (nome.isEmpty() || senha.isEmpty() || nivel.isEmpty()) {
            
            System.out.println("Erro! Campos obrigatórios! Preencha todos os campos e tente novamente.");
        } 
        
        else if (!nivel.matches("[12]")) { 
            
            System.out.println("Erro! Nível inválido! O nível deve ser 1 ou 2.");
        }
        
        else if (!NivelValidator.podeCriarUsuario(nivel)) {
            
            System.out.println("Erro! Permissão Negada! Somente usuários de nível 1 podem criar novos usuários.");
        }
       
        else {
            
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setNivelUsuario(nivel); 

            
            usuarioDAO.create(usuario);
            
            
            System.out.println("Sucesso! Usuário cadastrado! O usuário foi cadastrado com sucesso.");
        }
    }






    @FXML
    void actionExcluir(ActionEvent event) {

    }

}
