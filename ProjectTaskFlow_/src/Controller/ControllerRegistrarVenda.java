 package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoVendaDAO;
import DAO.ServicoDAO;
import DAO.ServicoVendaDAO;
import DAO.VendaDAO;
import Model.Cliente;
import Model.Produto;
import Model.ProdutoVenda;
import Model.Servico;
import Model.ServicoVenda;
import Model.Venda;
import Util.Alerts;
import application.Main;
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
	private static Servico servicoVenda = new Servico();
	private ArrayList<Produto> ArrayProdutos = new ArrayList<>();
	private ArrayList<Servico> ArrayServicos = new ArrayList<>();
	double totalVendaProd;
	double totalVendaServico;
	double desconto;
	String[] nomesProdutos = new String[200];
	String[] nomeServicos = new String[200];
	String[] quantidadeProd = new String[200];
	String[] quantidadeServico = new String[200];

	@FXML
	void ActionAdicionarProduto(ActionEvent event) {

		if (txtCPF.getText() == "" || txtNomeCliente.getText() == "" || txtTotalProduto.getText() == ""
				|| txtProduto.getText() == "" || txtQtdProduto.getText() == "" || txtPrecoUniProduto.getText() == "") {
			Alerts.showAlert("Erro!", "Campos inválidos",
					" Verifique se os campos estãos preenchidos e tente novamente!", AlertType.ERROR);

		} else {
			produtoVenda.setNome(txtProduto.getText());
			produtoVenda.setEstoque(txtQtdProduto.getText());
			produtoVenda.setPrecoUni(txtPrecoUniProduto.getText());
			produtoVenda.setTotalProduto(txtTotalProduto.getText());
			produtoVenda.setId("" + ArrayProdutos.size());
			String valor = txtTotalProduto.getText();
			valor = valor.replace(",", ".");
			double um = Double.parseDouble(valor);
			String totalVenda = txtTotalVenda.getText();
			totalVenda = totalVenda.replace(",", ".");
			
			if(txtTotalVenda.getText().equals("")) {
				double tres = um;
				double precoTotal = Double.parseDouble(valor);
				totalVendaProd = totalVendaProd + precoTotal;
				valor = String.format("%.2f", totalVendaProd);
				txtTotalVenda.setText("" +tres);
			}else {
			double dois = Double.parseDouble(txtTotalVenda.getText());
			double tres = um + dois;
			double precoTotal = Double.parseDouble(valor);
			totalVendaProd = totalVendaProd + precoTotal;
			valor = String.format("%.2f", totalVendaProd);
			
			txtTotalVenda.setText("" +tres);
			}
			
			valor = txtDesconto.getText();
			valor = valor.replace(",", ".");
			double valorDesconto = Double.parseDouble(valor);
			desconto = desconto + valorDesconto;

			nomesProdutos[ArrayProdutos.size()] = txtProduto.getText();
			quantidadeProd[ArrayProdutos.size()] = txtQtdProduto.getText();

			ArrayProdutos.add(produtoVenda);
			CarregarTableProdutos(ArrayProdutos);

			txtProduto.setText("");
			txtPrecoUniProduto.setText("");
			txtQtdProduto.setText("");
			txtTotalProduto.setText("");

		}

	}

	@FXML
	void ActionAdicionarServico(ActionEvent event) throws IOException {
		if (txtCPF.getText() == "" || txtNomeCliente.getText() == "" || txtTotalServico.getText() == ""
				|| txtServico.getText() == "" || txtQtdServico.getText() == "" || txtPrecoUniServico.getText() == "") {
			Alerts.showAlert("Erro!", "Campos inválidos",
					" Verifique se os campos estãos preenchidos e tente novamente!", AlertType.ERROR);

		} else {
			servicoVenda.setNome(txtServico.getText());
			servicoVenda.setEstoque(txtQtdServico.getText());
			servicoVenda.setPrecoUni(txtPrecoUniServico.getText());
			servicoVenda.setTotalServico(txtTotalServico.getText());
			servicoVenda.setId("" + ArrayProdutos.size());

			String valor = txtTotalServico.getText();
			valor = valor.replace(",", ".");
			double um = Double.parseDouble(valor);
			String totalVenda = txtTotalVenda.getText();
			totalVenda = totalVenda.replace(",", ".");
			
			if(txtTotalVenda.getText().equals("")) {
				double tres = um;
				double precoTotal = Double.parseDouble(valor);
				totalVendaServico = totalVendaServico + precoTotal;
				valor = String.format("%.2f", totalVendaServico);
				txtTotalVenda.setText("" +tres);
			}else {
			double dois = Double.parseDouble(txtTotalVenda.getText());
			double tres = um + dois;
			double precoTotal = Double.parseDouble(valor);
			totalVendaServico = totalVendaServico + precoTotal;
			valor = String.format("%.2f", totalVendaServico);
			
			txtTotalVenda.setText("" +tres);
			}
			nomeServicos[ArrayServicos.size()] = txtServico.getText();
			quantidadeServico[ArrayServicos.size()] = txtQtdServico.getText();

			ArrayServicos.add(servicoVenda);
			CarregarTableServicos(ArrayServicos);

			txtServico.setText("");
			txtPrecoUniServico.setText("");
			txtQtdServico.setText("");
			txtTotalServico.setText("");
		}
	}

	@FXML
	void ActionRegistrar(ActionEvent event) throws IOException {
		if (choiceFormaPag.getValue() == null || txtTotalVenda.getText() == "" || txtVendedor.getText() == "") {
			Alerts.showAlert("Erro!", "Campo inválido",
					" Verifique se os campos estão preenchidos e tente novamente!", AlertType.ERROR);
		} else {

			Venda venda = new Venda();
			VendaDAO vendaDAO = new VendaDAO();
			Cliente cliente = new Cliente();
			ClienteDAO clienteDAO = new ClienteDAO();

			ArrayList<Cliente> clientes = new ArrayList<>();
			cliente.setCpf(txtCPF.getText());
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);

			venda.setIdUsuario(ControllerLogin.usuario.getId());
			venda.setIdCliente(cliente.getId());
			venda.setFormaPag(choiceFormaPag.getValue().toString());
			venda.setDesconto("" + desconto);
			venda.setPrecoTotal(txtTotalServico.getText());
			venda.setPrecoTotal(txtTotalProduto.getText());
					
			
	
			
		//	venda.setDesconto(desc);
			
			String valor = txtTotalVenda.getText();
			valor = valor.replace(",", ".");
			double TotalServicoProduto = Double.parseDouble(valor);
			totalVendaProd = totalVendaProd + totalVendaServico + TotalServicoProduto;
			String aux = String.format("%.2f", totalVendaServico);
			aux = aux.replace(",", ".");
			venda.setPrecoTotal(aux);
			vendaDAO.create(venda);

			for (int i = 0; i < ArrayProdutos.size(); i++) { // size tamanho do array
				Produto produto = new Produto();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				ProdutoVenda produtoVenda = new ProdutoVenda();
				ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
				ArrayList<Produto> produtos = new ArrayList<>();
				produto.setNome(nomesProdutos[i]);
				produtos = produtoDAO.search(produto);
				produto = produtos.get(0);
				produtoVenda.setIdProduto(produto.getId());
				produtoVenda.setIdVenda(vendaDAO.readID());
				produtoVenda.setQuantidade(quantidadeProd[i]);
				produtoVendaDAO.create(produtoVenda);
				
			}

				for (int J = 0; J < ArrayServicos.size(); J++) {
					Servico servico = new Servico();
					ServicoDAO servicoDAO = new ServicoDAO();
					ServicoVenda servicoVenda = new ServicoVenda();
					ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();
					ArrayList<Servico> servicos = new ArrayList<>();
					servico.setNome(nomeServicos[J]);
					servicos = servicoDAO.search(servico);
					servico = servicos.get(0);
					servicoVenda.setIdServico(servico.getId());
					servicoVenda.setIdVenda(vendaDAO.readID());
					servicoVenda.setQuantidade(quantidadeServico[J]);
					servicoVendaDAO.create(servicoVenda);
				}

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

				Alerts.showAlert("Sucesso!", "Parabéns", "Venda realizada com sucesso", AlertType.INFORMATION);
			}
		
		ArrayProdutos = new ArrayList<Produto>();
		ArrayServicos = new ArrayList<Servico>();
		CarregarTableProdutos(ArrayProdutos);
		CarregarTableServicos(ArrayServicos);
	}

	@FXML
	void ActionCancelar(ActionEvent event) {	
		Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

	}

	
	
	@FXML
	void actionDescontoProd(KeyEvent event) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto.setNome(txtProduto.getText());
		ArrayList<Produto> produtos = new ArrayList<>();
		produtos = produtoDAO.search(produto);
		produto = produtos.get(0);
		double quantidade = Double.parseDouble(txtQtdProduto.getText());
		double precoUni = Double.parseDouble(produto.getPrecoUni());
		if (quantidade >= 15) {
			double desconto = (precoUni * quantidade) * 0.05;
			double precoTotal = precoUni * quantidade - desconto;
			txtDesconto.setText("" + String.format("%.2f", desconto));
			txtTotalProduto.setText("" + String.format("%.2f", precoTotal));

		} else if (quantidade < 15) {
			double precoTotal = precoUni * quantidade;
			txtDesconto.setText("0.00");
			txtTotalProduto.setText("" + String.format("%.2f", precoTotal));

		} else {
			txtDesconto.setText(null);
			txtTotalProduto.setText(null);
			txtPrecoUniProduto.setText(null);
		}
	}

	// ------------------ calcular preco total de itens do serviço -------------
	@FXML
	void ActionCalcularTotal(KeyEvent event) {
		ServicoDAO servicoDAO = new ServicoDAO();
		Servico servico = new Servico();
		servico.setNome(txtServico.getText());
		ArrayList<Servico> servicos = new ArrayList<>();
		servicos = servicoDAO.search(servico);
		servico = servicos.get(0);
		double quantidade = Double.parseDouble(txtQtdServico.getText());
		double precoUni = Double.parseDouble(servico.getPrecoUni());

		double precoTotal = precoUni * quantidade;
		txtTotalServico.setText("" + String.format("%.2f", precoTotal));
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
		Main.TelaHome();
	}

	@FXML
	void actionNomeClick(MouseEvent event) {
		if (txtCPF.getText().length() > 5) {
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
		if (txtCPF.getText().length() > 5) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpf(txtCPF.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtNomeCliente.setText(cliente.getNome());
		} else {
			txtNomeCliente.setText("");
		}
	}

	@FXML
	void actionProdClick(MouseEvent event) {
		if (txtProduto.getText().length() > 5) {
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

			txtPrecoUniProduto.setText("R$ " + precoUn);
		}
	}

	@FXML
	void actionProdtype(KeyEvent event) {
		if (txtProduto.getText().length() > 5) {
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

			txtPrecoUniProduto.setText("R$ " + precoUn);
		} else {
			txtPrecoUniProduto.setText("");
		}
	}

	@FXML
	void actionSerClick(MouseEvent event) {
		if (txtServico.getText().length() > 5) {
			ServicoDAO servicoDAO = new ServicoDAO();
			Servico servico = new Servico();
			servico.setNome(txtServico.getText());
			ArrayList<Servico> servicos = new ArrayList<>();
			servicos = servicoDAO.search(servico);
			servico = servicos.get(0);

			String precoUn;
			precoUn = servico.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUniServico.setText("R$ " + precoUn);
		}
	}

	@FXML
	void actionSertype(KeyEvent event) {
		if (txtServico.getText().length() > 5) {
			ServicoDAO servicoDAO = new ServicoDAO();
			Servico servico = new Servico();
			servico.setNome(txtServico.getText());
			ArrayList<Servico> servicos = new ArrayList<>();
			servicos = servicoDAO.search(servico);
			servico = servicos.get(0);

			String precoUn;
			precoUn = servico.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUniServico.setText("R$ " + precoUn);
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
		txtVendedor.setText(ControllerLogin.funcionario.getNome());

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

		// ----------------- barra de pesquisar cliente ----------------------------
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomeclientes = new ArrayList<String>();
		nomeclientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomeclientes.size()];

		for (int i = 0; i < nomeclientes.size(); i++) {
			cliente[i] = nomeclientes.get(i);
		}
		TextFields.bindAutoCompletion(txtNomeCliente, cliente);
		// ----------------- barra de pesquisar Vendedor(func)
		// ----------------------------
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ArrayList<String> nomeFuncionarios = new ArrayList<String>();
		nomeFuncionarios = funcionarioDAO.readFuncionarioByNome();
		String[] funcionario = new String[nomeFuncionarios.size()];

		for (int i = 0; i < nomeFuncionarios.size(); i++) {
			funcionario[i] = nomeFuncionarios.get(i);
		}
		TextFields.bindAutoCompletion(txtVendedor, funcionario);

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

	private void CarregarTableServicos(ArrayList<Servico> ArrayServicos) {

		ObservableList<Servico> servicosFeitos = FXCollections.observableArrayList(ArrayServicos);

		columnIndiceServ.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeServ.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnQtdServ.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnPrecoUniS.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnTotalServ.setCellValueFactory(new PropertyValueFactory<>("totalServico"));
		tableVendaServico.setItems(servicosFeitos);
	}
}
