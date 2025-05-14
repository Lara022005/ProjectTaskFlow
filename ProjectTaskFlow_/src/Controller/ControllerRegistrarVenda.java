package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Produto;
import Model.Servico;
import Util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    void ActionAdicionarProduto(ActionEvent event) {
    	
    	if(txtCPF.getText() == "" || txtNomeCliente.getText() == "" || txtTotalProduto.getText() == "" || 
    			txtPesquisarProduto.getText() == "" || txtQtdProduto.getText() == "" ||txtPrecoProduto.getText() == "") {
    		Alerts.showAlert("Erro!", "Campos inválidos"," Verifique se os campos estãos preenchidos e tente novamente!", AlertType.ERROR);  
    		
    	} else {}
    	
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
		
    	Stage stage = (Stage) btVoltar.getScene().getWindow();
		stage.close();
  //  	Main.TelaHome();
    }

    @FXML
    void ActionAdicionarServico(ActionEvent event) {

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
