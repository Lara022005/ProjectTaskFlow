package application;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private static Stage stage;
	private static Scene login;
	private static Scene main;
	private static Scene agendamento;
	private static Scene registroAgendamento;
	


	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			primaryStage.setTitle("TaskFlow - Login");

			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/View/viewLogin.fxml"));
			login = new Scene(fxmlLogin);
		
			primaryStage.setScene(login);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void changeScreen(String tela) {

		if(tela.equals("main")) {

			stage.setScene(main);
			stage.centerOnScreen();
			stage.setTitle("Menu principal");
			
		}else if(tela.equals("login")) {

			stage.setScene(login);
			stage.centerOnScreen();
			stage.setTitle("TaskFlow - Login");
			
		}else if(tela.equals("registroAgendamento")) {

			stage.setScene(registroAgendamento);
			stage.centerOnScreen();
			stage.setTitle("Registrar Agendamento");
		}
	}
	
	public static void TelaHome() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/viewMain.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
		
	}
	
	public static void TelaAgendamento() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/viewAgendamento.fxml"));
		Parent TelaAgendamento = fxmlHome.load();
		agendamento = new Scene(TelaAgendamento);
		
		stage.setScene(agendamento);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	public static void TelaRegistrarAgendamento() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/viewRegistrarAgendamento.fxml"));
		Parent TelaRegistrarAgendamento = fxmlHome.load();
		registroAgendamento = new Scene(TelaRegistrarAgendamento);
		
		stage.setScene(registroAgendamento);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	

	public static void main(String[] args) {
		
		launch(args);
	}
}





