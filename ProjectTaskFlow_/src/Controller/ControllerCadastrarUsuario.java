package Controller;
import DAO.UsuarioDAO;
import Model.Usuario;
import Util.Alerts;
import Util.NivelValidator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCadastrarUsuario {

    @FXML
    private AnchorPane TelaUsuario;

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
    private Button btProduto;

    @FXML
    private Button btSair;

    @FXML
    private TextField txtNivel;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    void ActionCliente(ActionEvent event) {}

    @FXML
    void ActionFuncionario(ActionEvent event) {}

    @FXML
    void ActionMain(ActionEvent event) {}

    @FXML
    void ActionProduto(ActionEvent event) {}

    @FXML
    void ActionSair(ActionEvent event) {}

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @FXML
    void actionCadastrar(ActionEvent event) {
        // Verifica se o usuário tem permissão
        if (usuarioLogado != null && usuarioLogado.getNivelUsuario() == 1) {
            // Verifica se os campos estão preenchidos
            if (txtNome.getText().isEmpty() || txtSenha.getText().isEmpty() || txtNivel.getText().isEmpty()) {
                Alerts.showAlert("Erro!", "Campos obrigatórios não preenchidos!", "Preencha todos os campos.", AlertType.ERROR);
                return;
            }

            try {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText());
                usuario.setSenha(txtSenha.getText());
                
                // Corrigido: conversão segura de String para int
                int nivel = Integer.parseInt(txtNivel.getText());
                usuario.setNivelUsuario(nivel);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.create(usuario);

                Alerts.showAlert("Sucesso!", "Cadastro realizado", "Usuário cadastrado com sucesso!", AlertType.INFORMATION);

                // Fecha a janela após cadastrar
                Stage stage = (Stage) btCadastrar.getScene().getWindow();
                stage.close();

            } catch (NumberFormatException e) {
                Alerts.showAlert("Erro!", "Nível inválido", "Digite um número válido para o nível de acesso.", AlertType.ERROR);
            }

        } else {
            Alerts.showAlert("Acesso negado", "Permissão negada", "Você não tem permissão para cadastrar usuários.", AlertType.WARNING);
        }
    }


    
    



    @FXML
    void actionExcluir(ActionEvent event) {}
}
