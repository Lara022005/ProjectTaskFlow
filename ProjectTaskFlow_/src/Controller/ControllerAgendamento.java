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
import Model.Cliente;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Button btCliente;
	
	@FXML
    private Button btFuncionario;
	
	@FXML
    private Button btMain;
	
	@FXML
    private Button btProduto;
   
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
    private TableColumn<Agendamento, String> columnStatusAgend;
    
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
    	alterarAgendamento = null;
    	Main.TelaRegistrarAgendamento();
		CarregarTableAgendamento();	  		
    }
    
    public static Agendamento alterarAgendamento = new Agendamento();
    public static Agendamento AlterarStatusAgend = new Agendamento();
    @FXML
    void actionAlterar(ActionEvent event) throws IOException {
    	
    	int i = tableAgendamentos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um agendamento para editar", AlertType.ERROR);   		
		}else {
			alterarAgendamento = tableAgendamentos.getItems().get(i);
			Main.TelaRegistrarAgendamento();
			alterarAgendamento = null;
		}
		CarregarTableAgendamento();		
    }    
      
    @FXML
    void actionConcluir(ActionEvent event) {
    	
   // 	ArrayList<Agendamento> agendamentos = new ArrayList<>();
    	Alert confirmation = new Alert (AlertType.CONFIRMATION);
    	Agendamento agendamento = new Agendamento();
    	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		 		   	   	
    	int i = tableAgendamentos.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar concluir", "Selecione um agendamento para ser concluído", AlertType.ERROR);   		
		}else {
			AlterarStatusAgend = tableAgendamentos.getItems().get(i);
			confirmation.setContentText("Deseja realmente concluir esse agendamneto?");
			
			Optional<ButtonType> resultado = confirmation.showAndWait();

			if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
				Cliente cliente = new Cliente();
				ClienteDAO clienteDAO = new ClienteDAO();
				ArrayList<Agendamento> agendamentos = new ArrayList<>();
				ArrayList<Cliente> clientes = new ArrayList<>();
				agendamento = tableAgendamentos.getItems().get(i);
				cliente.setCpf(null);
				cliente.setNome(agendamento.getIdCliente());
				clientes = clienteDAO.search(cliente);
				cliente = clientes.get(0);
				agendamento.setIdCliente(cliente.getId());
				System.out.println("Cliente: "+ cliente.getId());
				agendamentos = agendamentoDAO.searchIdAgendamento(agendamento);
				agendamento = agendamentos.get(0); 
				
				agendamentoDAO.updateStatusAgend(agendamento);	
			}							 
		//	AlterarStatusAgend = null;
		}
		CarregarTableAgendamento();	   	  	
    }

    @FXML
    void actionPesquisar(ActionEvent event) {
    	PesquisarTableAgedamento();
    	tableAgendamentos.refresh();
    }
    
    @FXML
    void telaCliente(ActionEvent event) throws IOException {
		Main.TelaCliente();
//		CarregarTableCliente();
    }

    @FXML
    void telaFuncionario(ActionEvent event) throws IOException {
    	Main.TelaFuncionario();
    }

    @FXML
    void telaMain(ActionEvent event) throws IOException {
    	Main.TelaHome();
    }

    @FXML
    void telaProduto(ActionEvent event) throws IOException {
    	Main.TelaProduto();
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
		columnStatusAgend.setCellValueFactory(new PropertyValueFactory<>("statusAgendamento"));
		tableAgendamentos.setItems(ArrayAgendamentos);						
	}
	
	public void PesquisarTableAgedamento() {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		Agendamento agendamento = new Agendamento();
		agendamento.setIdCliente(txtPesquisar.getText());

		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.search(agendamento.getIdCliente(), agendamento.getIdCliente()));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));	
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		columnStatusAgend.setCellValueFactory(new PropertyValueFactory<>("statusAgendamento"));
		tableAgendamentos.setItems(ArrayAgendamentos);						

	}    
	
	
}
