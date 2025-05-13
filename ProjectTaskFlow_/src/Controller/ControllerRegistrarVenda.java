package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Produto;
import Model.Servico;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerRegistrarVenda implements Initializable {

    @FXML
    private Button btAdicionarProduto;

    @FXML
    private Button btAdicionarServico;

    @FXML
    private ImageView btBuscarCliente;

    @FXML
    private ImageView btBuscarProduto;

    @FXML
    private ImageView btBuscarServico;

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
    private ImageView btVoltar;

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
    private TextField txtCPF;

    @FXML
    private TextField txtDesconto;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtPesquisarProduto;

    @FXML
    private TextField txtPesquisarServico;

    @FXML
    private TextField txtPrecoProduto;

    @FXML
    private TextField txtPrecoServico;

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

    @FXML
    void AcBuscarCliente(MouseEvent event) {

    }

    @FXML
    void AcBuscarServico(MouseEvent event) {

    }

    @FXML
    void ActionAdicionarProduto(ActionEvent event) {

    }

    @FXML
    void ActionBuscarServico(ActionEvent event) {

    }

    @FXML
    void ActionCancelar(ActionEvent event) {
    	
    	txtCPF.setText("");
    	txtDesconto.setText("");
    	txtNomeCliente.setText("");
    	txtPesquisarProduto.setText("");
    	txtPesquisarServico.setText("");
    	txtPrecoProduto.setText("");
    	txtQtdProduto.setText("");
    	txtQtdServico.setText("");
    	txtTotalProduto.setText("");
    	txtTotalServico.setText("");
		txtTotalVenda.setText("");
		txtVendedor.setText("");
		txtDesconto.setText("");		
		choiceFormaPag.setValue(null);    	   	

		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();

    }

    @FXML
    void ActionDesconto(ActionEvent event) {

    }

    @FXML
    void ActionRegistrar(ActionEvent event) {

    }

    @FXML
    void ActionVoltar(MouseEvent event) throws IOException {
    	Main.TelaHome();
    }

    @FXML
    void ActionbtAdicionarServico(ActionEvent event) {

    }

    @FXML
    void actionNomeClick(MouseEvent event) {

    }

    @FXML
    void actionNomeType(KeyEvent event) {

    }

    @FXML
    void actionPesquisarCliente(ActionEvent event) {

    }

    @FXML
    void actionPesquisarProduto(ActionEvent event) {

    }

    @FXML
    void actionPesquisarServico(ActionEvent event) {

    }

    @FXML
    void actionServicoClick(MouseEvent event) {

    }

    @FXML
    void actionServicoType(KeyEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceFormaPag.getItems().add("Débito");
		choiceFormaPag.getItems().add("Credito");
		choiceFormaPag.getItems().add("Dinheiro");
		choiceFormaPag.getItems().add("Pix");
	}

}
