package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DAO.AgendamentoDAO;
import Model.Agendamento;
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
    	
    if(txtNomeCliente.getText() == "" || txtServico.getText() == "" ||dpDataAgend.getValue() == null || 
    		txtHorario.getText() == "") {   		
    	Alerts.showAlert("Erro!", "Informações obrigatorias não foram preenchidas"," Verifique e tente novamente", AlertType.ERROR);    		
    }else if(txtCpf.getText() == "" || cpfValidador.validarCPF(txtCpf.getText()) == false) {   		
    		Alerts.showAlert("Erro!", "CPF inválido"," Verifique o CPF e tente novamente", AlertType.ERROR);
    }
    else {
    	agendamento.setIdCliente(txtNomeCliente.getText());
    	agendamento.setIdServico(txtServico.getText());
    	agendamento.setDataAgendamento(dpDataAgend.getValue().toString());	
		agendamento.setDescricao(txtDescricao.getText());
		agendamento.setHorario(txtHorario.getText());	
		
		if(ControllerAgendamento.agendamentoAlterar == null) {
			agendamentoDAO.create(agendamento);   		
    		Alerts.showAlert("Sucesso!", "Cliente cadastrado", "Seja bem vindo", AlertType.INFORMATION);
    		Stage stage = (Stage) btCancelar.getScene().getWindow();
        	stage.close();
    		}else if(ControllerAgendamento.agendamentoAlterar != null) {
    		agendamentoDAO.update(agendamento);   		
    		Alerts.showAlert("Sucesso!", "Cliente editado", "O cliente foi editado com sucesso", AlertType.INFORMATION);    
    		Stage stage = (Stage) btCancelar.getScene().getWindow();
        	stage.close();
    		}			
    	}    
    }

    @FXML
    void actionCancelar(ActionEvent event) {

    }

    @FXML
    void actionExcluir(ActionEvent event) {

    }
    
    @FXML
    void actionCPFclick(MouseEvent event) {

    }

    @FXML
    void actionCPFtype(KeyEvent event)throws Exception  {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		if(ControllerAgendamento.agendamentoAlterar != null) {
			btAgendar.setText("Salvar");
			Agendamento agendamentoEditar = new Agendamento();
			agendamentoEditar = ControllerAgendamento.agendamentoAlterar;
			txtNomeCliente.setText(agendamentoEditar.getIdCliente());
			txtServico.setText(agendamentoEditar.getIdServico());
			LocalDate dataAgendamento = LocalDate.parse(agendamentoEditar.getDataAgendamento());
			dpDataAgend.setValue(dataAgendamento);
			txtDescricao.setText(agendamentoEditar.getDescricao());
			txtHorario.setText(agendamentoEditar.getHorario());			
		}
		
	}


}
