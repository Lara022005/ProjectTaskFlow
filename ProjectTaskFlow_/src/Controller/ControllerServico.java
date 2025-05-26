package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;

import DAO.ServicoDAO;
import DAO.ServicoDAO;
import DAO.ServicoVendaDAO;
import Model.Servico;
import Model.Servico;
import Model.ServicoVenda;
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

public class ControllerServico implements Initializable {

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
    private TextField txtPesquisar;

    @FXML
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TableColumn<Servico, String> columnDescricao;

    @FXML
    private TableColumn<Servico, String> columnIndice;

    @FXML
    private TableColumn<Servico, String> columnNomeServico;

    @FXML
    private TableColumn<Servico, String> columnPrecoUni;

    @FXML
    private TableView<Servico> tableServico;

    @FXML
    void ActionCliente(ActionEvent event) throws IOException {
		Main.TelaCliente();
		
    }

    @FXML
    void ActionFuncionario(ActionEvent event) throws IOException {
    	Main.TelaFuncionario();

    }

    @FXML
    void ActionMain(ActionEvent event) throws IOException {
    	
    	Main.TelaHome();

    }

    @FXML
    void ActionProduto(ActionEvent event) throws IOException {
    	Main.TelaProduto();

    }

    @FXML
    void ActionSair(ActionEvent event) throws IOException {
    	Main.TelaHome();

    }

    public static Servico alterarServico = new Servico ();
    
    @FXML
    void actionAlterar(ActionEvent event) throws IOException {
    	int i = tableServico.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um serviço para editar", AlertType.ERROR);   		
		}else {
			alterarServico = tableServico.getItems().get(i);
			Main.TelaCadastrarServico();
			alterarServico = null;
		}	
		CarregarTableServico();

    }

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	alterarServico = null;
    	Main.TelaCadastrarServico();
    	CarregarTableServico();
    }

    @FXML
    void actionExcluir(ActionEvent event) {
    	
    	int i = tableServico.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao concluir", "Selecione um serviço", AlertType.ERROR);   		
		}else {
			Servico servico = new Servico();
			servico = tableServico.getItems().get(i);

			Alert confirmation = new Alert (AlertType.CONFIRMATION);
			confirmation.setContentText("Deseja realmente excluir esse serviço? \n   " + servico.getNome());

			Optional<ButtonType> resultado = confirmation.showAndWait();

			if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
				ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();
				ServicoVenda servicoVenda = new ServicoVenda();
				ServicoDAO servicoDAO = new ServicoDAO();
				servicoVendaDAO.delete(servicoVenda);
				servicoDAO.delete(servico);

				Alerts.showAlert("Sucesso!", "Serviço excluído", "O serviço "+ servico.getNome()+", foi excluido com sucesso", AlertType.INFORMATION);
				CarregarTableServico();
			}
		}

    }

    @FXML
    void actionPesquisar(ActionEvent event) {
    	PesquisarTableServico();

    }
    
	private ObservableList<Servico> ArrayServico;

    
    
    public void CarregarTableServico() {
		
		
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayServico = FXCollections.observableArrayList(servicoDAO.read());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeServico.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnPrecoUni.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableServico.setItems(ArrayServico);


	}
    
    

    public void PesquisarTableServico() {
		ServicoDAO sercivoDAO = new ServicoDAO();
		Servico servico = new Servico();
		servico.setNome(txtPesquisar.getText());
		

		ArrayServico = FXCollections.observableArrayList(sercivoDAO.search(servico));
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNomeServico.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnPrecoUni.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableServico.setItems(ArrayServico);

	
	}
  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		CarregarTableServico();
		
		alterarServico = null;
		
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayList<String> nomeservico = new ArrayList<String>();
		nomeservico = servicoDAO.readServicoByNome();
		String[] servico = new String[nomeservico.size()];

		for (int i = 0; i < nomeservico.size(); i++) {
			servico[i] = nomeservico.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, servico);	
		
	}

}
