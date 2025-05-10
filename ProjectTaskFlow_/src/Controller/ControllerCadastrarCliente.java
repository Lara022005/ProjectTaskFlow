package Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerCadastrarCliente {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btExcluir;

    @FXML
    void actionCadastrar(ActionEvent event) {
    	
    	
    }

    @FXML
    void actionExcluir(ActionEvent event) throws IOException {
    	
    	
    	Stage stage = (Stage) btExcluir.getScene().getWindow();
    	stage.close();

    }
    
    
    
    

}
