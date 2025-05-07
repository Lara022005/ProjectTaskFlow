package Controller;


import java.io.IOException;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import DAO.ClienteDAO;
import Model.Cliente;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerCliente implements Initializable{

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
	private TableColumn<Cliente, String> columnCPF;

	@FXML
	private TableColumn<Cliente, String> columnEmail;

	@FXML
	private TableColumn<Cliente, String> columnEndereco;

	@FXML
	private TableColumn<Cliente, String> columnIndice;

	@FXML
	private TableColumn<Cliente, String> columnNomeCliente;

	@FXML
	private TableColumn<Cliente, String> columnTelefone;

	@FXML
	private TableView<Cliente> tableCliente;

	@FXML
	private TextField txtPesquisar;

	  void actionPesquisar(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void telaCliente(ActionEvent event) throws IOException {
			Main.TelaCliente();
			CarregarTableCliente();
	    }

	    @FXML
	    void telaFuncionario(ActionEvent event) throws IOException {
	    	Main.TelaFuncionario();
	    }

	    @FXML
	    void telaMain(ActionEvent event) throws IOException {
	    	Main.TelaHome();
	    }

	    @FXML
	    void telaProduto(ActionEvent event) throws IOException {
	    	Main.TelaProduto();
	    }


	private ObservableList<Cliente> ArrayCliente;

	public void CarregarTableCliente() {
		
		
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayCliente = FXCollections.observableArrayList(clienteDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableCliente.setItems(ArrayCliente);		

	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		CarregarTableCliente();
		
	}



}

