package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastrarProduto {

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
}
