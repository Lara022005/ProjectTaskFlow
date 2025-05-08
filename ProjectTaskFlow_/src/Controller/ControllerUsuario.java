package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.AgendamentoDAO;
import DAO.UsuarioDAO;
import Model.Agendamento;
import Model.Usuario;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerUsuario implements Initializable{

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
    private TableView<Usuario> tableUsuarios;

    @FXML
    private TextField txtPesquisar;
    
    public static Usuario usuarioAlterar = new Usuario();

    @FXML
    void ActionAlterar(ActionEvent event) {
    	int i = tableUsuarios.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um agendamento para editar", AlertType.ERROR);   		
		}else {
			usuarioAlterar = tableUsuarios.getItems().get(i);
			Main.TelaCadastrarUsuario();
			usuarioAlterar = null;
		}
		CarregarTableUsuario();
    }

    @FXML
    void ActionCadastrar(ActionEvent event) {

    }

    @FXML
    void ActionCliente(ActionEvent event) {

    }

    @FXML
    void ActionExcluir(ActionEvent event) {

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
    void ActionSair(ActionEvent event) {

    }

    @FXML
    void actionPesquisar(ActionEvent event) {

    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
	}
	
	private ObservableList<Usuario> ArrayUsuarios;
    
    public void CarregarTableAgendamento() {
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
//    	terminar a readTerminar para funcionar
    	ArrayUsuarios = FXCollections.observableArrayList(usuarioDAO.readCompleta());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));	
		columnStatusAgend.setCellValueFactory(new PropertyValueFactory<>("statusAgendamento"));
		tableAgendamentos.setItems(ArrayAgendamentos);						
	}
    
    

}
