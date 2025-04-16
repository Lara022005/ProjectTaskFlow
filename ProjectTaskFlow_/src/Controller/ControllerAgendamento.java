package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.AgendamentoDAO;
import Model.Agendamento;
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

public class ControllerAgendamento implements Initializable{

    @FXML
    private Button btAgendar;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btConcluir;

    @FXML
    private Button btPesquisar;
    
    @FXML
    private TableColumn<Agendamento, String> columnData;

    @FXML
    private TableColumn<Agendamento, String> columnDescricao;

    @FXML
    private TableColumn<Agendamento, String> columnHorario;

    @FXML
    private TableColumn<Agendamento, String> columnIndice;

    @FXML
    private TableColumn<Agendamento, String> columnNomeCliente;
    
    @FXML
    private TableColumn<Agendamento, String> columnServico;

    @FXML
    private TableView<Agendamento> tableAgendamentos;

    @FXML
    private TextField txtPesquisar;

    @FXML
    void actionAgendar(ActionEvent event) {

    }

    @FXML
    void actionAlterar(ActionEvent event) {

    }

    @FXML
    void actionConcluir(ActionEvent event) {

    }

    @FXML
    void actionPesquisar(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		CarregarTableAgendamento();
		
	}
	
	private ObservableList<Agendamento> ArrayAgendamentos;
	
	public void CarregarTableAgendamento() {
    	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.readCompleta());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));	
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		tableAgendamentos.setItems(ArrayAgendamentos);						
	}

}
