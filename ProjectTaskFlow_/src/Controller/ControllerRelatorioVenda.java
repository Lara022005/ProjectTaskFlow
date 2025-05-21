package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.ClienteDAO;
import DAO.VendaDAO;
import Model.Cliente;
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
		Venda venda = new Venda();
		Cliente cliente = new Cliente();
		ClienteDAO  clienteDAO = new ClienteDAO();
		
		ArrayVendas = FXCollections.observableArrayList(vendaDAO.readTableVenda());
		
		venda.setIdCliente(cliente.getNome());
		ArrayList<String> nome = clienteDAO.readClienteByNome();
	
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>(nome + "idCliente"));
		columnCpfCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));			
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnFormaPag.setCellValueFactory(new PropertyValueFactory<>("formaPag"));	
		tableVendas.setItems(ArrayVendas);						
	}
	
	
	public void PesquisarTableVenda() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		venda.setNome(txtPesquisar.getText());
		
		ArrayVendas = FXCollections.observableArrayList(vendaDAO.search(venda));
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnCpfCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));			
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnFormaPag.setCellValueFactory(new PropertyValueFactory<>("formaPag"));		
		tableVendas.setItems(ArrayVendas);						
	}
}
