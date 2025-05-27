package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import DAO.AgendamentoDAO;
import DAO.FuncionarioDAO;
import Model.Agendamento;
import Model.Funcionario;
import Model.Usuario;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerMain implements Initializable {

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
	private Button btRelatorioVenda;

	@FXML
	private Button btSair;

	@FXML
	private Button btServico;

	@FXML
	private Button btUsuario;

	@FXML
	private Label txtNome;

	@FXML
	private Label txtAgendamento;

	@FXML
	private Label txtTotalVendido;

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

	@FXML
	private TableColumn<Agendamento, String> columnIndice;

	public static Funcionario funcionario = new Funcionario();
	public static Usuario usuario = new Usuario();
	public static Agendamento agendamento = new Agendamento();

	@FXML
	void actionSair(ActionEvent event) throws IOException {

		Alert confirmation = new Alert (AlertType.CONFIRMATION);
		confirmation.setContentText("Deseja realmente sair do sistema?");

		Optional<ButtonType> resultado = confirmation.showAndWait();
		if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
			Main.changeScreen("login");
		}	
	}
	@FXML
	void telaAgendamento(ActionEvent event) throws IOException {
		Main.TelaAgendamento();      	
	}
	@FXML
	void telaCliente(ActionEvent event) throws IOException {
		Main.TelaCliente();  	
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
	@FXML
	void telaRegistroVenda(ActionEvent event) throws IOException {
		Main.TelaRegistroVenda();
	}
	@FXML
	void telaServico(ActionEvent event) throws IOException {
		Main.TelaServico();
	}
	@FXML
	void telaUsuario(ActionEvent event) throws IOException {
		Main.TelaUsuario();
	}
	@FXML
	void actionRelatorioVenda(ActionEvent event) throws IOException {
		Main.TelaReLatorioVenda();
	}

	private ObservableList<Agendamento> ArrayAgendamentos;


	public void CarregarTableAgendamento() {

		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

		ArrayAgendamentos = FXCollections.observableArrayList(agendamentoDAO.readNomes());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));	
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataAgendamento"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));				
		tableClientesAgend.setItems(ArrayAgendamentos);						
	}



	@Override

	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

		String totalVendido;
		String totalAgendamento;

		totalVendido = funcionarioDAO.SomaTotalVendido(ControllerLogin.usuario.getId());		
		totalAgendamento = agendamentoDAO.getTotalAgendamento();


		double valorTotal = Double.parseDouble(totalVendido);
		totalVendido = String.format("%.2f", valorTotal);

		txtNome.setText(ControllerLogin.funcionario.getNome());
		txtTotalVendido.setText("R$ " + totalVendido);
		txtAgendamento.setText(totalAgendamento);			
		CarregarTableAgendamento();				
	}    




}