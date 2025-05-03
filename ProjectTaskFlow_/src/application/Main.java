package application;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

	private static Stage stage;
	private static Scene login;
	private static Scene main;
	private static Scene agendamento;
	private static Scene usuario;
	private static Scene servico;
	private static Scene cliente;
	private static Scene produto;
	private static Scene funcionario;

	
		
	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			primaryStage.setTitle("TaskFlow");
					

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
			
		}else if(tela.equals("Agendamento")) {

			stage.setScene(agendamento);
			stage.centerOnScreen();
			stage.setTitle("Cadastrar Agendamento");
		}
	}
	
	public static void TelaHome() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/ViewMain.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		
		stage.setTitle("Menu principal");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	
	public static void TelaAgendamento() throws IOException {
		FXMLLoader fxmlAgend = new FXMLLoader();
		fxmlAgend.setLocation(Main.class.getResource("/View/viewAgendamento.fxml"));
		Parent TelaAgendamento = fxmlAgend.load();
		agendamento = new Scene(TelaAgendamento);
		
		stage.setTitle("Tela Agendamento");
		stage.setScene(agendamento);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}		
	private static Stage cadAgendamento;
	public static void TelaRegistrarAgendamento() throws IOException {
		FXMLLoader fxmlCadastroAgendamento = new FXMLLoader();
		fxmlCadastroAgendamento.setLocation(Main.class.getResource("/View/ViewRegistrarAgendamento.fxml"));
		Parent cadastroAgendamento = fxmlCadastroAgendamento.load();
		Scene scene2 = new Scene(cadastroAgendamento);
		
		cadAgendamento = new Stage();
		cadAgendamento.setTitle("Cadastro/Edição Agendamento");
		cadAgendamento.initModality(Modality.WINDOW_MODAL);
		cadAgendamento.setScene(scene2);
		cadAgendamento.centerOnScreen();
		cadAgendamento.showAndWait();
	}
	
	public static void TelaUsuario() throws IOException {
		FXMLLoader fxmlUsuario = new FXMLLoader();
		fxmlUsuario.setLocation(Main.class.getResource("/View/ViewUsuario.fxml"));
		Parent TelaUsuario = fxmlUsuario.load();
		usuario = new Scene(TelaUsuario);
		
		stage.setTitle("Tela Usuario");
		stage.setScene(usuario);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	private static Stage cadastrarUsuario;
	public static void TelaCadastrarUsuario() throws IOException {
		FXMLLoader fxmlCadastroUsuario = new FXMLLoader();
		fxmlCadastroUsuario.setLocation(Main.class.getResource("/View/ViewCadastrarUsuario.fxml"));
		Parent cadastroUsuario = fxmlCadastroUsuario.load();
		Scene scene2 = new Scene(cadastroUsuario);
		
		cadastrarUsuario = new Stage();
		cadastrarUsuario.setTitle("Cadastro/Edição de Usuario");
		cadastrarUsuario.initModality(Modality.WINDOW_MODAL);
		cadastrarUsuario.setScene(scene2);
		cadastrarUsuario.centerOnScreen();
		cadastrarUsuario.showAndWait();
	}
	
	public static void TelaServico() throws IOException {
		FXMLLoader fxmlServico = new FXMLLoader();
		fxmlServico.setLocation(Main.class.getResource("/View/ViewServico.fxml"));
		Parent TelaServico = fxmlServico.load();
		servico = new Scene(TelaServico);
		
		stage.setTitle("Tela Serviço");
		stage.setScene(servico);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	private static Stage cadastrarServico;
	public static void TelaCadastrarServico() throws IOException {
		FXMLLoader fxmlCadastroServico = new FXMLLoader();
		fxmlCadastroServico.setLocation(Main.class.getResource("/View/ViewRegistrarServico.fxml"));
		Parent cadastroServico = fxmlCadastroServico.load();
		Scene scene2 = new Scene(cadastroServico);
		
		cadastrarServico = new Stage();
		cadastrarServico.setTitle("Cadastro/Edição de Serviço");
		cadastrarServico.initModality(Modality.WINDOW_MODAL);
		cadastrarServico.setScene(scene2);
		cadastrarServico.centerOnScreen();
		cadastrarServico.showAndWait();
	}
	
	public static void TelaCliente() throws IOException {
		FXMLLoader fxmlCliente = new FXMLLoader();
		fxmlCliente.setLocation(Main.class.getResource("/View/ViewCliente.fxml"));
		Parent TelaCliente = fxmlCliente.load();
		cliente = new Scene(TelaCliente);
		
		stage.setTitle("Tela Cliente");
		stage.setScene(cliente);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	private static Stage cadastrarCliente;
	public static void TelaCadastrarCliente() throws IOException {
		FXMLLoader fxmlCadastroCliente = new FXMLLoader();
		fxmlCadastroCliente.setLocation(Main.class.getResource("/View/ViewCadastrarCliente.fxml"));
		Parent cadastroCliente = fxmlCadastroCliente.load();
		Scene scene2 = new Scene(cadastroCliente);
		
		cadastrarCliente = new Stage();
		cadastrarCliente.setTitle("Cadastro/Edição de Cliente");
		cadastrarCliente.initModality(Modality.WINDOW_MODAL);
		cadastrarCliente.setScene(scene2);
		cadastrarCliente.centerOnScreen();
		cadastrarCliente.showAndWait();
	}
	
	public static void TelaProduto() throws IOException {
		FXMLLoader fxmlProduto = new FXMLLoader();
		fxmlProduto.setLocation(Main.class.getResource("/View/ViewProduto.fxml"));
		Parent TelaProduto = fxmlProduto.load();
		produto = new Scene(TelaProduto);
		
		stage.setTitle("Tela Produto");
		stage.setScene(produto);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	private static Stage cadastrarProduto;
	public static void TelaCadastrarProduto() throws IOException {
		FXMLLoader fxmlCadastroProduto = new FXMLLoader();
		fxmlCadastroProduto.setLocation(Main.class.getResource("/View/ViewCadastrarProduto.fxml"));
		Parent cadastroProduto = fxmlCadastroProduto.load();
		Scene scene2 = new Scene(cadastroProduto);
		
		cadastrarProduto = new Stage();
		cadastrarProduto.setTitle("Cadastro/Edição de Produto");
		cadastrarProduto.initModality(Modality.WINDOW_MODAL);
		cadastrarProduto.setScene(scene2);
		cadastrarProduto.centerOnScreen();
		cadastrarProduto.showAndWait();
	}
	
	public static void TelaFuncionario() throws IOException {
		FXMLLoader fxmlFuncionario = new FXMLLoader();
		fxmlFuncionario.setLocation(Main.class.getResource("/View/ViewFuncionario.fxml"));
		Parent TelaFuncionario = fxmlFuncionario.load();
		funcionario = new Scene(TelaFuncionario);
		
		stage.setTitle("Tela Funcionario");
		stage.setScene(funcionario);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();	
	}
	private static Stage cadastrarFuncionario;
	public static void TelaCadastrarFuncionario() throws IOException {
		FXMLLoader fxmlCadastroFuncionario = new FXMLLoader();
		fxmlCadastroFuncionario.setLocation(Main.class.getResource("/View/ViewCadastrarFuncionario.fxml"));
		Parent cadastroFuncionario = fxmlCadastroFuncionario.load();
		Scene scene2 = new Scene(cadastroFuncionario);
		
		cadastrarFuncionario = new Stage();
		cadastrarFuncionario.setTitle("Cadastro/Edição de Funcionario");
		cadastrarFuncionario.initModality(Modality.WINDOW_MODAL);
		cadastrarFuncionario.setScene(scene2);
		cadastrarFuncionario.centerOnScreen();
		cadastrarFuncionario.showAndWait();
	}
	
	private static Stage RegistroVenda;
	public static void TelaRegistroVenda() throws IOException {
		FXMLLoader fxmlServicoVenda = new FXMLLoader();
		fxmlServicoVenda.setLocation(Main.class.getResource("/View/ViewRegistrarVenda.fxml"));
		Parent ServicoVenda = fxmlServicoVenda.load();
		Scene scene2 = new Scene(ServicoVenda);
		
		RegistroVenda = new Stage();
		RegistroVenda.setTitle("Registro de Venda");
		RegistroVenda.initModality(Modality.WINDOW_MODAL);
		RegistroVenda.setScene(scene2);
		RegistroVenda.centerOnScreen();
		RegistroVenda.showAndWait();
	}
	

	public static void main(String[] args) {
		
		launch(args);
	}
}





