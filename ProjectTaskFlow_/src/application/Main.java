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

	public static void main(String[] args) {
		
		launch(args);
	}
}





