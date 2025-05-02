package Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerCliente {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCliente;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btMain;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TableColumn<?, ?> columnData;

    @FXML
    private TableColumn<?, ?> columnDescricao;

    @FXML
    private TableColumn<?, ?> columnHorario;

    @FXML
    private TableColumn<?, ?> columnIndice;

    @FXML
    private TableColumn<?, ?> columnNomeCliente;

    @FXML
    private TableColumn<?, ?> columnServico;

    @FXML
    private TableView<?> tableCliente;

    @FXML
    private TextField txtPesquisar;

    @FXML
    void ActionCliente(ActionEvent event) {

    }

    @FXML
    void ActionFuncionario(ActionEvent event) {

    }

    @FXML
    void ActionMain(ActionEvent event) {

    }

    @FXML
    void ActionProduto(ActionEvent event) {

    }

    @FXML
    void ActionSair(ActionEvent event) throws IOException {
    	Main.TelaHome();
    }

    @FXML
    void actionAlterar(ActionEvent event) {

    }

    @FXML
    void actionCadastrar(ActionEvent event) {

    }

    @FXML
    void actionExcluir(ActionEvent event) {

    }

    @FXML
    void actionPesquisar(ActionEvent event) {

    }

}
