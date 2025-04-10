package Controller;

import DAO.AgendamentoDAO;
import Model.Agendamento;
import Model.Funcionario;
import Model.Usuario;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerMain {

    @FXML
    private Button btAgendamento;

    @FXML
    private Button btCliente;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btMain;

    @FXML
    private Button btProduto;

    @FXML
    private Button btRegistroVenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btServico;

    @FXML
    private Button btUsuario;

    @FXML
    private TableView<Agendamento> tableClientesAgend;   
    
    @FXML
    private TableColumn<Agendamento, String> columnData;

    @FXML
    private TableColumn<Agendamento, String> columnHorario;

    @FXML
    private TableColumn<Agendamento, String> columnNome;

    @FXML
    private TableColumn<Agendamento, String> columnServico;
    
    public static Funcionario funcionario = new Funcionario();
    
    public static Usuario usuario = new Usuario();

    @FXML
    void actionSair(ActionEvent event) {
    	Main.changeScreen("login");
    }

    @FXML
    void telaAgendamento(ActionEvent event) {

    }

    @FXML
    void telaCliente(ActionEvent event) {

    }

    @FXML
    void telaFuncionario(ActionEvent event) {

    }

    @FXML
    void telaMain(ActionEvent event) {

    }

    @FXML
    void telaProduto(ActionEvent event) {

    }

    @FXML
    void telaRegistroVenda(ActionEvent event) {

    }

    @FXML
    void telaServico(ActionEvent event) {

    }

    @FXML
    void telaUsuario(ActionEvent event) {

    }
    
    private ObservableList<Agendamento> ArrayAgendamentos;
    
    public void CarregarTableAgendamento() {
    	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.read());
		
		columnNome.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));		

		tableClientesAgend.setItems(ArrayAgendamentos);						
	}    


}
