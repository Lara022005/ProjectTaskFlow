package Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerCadastrarServico {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btExcluir;

    @FXML
    void actionCadastrar(ActionEvent event) {

    }

    @FXML
    void actionExcluir(ActionEvent event) throws IOException {
    	Main.TelaServico();
    	

    }

}
