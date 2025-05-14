package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.ServicoDAO;
import DAO.UsuarioDAO;
import Model.Agendamento;
import Model.Cliente;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
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
    private TableColumn<Usuario, String> columnCpf;

    @FXML
    private TableColumn<Usuario, String> columnIndice;

    @FXML
    private TableColumn<Usuario, String> columnNivel;

    @FXML
    private TableColumn<Usuario, String> columnNome;

    @FXML
    private TableColumn<Usuario, String> columnUsuario;

    @FXML
    private TableView<Usuario> tableUsuarios;

    @FXML
    private TextField txtPesquisar;
    
    public static Usuario usuarioAlterar = new Usuario();

    @FXML
    void ActionAlterar(ActionEvent event) throws IOException {
    	int i = tableUsuarios.getSelectionModel().getSelectedIndex(); // valor clicado na tela
    	int nivel = Integer.parseInt(ControllerLogin.usuario.getNivelUsuario());

    		
    		if(i == -1) {
    			Alerts.showAlert("ERRO!", "Falha ao tentar editar", "Selecione um usuario para editar", AlertType.ERROR);   		
    		}else if(nivel == 1) {
    			usuarioAlterar = tableUsuarios.getItems().get(i);
    			Main.TelaCadastrarUsuario();
    			usuarioAlterar = null;
    		}
    		else {
    			Alerts.showAlert("Erro!", "Erro de nível de acesso!", "O seu nível não é permitido para acessar!", AlertType.ERROR);   		
    			
    		}
    		CarregarTableUsuario();
    	
    }

    @FXML
    void ActionCadastrar(ActionEvent event) throws IOException {
    	int nivel = Integer.parseInt(ControllerLogin.usuario.getNivelUsuario()); 
    	 if(nivel == 1) {
    	Main.TelaCadastrarUsuario();
    	 }else {
 			Alerts.showAlert("Erro!", "Erro de nível de acesso!", "O seu nível não é permitido para acessar!", AlertType.ERROR);   		

    	 }
    	
    }

    @FXML
    void ActionCliente(ActionEvent event) {

    }

    @FXML
    void ActionExcluir(ActionEvent event) {
    	int i = tableUsuarios.getSelectionModel().getSelectedIndex();
    	if(i == -1) {
    		Alerts.showAlert("Erro!!", "Falha ao Excluir","Erro! selecione um Usuario para excluir",AlertType.ERROR);
    	}else {
    		Usuario usuario = new Usuario();
    		usuario = tableUsuarios.getItems().get(i);
    		
    		Alert confirmation = new Alert(AlertType.CONFIRMATION);
    		confirmation.setContentText("Deseja realmente excluir o usuario "+ usuario.getNome()+"?");
    		
    		Optional<ButtonType> resultado = confirmation.showAndWait();
    		
    		if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
    			UsuarioDAO usuarioDAO = new UsuarioDAO();
    			usuarioDAO.delete(usuario);
    			
    			Alerts.showAlert("Sucesso!!", "Usuario excluido", "O usuario "+ usuario.getNome()+ " foi excluido com sucesso!!",AlertType.INFORMATION);
    			CarregarTableUsuario();
    		}
    	}
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
    void actionPesquisar(ActionEvent event) {
    	PesquisarTableUsuario();
    	tableUsuarios.refresh();
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CarregarTableUsuario();
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<String> nomesUsuarios = new ArrayList<String>();
		nomesUsuarios = usuarioDAO.readUsuarioByNome();
		String[] usuario = new String[nomesUsuarios.size()];

		for (int i = 0; i < nomesUsuarios.size(); i++) {
			usuario[i] = nomesUsuarios.get(i);
		}
		TextFields.bindAutoCompletion(txtPesquisar, usuario);
		
	}
	  
	private ObservableList<Usuario> ArrayUsuarios;
    
    public void CarregarTableUsuario() {
    	UsuarioDAO usuarioDAO = new UsuarioDAO();  	
    	
//    	terminar a readTerminar para funcionar===========================================================
    	ArrayUsuarios = FXCollections.observableArrayList(usuarioDAO.readCompleta());
		
		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("senha"));
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));	
		columnNivel.setCellValueFactory(new PropertyValueFactory<>("nivelUsuario"));	
		tableUsuarios.setItems(ArrayUsuarios);						
	}
    
    public void PesquisarTableUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario.setIdFuncionario(txtPesquisar.getText());

		ArrayUsuarios = FXCollections.observableArrayList(usuarioDAO.search1(usuario.getIdFuncionario(), usuario.getIdFuncionario()));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("senha"));
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));	
		columnNivel.setCellValueFactory(new PropertyValueFactory<>("nivelUsuario"));	
		tableUsuarios.setItems(ArrayUsuarios);						

	}
    
    

}
