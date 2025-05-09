package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import DAO.ServicoAgendamentoDAO;
import DAO.ServicoDAO;
import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import Model.ServicoAgendamento;
import Util.Alerts;
import Util.cpfValidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ControllerRegistrarAgendamento implements Initializable{

	@FXML
	private TextField NomeCliente;

	@FXML
	private Button btAgendar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btExcluir;
	
	@FXML
	private TextField txtNomeCliente;

	@FXML
	private TextField txtCpf;

	@FXML
	private DatePicker dpDataAgend;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtHorario;

	@FXML
	private TextField txtServico;


	@FXML
	void actionAgendar(ActionEvent event) {

		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		Agendamento agendamento = new Agendamento();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		ServicoDAO servicoDAO = new ServicoDAO();
		Servico servico = new Servico();

		ArrayList<Cliente> clientes = new ArrayList<>();
		cliente.setCpf(txtCpf.getText());
		cliente.setNome(txtNomeCliente.getText());
		clientes = clienteDAO.search(cliente);
		cliente = clientes.get(0);

		ArrayList<Servico> servicos = new ArrayList<>();
		servico.setNome(txtServico.getText());
		servicos = servicoDAO.search(servico);
		servico = servicos.get(0);

		ArrayList<Agendamento> agendamentos = new ArrayList<>();

		if(txtNomeCliente.getText() == "" || txtServico.getText() == "" ||dpDataAgend.getValue() == null || 
				txtHorario.getText() == "") {   		
			Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);    		
		}else if(txtCpf.getText() == "" || cpfValidador.validarCPF(txtCpf.getText()) == false) {   		
			Alerts.showAlert("Erro!", "CPF inválido"," Verifique o CPF e tente novamente", AlertType.ERROR);
		}
		else { 	
//			agendamento.setIdServico(servico.getId());
			agendamento.setIdCliente(cliente.getId());
			agendamento.setDataAgendamento(dpDataAgend.getValue().toString());	
			agendamento.setDescricao(txtDescricao.getText());
			agendamento.setHorario(txtHorario.getText());
//			agendamento.setStatusAgendamento(null);

			if(ControllerAgendamento.alterarAgendamento == null) {
				agendamentoDAO.create(agendamento);
				agendamentos = agendamentoDAO.readIDAdcionado();
				ServicoAgendamentoDAO saDAO = new ServicoAgendamentoDAO();
				ServicoAgendamento sa = new ServicoAgendamento();
				agendamento = agendamentos.get(0);
				sa.setIdServico(servico.getId());
				sa.setIdAgendamento(agendamento.getId());
				sa.setQuantidade("0");
				saDAO.create(sa);

				Alerts.showAlert("Sucesso!", "Cliente Agendado", "Agendamento concluído com sucesso", AlertType.INFORMATION);
				   		Stage stage = (Stage) btCancelar.getScene().getWindow();
				       	stage.close();
			}else {

				ArrayList<Cliente> clientes1 = new ArrayList<>();
				cliente.setCpf(null);
				cliente.setNome(ControllerAgendamento.alterarAgendamento.getIdCliente());
				clientes1 = clienteDAO.search(cliente);
				System.out.println(ControllerAgendamento.alterarAgendamento.getIdCliente()); 
				cliente = clientes1.get(0);
				System.out.println("Cliente: "+ cliente.getId());
				ControllerAgendamento.alterarAgendamento.setIdCliente(cliente.getId());
				System.out.println(ControllerAgendamento.alterarAgendamento.getHorario()); 
				System.out.println(ControllerAgendamento.alterarAgendamento.getDataAgendamento());
				agendamentos = agendamentoDAO.searchIdAgendamento(ControllerAgendamento.alterarAgendamento);
				agendamento = agendamentos.get(0); 
				cliente.setCpf(txtCpf.getText());
				cliente.setNome(txtNomeCliente.getText());
				clientes = clienteDAO.search(cliente);
				cliente = clientes.get(0);
				ArrayList<Servico> servicos1 = new ArrayList<>();
				servico.setNome(txtServico.getText());
				servicos1 = servicoDAO.search(servico);
				servico = servicos1.get(0);
				agendamento.setIdCliente(cliente.getId());
				agendamento.setIdCliente(cliente.getCpf());
				agendamento.setDataAgendamento(dpDataAgend.getValue().toString());	
				agendamento.setDescricao(txtDescricao.getText());
				agendamento.setHorario(txtHorario.getText());	
				System.out.println("Agendamento: "+ agendamento.getId()); 
				agendamentoDAO.update(agendamento);
				ServicoAgendamentoDAO saDAO = new ServicoAgendamentoDAO();
				ServicoAgendamento sa = new ServicoAgendamento();
				sa.setIdServico(servico.getId());
				sa.setIdAgendamento(agendamento.getId());
				sa.setQuantidade("1");
				saDAO.update(sa);

				ControllerAgendamento.alterarAgendamento = null;
				
				Alerts.showAlert("Sucesso!", "Cliente editado", "O cliente foi editado com sucesso", AlertType.INFORMATION);    
				   		Stage stage = (Stage) btCancelar.getScene().getWindow();
				       	stage.close();
			}			
		} 
	}	

	@FXML
	void actionCancelar(ActionEvent event) throws IOException {
		txtCpf.setText("");
		txtDescricao.setText("");
		txtHorario.setText("");
		txtNomeCliente.setText("");
		txtServico.setText("");
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();

	}


	@FXML
	void actionNomeClick(MouseEvent event) {
		if(txtCpf.getText().length() > 10) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpf(txtCpf.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtNomeCliente.setText(cliente.getNome());						
		}
	}

	@FXML
	void actionNomeType(KeyEvent event)throws Exception  {

		if(txtCpf.getText().length() > 10) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpf(txtCpf.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtNomeCliente.setText(cliente.getNome());						
		}else {
			txtNomeCliente.setText("");
		}
	}    	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		if(ControllerAgendamento.alterarAgendamento != null) {
			btAgendar.setText("Salvar");
			Agendamento agendamentoEditar = new Agendamento();
			agendamentoEditar = ControllerAgendamento.alterarAgendamento;
			txtNomeCliente.setText(agendamentoEditar.getIdCliente());
			txtServico.setText(agendamentoEditar.getIdServico());
			LocalDate dataAgendamento = LocalDate.parse(agendamentoEditar.getDataAgendamento());
			dpDataAgend.setValue(dataAgendamento);
			txtDescricao.setText(agendamentoEditar.getDescricao());
			txtHorario.setText(agendamentoEditar.getHorario());			
		}
		// -------------------- barra de pesquisar servico --------------------				
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayList<String> nomeServicos = new ArrayList<String>();
		nomeServicos = servicoDAO.readServicoByNome();
		String[] servico = new String[nomeServicos.size()];

		for (int i = 0; i < nomeServicos.size(); i++) {
			servico[i] = nomeServicos.get(i);
		}
		TextFields.bindAutoCompletion(txtServico, servico);	

		// -------------------- barra de pesquisar cliente --------------------		
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomesClientes = new ArrayList<String>();
		nomesClientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomesClientes.size()];

		for (int i = 0; i < nomesClientes.size(); i++) {
			cliente[i] = nomesClientes.get(i);
		}
		TextFields.bindAutoCompletion(txtNomeCliente, cliente);	
	}


}
