package Controller;


import java.io.IOException;

import DAO.FuncionarioDAO;
import DAO.UsuarioDAO;
import Model.Funcionario;
import Model.Usuario;
import Util.Alerts;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class ControllerLogin {

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btEntrar;

	@FXML
	private TextField txtSenha;

	@FXML
	private TextField txtNome;

	public static Funcionario funcionario = new Funcionario();
	public static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	public static Usuario usuario = new Usuario();	
	
	@FXML
	void actionEntrar(ActionEvent event) throws IOException{

		UsuarioDAO usuarioDAO = new UsuarioDAO();   

		usuario = usuarioDAO.autenticarUser(txtNome.getText(), txtSenha.getText());

		if(usuario.getNome() == null) {
			Alerts.showAlert("Erro!", "Erro de login", "Verifique se as informações estão corretas e tente novamente!", AlertType.ERROR);
		}
		else if(txtNome.getText() == "" && txtSenha.getText() == "") {
			Alerts.showAlert("Erro!", "Erro de login", "Preencha as informações de login e senha para acessar!", AlertType.ERROR);   		
		}
		else if(usuario.getNome().equals(txtNome.getText()) && usuario.getSenha().equals(txtSenha.getText())) {    		
			funcionario = funcionarioDAO.searchName(usuario);
			Alerts.showAlert("Login bem sucedido!", "Seja bem vindo, " + funcionario.getNome(),"Se sinta a vontade para navegar no nosso sistema!", AlertType.INFORMATION); 
			txtNome.setText("");
			txtSenha.setText("");			
			Main.TelaHome();    			
		}	
	}   
}
