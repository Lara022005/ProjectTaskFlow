package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.ServicoDAO;
import Model.Servico;
import Util.Alerts;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerCadastrarServico implements Initializable{
	
    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtServico;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btExcluir;
    
    @FXML
    private TextField txtID;

    @FXML
    void actionCadastrar(ActionEvent event) {
    	Servico servico = new Servico();
    	ServicoDAO servicoDAO = new ServicoDAO();
    	
    	 if(txtServico.getText() == "" || txtPreco.getText() == "" ) {   		
    		Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);
    		
    	} else { 
    		servico.setNome(txtServico.getText());
    		servico.setPrecoUni(txtPreco.getText());
    		servico.setDescricao(txtDesc.getText());
 		
    		
    	if(ControllerServico.alterarServico == null) {
    		servicoDAO.create(servico);   		
    		Alerts.showAlert("Sucesso!", "Serviço cadastrado", "Seja bem vindo", AlertType.INFORMATION);
    		Stage stage = (Stage) btExcluir.getScene().getWindow();
        	stage.close();
    	}else if(ControllerServico.alterarServico != null) {
    		servicoDAO.update(servico);     		
    		Alerts.showAlert("Sucesso!", "Serviço editado", "O serviço foi editado com sucesso", AlertType.INFORMATION);    
    		Stage stage = (Stage) btExcluir.getScene().getWindow();
        	stage.close();
    		}   	
    		
    	} 	

    }

    @FXML
    void actionExcluir(ActionEvent event) throws IOException {
    	Main.TelaServico();
    	
    	Stage stage = (Stage) btExcluir.getScene().getWindow();
    	stage.close();

    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		if(ControllerServico.alterarServico != null) {
			btCadastrar.setText("Salvar");
			Servico servicoEditar = new Servico();
			servicoEditar = ControllerServico.alterarServico;
			txtID.setText(servicoEditar.getId());
			txtServico.setText(servicoEditar.getNome());
			txtPreco.setText(servicoEditar.getPrecoUni());
			txtDesc.setText(servicoEditar.getDescricao());
		
		}
		
	}

}
