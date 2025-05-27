package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.ClienteDAO;
import DAO.ProdutoVendaDAO;
import DAO.ServicoVendaDAO;
import DAO.VendaDAO;
import Model.Produto;
import Model.ProdutoVenda;
import Model.Servico;
import Model.ServicoVenda;
import Model.Venda;
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

public class ControllerRelatorioVenda implements Initializable {

	@FXML
	private Button btPesquisar;

	// ---------------------------- colunas da tabela produto ----------------------
	@FXML
	private TableColumn<Produto, String> columnIndiceProd;

	@FXML
	private TableColumn<Produto, String> columnPrecoUniProd;
	@FXML
	private TableColumn<Produto, String> columnProduto;

	@FXML
	private TableColumn<Produto, String> columnQtdSer;

	@FXML
	private TableColumn<Produto, String> columnTotalProd;

	@FXML
	private TableView<ProdutoVenda> tableVendaProd;

	// ------------------ colunas da tabela sertvico -----------------------
	@FXML
	private TableColumn<Servico, String> columnIndiceServ;

	@FXML
	private TableColumn<Servico, String> columnPrecoUniSer;

	@FXML
	private TableColumn<Servico, String> columnQtdProd;

	@FXML
	private TableColumn<Servico, String> columnServico;

	@FXML
	private TableColumn<Servico, String> columnTotalSer;

	@FXML
	private TableView<ServicoVenda> tableVendaSer;

	// ----------------------  colunas da tabela produto e venda ---------------------
	@FXML
	private TableColumn<Venda, String> columnCpfCliente;

	@FXML
	private TableColumn<Venda, String> columnDataVenda;

	@FXML
	private TableColumn<Venda, String> columnDesconto;

	@FXML
	private TableColumn<Venda, String> columnFormaPag;

	@FXML
	private TableColumn<Venda, String> columnIndice;

	@FXML
	private TableColumn<Venda, String> columnNomeCliente;

	@FXML
	private TableColumn<Venda, String> columnPrecoVenda;

	@FXML
	private TableView<Venda> tableVendas;

	@FXML
	private TextField txtPesquisar;

	@FXML
	void actionPesquisar(ActionEvent event) {
		PesquisarTableVenda();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CarregarTableVenda();
		CarregarTableVendaProduto();
		CarregarTableVendaServico();

		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomeclientes = new ArrayList<String>();
		nomeclientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomeclientes.size()];

		for (int i = 0; i < nomeclientes.size(); i++) {
			cliente[i] = nomeclientes.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, cliente);	
	}

	private ObservableList<Venda> ArrayVendas;
	public void CarregarTableVenda() {
		VendaDAO vendaDAO = new VendaDAO();			
		ArrayVendas = FXCollections.observableArrayList(vendaDAO.readTableVenda());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCpfCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));			
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnFormaPag.setCellValueFactory(new PropertyValueFactory<>("formaPag"));	
		tableVendas.setItems(ArrayVendas);						
	}

	private ObservableList<ProdutoVenda> ArrayVendaProd;
	public void CarregarTableVendaProduto() {
		
		ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
		ArrayVendaProd = FXCollections.observableArrayList(produtoVendaDAO.readTableVendaProduto());

		columnIndiceProd.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnQtdProd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPrecoUniProd.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		tableVendaProd.setItems(ArrayVendaProd);						
	}

	private ObservableList<ServicoVenda> ArrayVendaSer;
	public void CarregarTableVendaServico() {

		ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();
		ArrayVendaSer = FXCollections.observableArrayList(servicoVendaDAO.readTableVendaServico());;

		columnIndiceServ.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));
		columnQtdSer.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPrecoUniSer.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		tableVendaSer.setItems(ArrayVendaSer);						
	}
	
	public void PesquisarTableVenda() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		venda.setNome(txtPesquisar.getText());

		ArrayVendas = FXCollections.observableArrayList(vendaDAO.searchCliente(venda));
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCpfCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));			
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnFormaPag.setCellValueFactory(new PropertyValueFactory<>("formaPag"));	
		tableVendas.setItems(ArrayVendas);							
	}





}
