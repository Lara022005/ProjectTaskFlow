package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import Model.Agendamento;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private Button btSair;
   
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
    void actionSair(ActionEvent event) throws IOException {
    	Main.TelaHome();
    	CarregarTableAgendamento();
    }
    @FXML
    void actionAgendar(ActionEvent event) throws IOException {
    	agendamentoAlterar = null;
    	Main.TelaRegistrarAgendamento();
		CarregarTableAgendamento();	  
		System.out.println("teste");
    }
    
    public static Agendamento agendamentoAlterar = new Agendamento();
    @FXML
    void actionAlterar(ActionEvent event) throws IOException {
    	
    	int i = tableAgendamentos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um agendamento para editar", AlertType.ERROR);   		
		}else {
			agendamentoAlterar = tableAgendamentos.getItems().get(i);
			Main.TelaRegistrarAgendamento();
			agendamentoAlterar = null;
		}
		CarregarTableAgendamento();		
    }

    @FXML
    void actionConcluir(ActionEvent event) {
    	
    	int i = tableAgendamentos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao concluir", "Selecione um agendamento", AlertType.ERROR);   		
		}else {
			Agendamento agendamento = new Agendamento();
			agendamento = tableAgendamentos.getItems().get(i);

			Alert confirmation = new Alert (AlertType.CONFIRMATION);
			confirmation.setContentText("Deseja realmente excluir o agendamento? \n  ");

			Optional<ButtonType> resultado = confirmation.showAndWait();

			if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
				AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
				agendamentoDAO.delete(agendamento);

				Alerts.showAlert("Sucesso!", "Agendamento excluído", "O foi excluido com sucesso", AlertType.INFORMATION);
				CarregarTableAgendamento();
			}
		}		   	
    }

    @FXML
    void actionPesquisar(ActionEvent event) {
    	PesquisarTableAgedamento();
    	tableAgendamentos.refresh();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
				
		CarregarTableAgendamento();
		
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomesClientes = new ArrayList<String>();
		nomesClientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomesClientes.size()];

		for (int i = 0; i < nomesClientes.size(); i++) {
			cliente[i] = nomesClientes.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, cliente);
			
	}
	
	private ObservableList<Agendamento> ArrayAgendamentos;
	
	public void CarregarTableAgendamento() {
    	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.readCompleta());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));	
		tableAgendamentos.setItems(ArrayAgendamentos);						
	}
	
	public void PesquisarTableAgedamento() {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		Agendamento agendamento = new Agendamento();
		agendamento.setIdCliente(txtPesquisar.getText());
//		agendamento.setNome(txtPesquisar.getText());

		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.search(agendamento.getIdCliente(), agendamento.getIdCliente()));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));	
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		tableAgendamentos.setItems(ArrayAgendamentos);						

	}    
	
	
}
