package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.FuncionarioDAO;
import Model.Funcionario;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerFuncionario implements Initializable {

	@FXML
	private Button btAlterar;

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
	private Button btPesquisar;

	@FXML
	private Button btProduto;

	@FXML
	private Button btSair;

	@FXML
	private TableColumn<Funcionario, String> columnCPF;

	@FXML
	private TableColumn<Funcionario, String> columnDataNasc;

	@FXML
	private TableColumn<Funcionario, String> columnEmail;

	@FXML
	private TableColumn<Funcionario, String> columnEndereco;

	@FXML
	private TableColumn<Funcionario, String> columnIndice;

	@FXML
	private TableColumn<Funcionario, String> columnNomeFunc;

	@FXML
	private TableColumn<Funcionario, String> columnTelefone;

	@FXML
	private TableView<Funcionario> tableFuncionarios;

	@FXML
	private TextField txtPesquisar;

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

	public static Funcionario alterarFuncionario = new Funcionario();

	@FXML
	void actionAlterar(ActionEvent event) throws IOException {
		int i = tableFuncionarios.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um funcionário para editar", AlertType.ERROR);   		
		}else {
			alterarFuncionario = tableFuncionarios.getItems().get(i);
			Main.TelaCadastrarFuncionario();
			alterarFuncionario = null;
		}
		CarregarTableFuncionario();	
	}

	@FXML
	void actionCadastrar(ActionEvent event) throws IOException {
		alterarFuncionario = null;
		Main.TelaCadastrarFuncionario();
		CarregarTableFuncionario();
	}

	@FXML
	void actionPesquisar(ActionEvent event) {
		PesquisarTableFuncionario();
	}

	private ObservableList<Funcionario> ArrayFuncionarios;

	public void CarregarTableFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ArrayFuncionarios = FXCollections.observableArrayList(funcionarioDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));	
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));			
		tableFuncionarios.setItems(ArrayFuncionarios);						
	}
	public void PesquisarTableFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(txtPesquisar.getText());
		funcionario.setCpf(txtPesquisar.getText());

		ArrayFuncionarios = FXCollections.observableArrayList(funcionarioDAO.search(funcionario));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));	
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));			
		tableFuncionarios.setItems(ArrayFuncionarios);					
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CarregarTableFuncionario();

		// -------------------- barra de pesquisar funcionario --------------------				
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ArrayList<String> nomeFuncionarios = new ArrayList<String>();
		nomeFuncionarios = funcionarioDAO.readFuncionarioByNome();
		String[] funcionario = new String[nomeFuncionarios.size()];

		for (int i = 0; i < nomeFuncionarios.size(); i++) {
			funcionario[i] = nomeFuncionarios.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, funcionario);	

	}



}
