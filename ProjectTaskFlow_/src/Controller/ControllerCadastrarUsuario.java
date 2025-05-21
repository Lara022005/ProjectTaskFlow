package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DAO.FuncionarioDAO;
import DAO.UsuarioDAO;
import Model.Funcionario;
import Model.Usuario;
import Util.Alerts;
import Util.cpfValidador;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCadastrarUsuario implements Initializable {

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
	void ActionCliente(ActionEvent event) throws IOException {
		Main.TelaCliente();
	}

	@FXML
	void ActionFuncionario(ActionEvent event) throws IOException {
		Main.TelaFuncionario();
	}

	@FXML
	void ActionMain(ActionEvent event) throws IOException {
		Main.TelaHome();
	}

	@FXML
	void ActionProduto(ActionEvent event) throws IOException {
		Main.TelaProduto();
	}

	@FXML
	void ActionSair(ActionEvent event) throws IOException {
		Main.TelaHome();
	}

	@FXML
	void actionCadastrar(ActionEvent event) {


		if(txtNome.getText() == "" || txtSenha.getText() == "" || txtNivel.getText() == "" || txtCpf.getText() == "") {   		

			Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);   

		}else if(txtCpf.getText() == "" || cpfValidador.validarCPF(txtCpf.getText()) == false) {   	

			Alerts.showAlert("Erro!", "CPF inválido"," Verifique o CPF e tente novamente", AlertType.ERROR);
		}
		else {



			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = new Funcionario();

			usuario.setSenha(txtSenha.getText());
			usuario.setNome(txtNome.getText());
			usuario.setNivelUsuario(txtNivel.getText());


			ArrayList<Funcionario> funcionarios = new ArrayList<>();
			funcionario.setCpf(txtCpf.getText());
			funcionarios = funcionarioDAO.search(funcionario);
			funcionario = funcionarios.get(0);
			usuario.setIdFuncionario(funcionario.getId());


			usuarioDAO.create(usuario);


			Alerts.showAlert("Sucesso!", "Usuario Cadastrado", "Usuario Cadastrado com sucesso", AlertType.INFORMATION);
			//   		Stage stage = (Stage) btCancelar.getScene().getWindow();
			//       	stage.close();


		}
	}

	@FXML
	void actionCancelar(ActionEvent event) {
		txtCpf.setText("");
		txtNome.setText("");
		txtSenha.setText("");
		txtNivel.setText("");
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub



	}

}
