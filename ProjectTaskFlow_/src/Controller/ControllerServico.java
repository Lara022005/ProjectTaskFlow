package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.ServicoDAO;
//import DAO.ServicoDAO;
import Model.Servico;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerServico implements Initializable {

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
    private TableColumn<Servico, String> columnData;

    @FXML
    private TableColumn<Servico, String> columnDescricao;

    @FXML
    private TableColumn<Servico, String> columnHorario;

    @FXML
    private TableColumn<Servico, String> columnIndice;

    @FXML
    private TableColumn<Servico, String> columnNomeCliente;

    @FXML
    private TableColumn<Servico, String> columnServico;

    @FXML
    private TableView<Servico> tableAgendamentos;

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

    @FXML
    void actionAlterar(ActionEvent event) {

    }

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	Main.TelaCadastrarServico();
    }

    @FXML
    void actionExcluir(ActionEvent event) {

    }

    @FXML
    void actionPesquisar(ActionEvent event) {

    }
    
	private ObservableList<Servico> ArrayServico;

    
    
    public void CarregarTableCliente() {
		
		
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayServico = FXCollections.observableArrayList(servicoDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		


	}

    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
	}

}
