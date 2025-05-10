package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DAO.ProdutoDAO;
import Model.Produto;
import Util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastrarProduto implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField txtCodBarra;

    @FXML
    private DatePicker dpDataFab;

    @FXML
    private DatePicker dpDataVal;

    @FXML
    private TextField txtEstoque;

    @FXML
    private TextField txtPrecoUni;

    @FXML
    private TextField txtProduto;

    @FXML
    void actionCadastrar(ActionEvent event) {
    	Produto produto = new Produto();
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	
    	 if(txtCodBarra.getText() == "" || dpDataFab.getValue() == null ||dpDataVal.getValue() == null || txtEstoque.getText() == "" || 
    			 txtPrecoUni.getText() == "" || txtProduto.getText() == null) {   		
    		Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);
    		
    	} else { 
    		produto.setNome(txtProduto.getText());
    		produto.setCodBarra(txtCodBarra.getText());
    		produto.setNome(txtProduto.getText());   	
    		produto.setDataFab(dpDataFab.getValue().toString());
    		produto.setDataVal(dpDataVal.getValue().toString());
    		produto.setPrecoUni(txtPrecoUni.getText());
    		produto.setEstoque(txtEstoque.getText());   		
    		
    	if(ControllerProduto.alterarProduto == null) {
    		produtoDAO.create(produto);   		
    		Alerts.showAlert("Sucesso!", "Produto cadastrado", "Seja bem vindo", AlertType.INFORMATION);
    		Stage stage = (Stage) btCancelar.getScene().getWindow();
        	stage.close();
    	}else if(ControllerProduto.alterarProduto != null) {
    		produtoDAO.create(produto);     		
    		Alerts.showAlert("Sucesso!", "Produto editado", "O produto foi editado com sucesso", AlertType.INFORMATION);    
    		Stage stage = (Stage) btCancelar.getScene().getWindow();
        	stage.close();
    		}   	   		  		
    	} 	    	
    }

    @FXML
    void actionCancelar(ActionEvent event) throws IOException {
    	
    	txtCodBarra.setText("");
    	txtEstoque.setText("");
    	txtPrecoUni.setText("");
    	txtProduto.setText(""); 	
    	dpDataFab.setValue(null);
    	dpDataVal.setValue(null);   	
    	
    	ControllerProduto.alterarProduto = null;
    	
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		if(ControllerProduto.alterarProduto != null) {
			btCadastrar.setText("Salvar");
			Produto produtoEditar = new Produto();
			produtoEditar = ControllerProduto.alterarProduto;
			txtCodBarra.setText(produtoEditar.getCodBarra());
			txtEstoque.setText(produtoEditar.getEstoque());
			txtPrecoUni.setText(produtoEditar.getPrecoUni());
			txtProduto.setText(produtoEditar.getNome());		
			LocalDate dataFab = LocalDate.parse(produtoEditar.getDataFab());
			dpDataFab.setValue(dataFab);
			LocalDate dataVal = LocalDate.parse(produtoEditar.getDataVal());
			dpDataVal.setValue(dataVal);
		}
		
	}
}
