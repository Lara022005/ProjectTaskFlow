package Controller;

import java.io.IOException;
import java.util.ArrayList;
import DAO.FuncionarioDAO;
import DAO.UsuarioDAO;
import Model.Funcionario;
import Model.Usuario;
import Util.Alerts;
import Util.cpfValidador;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class ControllerCadastrarUsuario {

    @FXML
    private AnchorPane TelaUsuario;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCliente;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btMain;

    @FXML
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TextField txtCpf;

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
    void ActionSair(ActionEvent event) throws IOException {
    	Main.TelaHome();
    }

    @FXML
    void actionCadastrar(ActionEvent event) {
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();

		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuario.setSenha(txtSenha.getText());
		usuario.setNome(txtNome.getText());
		usuario.setNivelUsuario(txtNivel.getText());
		usuarios = usuarioDAO.search(usuario);
		usuario = usuarios.get(0);

		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		funcionario.setCpf(txtCpf.getText());
		funcionarios = funcionarioDAO.search(funcionario);
		funcionario = funcionarios.get(0);

		
		if(txtNome.getText() == "" || txtSenha.getText() == "" || txtNivel.getText() == "") {   		
			
			Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);    		
		}else if(txtCpf.getText() == "" || cpfValidador.validarCPF(txtCpf.getText()) == false) {   		
			Alerts.showAlert("Erro!", "CPF inválido"," Verifique o CPF e tente novamente", AlertType.ERROR);
		}
		else {
			usuario.setId(usuario.getId());
			usuario.setNome(txtNome.getText());
			usuario.setSenha(txtSenha.getText());
			usuario.setNivelUsuario(txtNivel.getText());
			
		}
    }

    @FXML
    void actionCancelar(ActionEvent event) {

    }

}
