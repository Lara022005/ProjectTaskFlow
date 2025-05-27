package Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.ClienteDAO;
import Model.Cliente;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

	  @FXML	
	  void actionPesquisar(ActionEvent event) {
		  PesquisarTableCliente();
	    	
	    }
	    
	    @FXML
	    void ActionCliente(ActionEvent event) throws IOException {
			Main.TelaCliente();
			CarregarTableCliente();
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
	    void actionCadastrar(ActionEvent event) throws IOException {
	    	Main.TelaCadastrarCliente();

	    }
	    
	    
	    @FXML
	    void actionExcluir(ActionEvent event) {

			int i = tableCliente.getSelectionModel().getSelectedIndex(); // valor clicado na tela
			if(i == -1) {
				Alerts.showAlert("ERRO!", "Falha ao concluir", "Selecione um cliente", AlertType.ERROR);   		
			}else {
				Cliente cliente = new Cliente();
				cliente = tableCliente.getItems().get(i);

				Alert confirmation = new Alert (AlertType.CONFIRMATION);
				confirmation.setContentText("Deseja realmente excluir esse cliente? \n   " + cliente.getNome());

				Optional<ButtonType> resultado = confirmation.showAndWait();

				if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
					
					ClienteDAO clienteDAO = new ClienteDAO();
					clienteDAO.delete(cliente);

					Alerts.showAlert("Sucesso!", "Cliente excluído", "O cliente "+ cliente.getNome()+", foi excluido com sucesso", AlertType.INFORMATION);
					CarregarTableCliente();
				}
			}

	    }
	    
		public static Cliente alterarCliente = new Cliente ();
	    
	    @FXML
	    void actionAlterar(ActionEvent event) throws IOException {	    	
	    	int i = tableCliente.getSelectionModel().getSelectedIndex(); // valor clicado na tela
			if(i == -1) {
				Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um produto para editar", AlertType.ERROR);   		
			}else {
				alterarCliente = tableCliente.getItems().get(i);
				Main.TelaCadastrarCliente();
				alterarCliente = null;
			}
		
			CarregarTableCliente();
	    	
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
	
	
	public void PesquisarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente.setNome(txtPesquisar.getText());
		cliente.setCpf(txtPesquisar.getText());

		ArrayCliente = FXCollections.observableArrayList(clienteDAO.search(cliente));

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
		
		alterarCliente = null;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomeclientes = new ArrayList<String>();
		nomeclientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomeclientes.size()];

		for (int i = 0; i < nomeclientes.size(); i++) {
			cliente[i] = nomeclientes.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, cliente);	
	}



}

