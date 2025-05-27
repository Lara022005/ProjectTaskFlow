package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.ProdutoDAO;
import DAO.ProdutoVendaDAO;
import Model.Produto;
import Model.ProdutoVenda;
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

public class ControllerProduto implements Initializable {

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
		PesquisarTableProduto();
	}

	public static Produto alterarProduto = new Produto();
	
	@FXML
	void actionAlterar(ActionEvent event) throws IOException {	
		int i = tableProdutos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um produto para editar", AlertType.ERROR);   		
		}else {
			alterarProduto = tableProdutos.getItems().get(i);
			Main.TelaCadastrarProduto();
			alterarProduto = null;
		}
		CarregarTableProduto();	
				
	}
	@FXML
	void actionCadastrar(ActionEvent event) throws IOException {
		alterarProduto = null;
		Main.TelaCadastrarProduto();
		CarregarTableProduto();	
	}

	@FXML
	void actionExcluir(ActionEvent event) {
		
		int i = tableProdutos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao concluir", "Selecione um produto", AlertType.ERROR);   		
		}else {
			Produto produto = new Produto();
			produto = tableProdutos.getItems().get(i);

			Alert confirmation = new Alert (AlertType.CONFIRMATION);
			confirmation.setContentText("Deseja realmente excluir esse produto? \n   " + produto.getNome());

			Optional<ButtonType> resultado = confirmation.showAndWait();

			if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
				ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
				ProdutoVenda produtoVenda = new ProdutoVenda();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoVendaDAO.delete(produtoVenda);
				produtoDAO.delete(produto);

				Alerts.showAlert("Sucesso!", "Produto excluído", "O pruduto "+ produto.getNome()+", foi excluido com sucesso", AlertType.INFORMATION);
				CarregarTableProduto();
			}
		}
	}
	private ObservableList<Produto> ArrayProdutos;

	public void CarregarTableProduto() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayProdutos = FXCollections.observableArrayList(produtoDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnDataFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
		columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));	
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));			
		tableProdutos.setItems(ArrayProdutos);						
	}

	public void PesquisarTableProduto() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto.setNome(txtPesquisar.getText());
		produto.setCodBarra(txtPesquisar.getText());

		ArrayProdutos = FXCollections.observableArrayList(produtoDAO.search(produto));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
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

		// -------------------- barra de pesquisar produto --------------------				
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<String> nomeProdutos = new ArrayList<String>();
		nomeProdutos = produtoDAO.readProdutoByNome();
		String[] produto = new String[nomeProdutos.size()];

		for (int i = 0; i < nomeProdutos.size(); i++) {
			produto[i] = nomeProdutos.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, produto);	

	}


}
