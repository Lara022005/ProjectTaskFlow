package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import Model.Cliente;
import Model.Cliente;
import Util.Alerts;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerCadastrarCliente implements Initializable {
	
    @FXML
    private Button btCadastrar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;


    @FXML
    private Button btExcluir;

    @FXML
    void actionCadastrar(ActionEvent event) {
    	Cliente cliente = new Cliente();
    	ClienteDAO clienteDAO = new ClienteDAO();
    	
    	 if(txtCliente.getText() == "" || txtCPF.getText() == "" || txtEndereco.getText() == "" || txtTelefone.getText() == "" || 
    			 txtEmail.getText() == "" ) {   		
    		Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);
    		
    	} else { 
    		cliente.setNome(txtCliente.getText());
    		cliente.setCpf(txtCPF.getText());
    		cliente.setEndereco( txtEndereco.getText());
    		cliente.setEmail(txtEmail.getText());
    		cliente.setTelefone(txtTelefone.getText());   		
    		
    	if(ControllerCliente.alterarCliente == null) {
    		clienteDAO.create(cliente);   		
    		Alerts.showAlert("Sucesso!", "Cliente cadastrado", "Seja bem vindo", AlertType.INFORMATION);
    		Stage stage = (Stage) btExcluir.getScene().getWindow();
        	stage.close();
    	}else if(ControllerCliente.alterarCliente != null) {
    		clienteDAO.update(cliente);     		
    		Alerts.showAlert("Sucesso!", "Cliente editado", "O produto foi editado com sucesso", AlertType.INFORMATION);    
    		Stage stage = (Stage) btExcluir.getScene().getWindow();
        	stage.close();
    		}   	
    		
    	} 	  
    	
    	
    }

    @FXML
    void actionExcluir(ActionEvent event) throws IOException {
    	
    	
    	Stage stage = (Stage) btExcluir.getScene().getWindow();
    	stage.close();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
		if(ControllerCliente.alterarCliente != null) {
			btCadastrar.setText("Salvar");
			Cliente clienteEditar = new Cliente();
			clienteEditar = ControllerCliente.alterarCliente;
			txtCliente.setText(clienteEditar.getNome());
			txtCPF.setText(clienteEditar.getCpf());
			txtEndereco.setText(clienteEditar.getEndereco());
			txtEmail.setText(clienteEditar.getEmail());	
			txtTelefone.setText(clienteEditar.getTelefone());
			
		}
		
	}
    
    
    
    

}
