package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import DAO.ServicoDAO;
import Model.Cliente;
import Model.Produto;
import Model.Servico;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerRegistrarVenda implements Initializable {

	@FXML
	private Button btAdicionarProduto;

	@FXML
	private Button btAdicionarServico;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btPesquisarCliente;

	@FXML
	private Button btPesquisarProduto;

	@FXML
	private Button btPesquisarServico;

	@FXML
	private Button btRegistrar;

	@FXML
	private Button btVoltar;

	@FXML
	private ChoiceBox<String> choiceFormaPag;

	@FXML
	private TableView<Produto> tableVendaProduto;

	@FXML
	private TableView<Servico> tableVendaServico;

	@FXML
	private TableColumn<Produto, String> columnNomeProd;

	@FXML
	private TableColumn<Servico, String> columnNomeServ;

	@FXML
	private TableColumn<Produto, String> columnPrecoUniP;

	@FXML
	private TableColumn<Servico, String> columnPrecoUniS;

	@FXML
	private TableColumn<Produto, String> columnQtdProd;

	@FXML
	private TableColumn<Servico, String> columnQtdServ;

	@FXML
	private TableColumn<Produto, String> columnTotalProd;

	@FXML
	private TableColumn<Servico, String> columnTotalServ;

	@FXML
	private TableColumn<Produto, String> columnIndiceProd;

	@FXML
	private TableColumn<Servico, String> columnIndiceServ;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtDesconto;

	@FXML
	private TextField txtNomeCliente;

	@FXML
	private TextField txtProduto;

	@FXML
	private TextField txtServico;

	@FXML
	private TextField txtPrecoUniProduto;

	@FXML
	private TextField txtPrecoUniServico;

	@FXML
	private TextField txtQtdProduto;

	@FXML
	private TextField txtQtdServico;

	@FXML
	private TextField txtTotalProduto;

	@FXML
	private TextField txtTotalServico;

	@FXML
	private TextField txtTotalVenda;

	@FXML
	private TextField txtVendedor;

	private static Produto produtoVenda = new Produto(); 
	private ArrayList<Produto> ArrayProdutos = new ArrayList<>();
	double totalVendaProd;
	double desconto;
	String[] nomesProdutos = new String[200];
	String[] quantidade = new String[200];

	@FXML
	void ActionAdicionarProduto(ActionEvent event) {

		if(txtCPF.getText() == "" || txtNomeCliente.getText() == "" || txtTotalProduto.getText() == "" || 
				txtProduto.getText() == "" || txtQtdProduto.getText() == "" ||txtPrecoUniProduto.getText() == "") {
			Alerts.showAlert("Erro!", "Campos inválidos"," Verifique se os campos estãos preenchidos e tente novamente!", AlertType.ERROR);  

		} else {
			produtoVenda.setNome(txtProduto.getText());
			produtoVenda.setEstoque(txtQtdProduto.getText());
			produtoVenda.setPrecoUni(txtPrecoUniProduto.getText());
			produtoVenda.setTotalProduto(txtTotalProduto.getText());
			produtoVenda.setId("" + ArrayProdutos.size());
			String valor = txtTotalProduto.getText();
			valor = valor.replace(",", ".");
			double precoTotal = Double.parseDouble(valor);
			totalVendaProd = totalVendaProd + precoTotal;
			valor = String.format("%.2f", totalVendaProd);
			txtTotalVenda.setText(valor);

			valor = txtDesconto.getText();
			valor = valor.replace(",", ".");
			double valorDesconto = Double.parseDouble(valor);
			desconto = desconto + valorDesconto;

			nomesProdutos[ArrayProdutos.size()] = txtProduto.getText();
			quantidade[ArrayProdutos.size()] = txtQtdProduto.getText();

			ArrayProdutos.add(produtoVenda);
			CarregarTableProdutos(ArrayProdutos);	

			Alerts.showAlert("Sucesso!", "Parabéns","Produto adicionado com sucesso", AlertType.INFORMATION);		
		}
		ArrayProdutos = new ArrayList<Produto>();
		CarregarTableProdutos(ArrayProdutos);
	}

	@FXML
	void ActionAdicionarServico(ActionEvent event) {

	}

	@FXML
	void ActionDesconto(ActionEvent event) {

	}

	@FXML
	void ActionRegistrar(ActionEvent event) {

	}

	@FXML
	void ActionCancelar(ActionEvent event) {

	}
	@FXML
	void ActionVoltar(ActionEvent event) throws IOException {  	

		txtCPF.setText("");
		txtDesconto.setText("");
		txtNomeCliente.setText("");
		txtProduto.setText("");
		txtServico.setText("");
		txtPrecoUniProduto.setText("");
		txtPrecoUniServico.setText("");
		txtQtdProduto.setText("");
		txtQtdServico.setText("");
		txtTotalProduto.setText("");
		txtTotalServico.setText("");
		txtTotalVenda.setText("");
		txtVendedor.setText("");
		txtDesconto.setText("");		
		choiceFormaPag.setValue(null);

		Stage stage = (Stage) btVoltar.getScene().getWindow();
		stage.close();
		//  	Main.TelaHome();
	}
	@FXML
	void actionNomeClick(MouseEvent event) {
		if(txtCPF.getText().length() > 5) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpf(txtCPF.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtNomeCliente.setText(cliente.getNome());						
		}
	}
	@FXML
	void actionNomeType(KeyEvent event) {
		if(txtCPF.getText().length() > 5) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpf(txtCPF.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtNomeCliente.setText(cliente.getNome());						
		}else {
			txtNomeCliente.setText("");
		}
	}	
	@FXML
	void actionProdClick(MouseEvent event) {
		if(txtProduto.getText().length() > 5) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNome(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();		
			produtos = produtoDAO.search(produto);
			produto = produtos.get(0);			

			String precoUn;
			precoUn = produto.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUniProduto.setText("R$ "+precoUn);
		}
	}
	@FXML
	void actionProdtype(KeyEvent event) {
		if(txtProduto.getText().length() > 5) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNome(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();		
			produtos = produtoDAO.search(produto);
			produto = produtos.get(0);			

			String precoUn;
			precoUn = produto.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);	

			txtPrecoUniProduto.setText("R$ "+precoUn);
		}else {			
			txtPrecoUniProduto.setText("");
		}
	}

	@FXML
	void actionSerClick(MouseEvent event) {
		if(txtServico.getText().length() > 5) {
			ServicoDAO servicoDAO = new ServicoDAO();
			Servico servico = new Servico();
			servico.setNome(txtServico.getText());
			ArrayList<Servico> servicos = new ArrayList<>();		
			servicos = servicoDAO.search(servico);
			servico = servicos.get(0);			

			String precoUn;
			precoUn = servico.getPreco();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUniServico.setText("R$ "+precoUn);
		}
	}

	@FXML
	void actionSertype(KeyEvent event) {
		if(txtServico.getText().length() > 5) {
			ServicoDAO servicoDAO = new ServicoDAO();
			Servico servico = new Servico();
			servico.setNome(txtServico.getText());
			ArrayList<Servico> servicos = new ArrayList<>();		
			servicos = servicoDAO.search(servico);
			servico = servicos.get(0);			

			String precoUn;
			precoUn = servico.getPreco();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUniServico.setText("R$ "+precoUn);
		} else {
			txtPrecoUniServico.setText("");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceFormaPag.getItems().add("Débito");
		choiceFormaPag.getItems().add("Credito");
		choiceFormaPag.getItems().add("Dinheiro");
		choiceFormaPag.getItems().add("Pix");

		// -------------------- barra de pesquisar produto --------------------				
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<String> nomeProdutos = new ArrayList<String>();
		nomeProdutos = produtoDAO.readProdutoByNome();
		String[] produto = new String[nomeProdutos.size()];

		for (int i = 0; i < nomeProdutos.size(); i++) {
			produto[i] = nomeProdutos.get(i);
		}
		TextFields.bindAutoCompletion(txtProduto, produto);	

		// -------------------- barra de pesquisar produto --------------------				
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayList<String> nomeServicos = new ArrayList<String>();
		nomeServicos = servicoDAO.readServicoByNome();
		String[] servico = new String[nomeServicos.size()];

		for (int i = 0; i < nomeServicos.size(); i++) {
			servico[i] = nomeServicos.get(i);
		}
		TextFields.bindAutoCompletion(txtServico, servico);	
	}

	private void CarregarTableProdutos(ArrayList<Produto> ArrayProdutos) {

		ObservableList<Produto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);

		columnIndiceProd.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnQtdProd.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnPrecoUniP.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnTotalProd.setCellValueFactory(new PropertyValueFactory<>("totalProduto"));	
		tableVendaProduto.setItems(produtosVendidos);						
	}
}
