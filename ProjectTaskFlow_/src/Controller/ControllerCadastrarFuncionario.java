package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DAO.FuncionarioDAO;
import Model.Funcionario;
import Util.Alerts;
import Util.cpfValidador;
import Util.emailValidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerCadastrarFuncionario implements Initializable{

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCancelar;

	@FXML
	private ChoiceBox<String> choiceGenero;

	@FXML
	private DatePicker dpDataAdm;

	@FXML
	private DatePicker dpDataNasc;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtNomeFunc;

	@FXML
	private TextField txtTelefone;

	@FXML
	void actionCadastrar(ActionEvent event) {
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		if(txtNomeFunc.getText() == "" || dpDataAdm.getValue() == null ||dpDataNasc.getValue() == null || txtTelefone.getText() == "" 
				|| txtEndereco.getText() == "" || choiceGenero.getValue() == null) {   		
			Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);
		}
		else if(txtCpf.getText() == "" || cpfValidador.validarCPF(txtCpf.getText()) == false){
			Alerts.showAlert("Erro!", "CPF inválido"," Verifique o CPF e tente novamente", AlertType.ERROR);

		}else if(txtEmail.getText() == "" || emailValidador.validarEmail(txtEmail.getText()) == false){
			Alerts.showAlert("Erro!", "Email inválido"," Verifique o email e tente novamente", AlertType.ERROR);
		}else { 
			funcionario.setNome(txtNomeFunc.getText());
			funcionario.setCpf(txtCpf.getText());
			funcionario.setEmail(txtEmail.getText());
			funcionario.setEndereco(txtEndereco.getText());   
			funcionario.setTelefone(txtTelefone.getText());   	
			funcionario.setGenero(choiceGenero.getValue().toString());
			funcionario.setDataAdmissao(dpDataAdm.getValue().toString());
			funcionario.setDataNasc(dpDataNasc.getValue().toString());

			if(ControllerFuncionario.alterarFuncionario == null) {
				funcionarioDAO.create(funcionario);   		
				Alerts.showAlert("Sucesso!", "Funcionário cadastrado", "Seja bem vindo", AlertType.INFORMATION);
				Stage stage = (Stage) btCancelar.getScene().getWindow();
				stage.close();
			}else if(ControllerFuncionario.alterarFuncionario != null) {
				funcionarioDAO.update(funcionario);     		
				Alerts.showAlert("Sucesso!", "Funcionário editado", "O funcionário foi editado com sucesso", AlertType.INFORMATION);    
				Stage stage = (Stage) btCancelar.getScene().getWindow();
				stage.close();
			}   	   		  		
		} 	    	
	}

	@FXML
	void actionCancelar(ActionEvent event) {

		txtNomeFunc.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
		dpDataNasc.setValue(null);
		dpDataAdm.setValue(null);
		choiceGenero.setValue(null);

		ControllerFuncionario.alterarFuncionario = null;
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();  
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceGenero.getItems().add("Feminino");
		choiceGenero.getItems().add("Masculino");

		if(ControllerFuncionario.alterarFuncionario != null) {

			btCadastrar.setText("Salvar");
			Funcionario funcionarioEditar = new Funcionario();
			funcionarioEditar = ControllerFuncionario.alterarFuncionario;
			txtNomeFunc.setText(funcionarioEditar.getNome());
			txtCpf.setText(funcionarioEditar.getCpf());
			txtEmail.setText(funcionarioEditar.getEmail());
			txtEndereco.setText(funcionarioEditar.getEndereco());
			txtTelefone.setText(funcionarioEditar.getTelefone());
			LocalDate dataNasc = LocalDate.parse(funcionarioEditar.getDataNasc());
			dpDataNasc.setValue(dataNasc);
			LocalDate dataAdm = LocalDate.parse(funcionarioEditar.getDataAdmissao());
			dpDataAdm.setValue(dataAdm);
		}

	}

}
