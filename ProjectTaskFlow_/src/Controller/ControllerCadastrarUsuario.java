package Controller;

import java.io.IOException;
import java.util.ArrayList;

import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import DAO.ServicoDAO;
import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerCadastrarUsuario {

    @FXML
    private AnchorPane TelaUsuario;

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
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNivel;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

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
    void actionCadastrar(ActionEvent event) {
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
    }

    @FXML
    void actionExcluir(ActionEvent event) {

    }

}
