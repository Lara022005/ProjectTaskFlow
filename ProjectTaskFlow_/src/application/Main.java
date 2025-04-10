package application;
import java.sql.Connection;
import ConnectionFactory.ConnectionDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private static Stage stage;
	private static Scene login;
	private static Scene main;


	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			primaryStage.setTitle("TaskFlow - Login");

			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/View/viewLogin.fxml"));
			login = new Scene(fxmlLogin);

			//		Parent fxmlMain = FXMLLoader.load(getClass().getResource("/View/viewMain.fxml"));
			//		main = new Scene(fxmlMain);


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
		}

	}

	public static void main(String[] args) {
		Connection con = ConnectionDataBase.getConnection();
		ConnectionDataBase.closeConnection(con);




		launch(args);
	}
}





