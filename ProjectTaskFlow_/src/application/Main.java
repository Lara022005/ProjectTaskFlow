package application;
	
import java.sql.Connection;


import ConnectionFactory.ConnectionDataBase;
import DAO.AgendamentoDAO;
import Model.Agendamento;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	---------------------------------- create ------------------------------------
	
//		agendamento.setId("");
//		agendamento.setIdServico("1");
//		agendamento.setIdCliente("1");
//		agendamento.setDataAgendamento("2005-04-02");				
//		agendamento.setDescricao("franjinha e selagem");
//		agendamento.setHorario("09:00:00");
//		
//		agendamentoDAO.create(agendamento);
	
// -------------------------------------------- read e search ----------------------------	
//		Agendamento agendamento = new Agendamento();
//		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
//		ArrayList<Agendamento> agendamentos = new ArrayList<>();
	//	agendamento.setIdCliente("1");
//		agendamentos.addAll(agendamentoDAO.search("67890123456"));
//	
//
//		for(int i = 0; i < agendamentos.size(); i++) {
//
//			agendamento = agendamentos.get(i);
//			System.out.println("");
//			System.out.print(agendamento.getId());
//			System.out.print(agendamento.getIdServico());
//			System.out.print(agendamento.getIdCliente());
//			System.out.print(agendamento.getDataAgendamento());
//			System.out.print(agendamento.getDescricao());
//			System.out.print(agendamento.getHorario());			
//		
//		}

	

	
	public static void main(String[] args) {
		Connection con = ConnectionDataBase.getConnection();
		ConnectionDataBase.closeConnection(con);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setId("6");
		agendamento.setIdServico("1");
		agendamento.setIdCliente("1");
		agendamento.setDataAgendamento("2005-04-10");				
		agendamento.setDescricao("franjinha e selagem");
		agendamento.setHorario("10:00:00");


		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		agendamentoDAO.delete(agendamento);
		
				
		launch(args);
	}
}
