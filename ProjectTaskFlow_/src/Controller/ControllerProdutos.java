package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import DAO.ProdutoDAO;
import Model.Produto;
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

public class ControllerProdutos implements Initializable {

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
    private TableColumn<Produto, String> columnCodBarra;

    @FXML
    private TableColumn<Produto, String> columnDataFab;

    @FXML
    private TableColumn<Produto, String> columnDataVal;

    @FXML
    private TableColumn<Produto, String> columnEstoque;

    @FXML
    private TableColumn<Produto, String> columnIndice;

    @FXML
    private TableColumn<Produto, String> columnPreco;

    @FXML
    private TableColumn<Produto, String> columnProduto;

    @FXML
    private TableView<Produto> tableProdutos;

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
    void actionPesquisar(ActionEvent event) {
    	
    }

    @FXML
    void actionAlterar(ActionEvent event) {

    }

    @FXML
    void actionCadastrar(ActionEvent event) {

    }

    @FXML
    void actionExcluir(ActionEvent event) {

    }
    
    private ObservableList<Produto> ArrayProdutos;
    
    public void CarregarTableProduto() {
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	ArrayProdutos = FXCollections.observableArrayList(produtoDAO.read());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnDataFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
		columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));	
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));			
		tableProdutos.setItems(ArrayProdutos);						
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CarregarTableProduto();
		
	}


}
