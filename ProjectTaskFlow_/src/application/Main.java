package application;
import java.sql.Connection;
import ConnectionFactory.ConnectionDataBase;
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
	
//	Agendamento agendamento = new Agendamento();
//	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();	
	
//		agendamento.setId("");
//		agendamento.setIdServico("1");
//		agendamento.setIdCliente("1");
//		agendamento.setDataAgendamento("2005-04-02");				
//		agendamento.setDescricao("franjinha e selagem");
//		agendamento.setHorario("09:00:00");
//		
//		agendamentoDAO.create(agendamento);
	
// -------------------------------------------- read e search ----------------------------	
//		Produto produto = new Produto();
//		ProdutoDAO produtoDAO = new ProdutoDAO();
//		ArrayList<Produto> produtos = new ArrayList<>();
//		agendamento.setIdCliente("1");
//		agendamentos.addAll(agendamentoDAO.search("67890123456"));
	

//		for(int i = 0; i < agendamentos.size(); i++) {
//
//			agendamento = agendamentos.get(i);
//			System.out.println("");
//			System.out.print(produto.getId());
//			System.out.print(produto.getIdServico());
//			System.out.print(produto.getIdCliente());
//			System.out.print(produto.getDataAgendamento());
//			System.out.print(produto.getDescricao());
//			System.out.print(produto.getHorario());			
//		
//		}

	// -------------------------- update e delete ----------------------------
//		Agendamento agendamento = new Agendamento();
//		agendamento.setId("6");
//		agendamento.setIdServico("1");
//		agendamento.setIdCliente("1");
//		agendamento.setDataAgendamento("2005-04-10");				
//		agendamento.setDescricao("franjinha e selagem");
//		agendamento.setHorario("10:00:00");
//
//
//		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
//		agendamentoDAO.delete(agendamento);
	

	
	public static void main(String[] args) {
		Connection con = ConnectionDataBase.getConnection();
		ConnectionDataBase.closeConnection(con);
		
//		---------------------------------- create ------------------------------------
		
//		ServicoVenda servicoVenda = new ServicoVenda();
//		ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();	
//		
//			servicoVenda.setId("");
//			servicoVenda.setIdServico("1");
//			servicoVenda.setIdVenda("1");
//			servicoVenda.setQuantidade("10");				
//		
//			
//			servicoVendaDAO.create(servicoVenda);
		
// -------------------------------------------- read e search ----------------------------			
//		
//		ServicoVenda servicoVenda = new ServicoVenda();
//		ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();
//		ArrayList<ServicoVenda> servicoVendas = new ArrayList<>();
//		servicoVenda.setIdServico("1");
//		servicoVendas.addAll(servicoVendaDAO.read());
//	
//
//		for(int i = 0; i < servicoVendas.size(); i++) {
//
//			servicoVenda = servicoVendas.get(i);			
//			System.out.println("");
//			System.out.print(servicoVenda.getId());
//			System.out.print(servicoVenda.getIdServico());
//			System.out.print(servicoVenda.getIdVenda());
//			System.out.print(servicoVenda.getQuantidade());
//					
//		
//		}
		
// -------------------------- update e delete ----------------------------	
		
//		ServicoVenda servicoVenda = new ServicoVenda();
//		servicoVenda.setId("6");
//		servicoVenda.setIdServico("1");
//		servicoVenda.setIdVenda("1");
//		servicoVenda.setQuantidade("15");	
//			
//
//		ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAO();
//		servicoVendaDAO.delete(servicoVenda);
		
							
		launch(args);
	}
}





