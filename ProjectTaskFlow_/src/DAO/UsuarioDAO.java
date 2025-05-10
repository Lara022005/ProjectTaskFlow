package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.Agendamento;
import Model.Funcionario;
import Model.Usuario;
import Util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class UsuarioDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Usuario usuario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Usuario values(?, ?, ?, ?)");			
			stmt.setString(1, usuario.getIdFuncionario());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getNivelUsuario());
			stmt.setString(4, usuario.getSenha());						

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}
	// ---------------------------------------  read ler (SELECT)	------------------------------------------
	public ArrayList<Usuario> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Usuario");
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Usuario usuario = new Usuario();				
				usuario.setId("" + i);
				usuario.setIdFuncionario(rs.getString(2));
				usuario.setNome(rs.getString(3));
				usuario.setNivelUsuario(rs.getString(4));
				usuario.setSenha(rs.getString(5));

				usuarios.add(usuario);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return usuarios;

	}
	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Usuario usuario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Usuario set Fk_Funcionario = ?, Nome_Usuario = ?,"
					+ "Nivel_Usuario = ?, Senha = ? where Nome_Usuario = ?");

			stmt.setString(1, usuario.getIdFuncionario());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getNivelUsuario());
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5, usuario.getNome());

			stmt.executeUpdate();
			System.out.println("Atualizar com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}
	// ---------------------------------------  delete apagar (DELETE) ----------------------------------------
	public void delete(Usuario usuario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Usuario where Id_Usuario = ? or Nome_Usuario = ?");		
			stmt.setString(1, usuario.getId());
			stmt.setString(2, usuario.getNome());

			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}
	// ---------------------------------------  search pesquisar (SELECT + LIKE) ------------------------------
	public ArrayList<Usuario>  search(Usuario usuario1){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Usuario where Id_Usuario like ? or Nome_Usuario like ?");
			stmt.setString(1, usuario1.getId());
			stmt.setString(2, usuario1.getNome());
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Usuario usuario = new Usuario();
				usuario.setId("" + i);
				usuario.setIdFuncionario(rs.getString(2));
				usuario.setNome(rs.getString(3));
				usuario.setNivelUsuario(rs.getString(4));
				usuario.setSenha(rs.getString(5));

				usuarios.add(usuario);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return usuarios;
	}	
	// ---------------------------- verificar se a senha e a mesma do banco ------------------------------
	public Usuario autenticarUser(String nome, String senha) {
		Connection con = ConnectionDataBase.getConnection(); // conectar com banco
		PreparedStatement stmt = null; // puxar informação do banco
		ResultSet rs = null; // tras resultado do banco
		Usuario usuario = new Usuario();

		try {						
			stmt = con.prepareStatement("select * from Usuario where Nome_Usuario = ? and Senha = ?");
			stmt.setString(1, nome);	
			stmt.setString(2, senha);
			rs = stmt.executeQuery();

			while(rs.next()) {
				usuario.setId(rs.getString(1));
				usuario.setIdFuncionario(rs.getString(2));
				usuario.setNome(rs.getString(3));
				usuario.setNivelUsuario(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("Erro!", "Erro de conexão!", "Falha ao consultar informações no banco de dados.", AlertType.ERROR);
			throw new RuntimeException("Erro de autenticação", e);
		} finally{
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}				
		return usuario;		
	}
	
	// --------------------------------------- ler as tabelas todas juntas
		public ArrayList<Usuario> readCompleta(){

			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Usuario> usuario1 = new ArrayList<>();	
			Funcionario funcionario = new Funcionario();
			
			try {
				stmt = con.prepareStatement("select Id_Usuario, Nome_Funcionario, Cpf_Funcionario, Nome_Usuario, Nivel_Usuario\n"
						+ "from Usuario, Funcionario\n"
						+ "where Id_Funcionario = Fk_Funcionario");

				rs = stmt.executeQuery();			
				int i = 1;

				while(rs.next()) { // so ira funcionar enquanto estiver linha 				
					Usuario usuario = new Usuario();
					usuario.setId("" + i);
					usuario.setIdFuncionario(rs.getString(2));	
					usuario.setSenha(rs.getString(3));	
					usuario.setNome(rs.getString(4));									
					usuario.setNivelUsuario(rs.getString(5));

					usuario1.add(usuario);
					i++;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}
			finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return usuario1;
		}
		
		public ArrayList<Usuario> search1(String nome, String cpf){
			
			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Usuario> usuarios = new ArrayList<>();

			try {
				stmt = con.prepareStatement("SELECT \n"
						+"	u.Id_Usuario,\n"						
						+ "    f.Nome_Funcionario,\n"
						+ "    f.Cpf_Funcionario,\n"
						+ "	u.Nome_Usuario,\n"
						+ "	u.Nivel_Usuario\n"
						+ "\n"
						+ "FROM \n"
						+ "    Usuario u\n"
						+ "\n"
						+ "JOIN \n"
						+ "    Funcionario f ON u.Fk_Funcionario = f.Id_Funcionario\n"
						+ "Where\n"
						+ "(? IS NULL OR Nome_Funcionario LIKE ?) or (? IS NULL OR Cpf_Funcionario LIKE ?)"); 

				stmt.setString(1, "%"+nome+"%");
				stmt.setString(2, "%"+nome+"%");
				stmt.setString(3, "%"+cpf+"%");
				stmt.setString(4, "%"+cpf+"%");
						
				rs = stmt.executeQuery();
				int i = 1;

				while(rs.next()) { // so ira funcionar enquanto estiver linha 				
					Usuario usuario = new Usuario();
					usuario.setId("" + i);
					usuario.setIdFuncionario(rs.getString(2));	
					usuario.setSenha(rs.getString(3));	
					usuario.setNome(rs.getString(4));									
					usuario.setNivelUsuario(rs.getString(5));
						
					usuarios.add(usuario);
					i++;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}
			finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return usuarios;
		}
		
		public ArrayList<String> readUsuarioByNome() {
			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<String> usuarios = new ArrayList<>();

			try {
				stmt = con.prepareStatement("SELECT Nome_Funcionario FROM Funcionario");
				rs = stmt.executeQuery();

				while (rs.next()) {
					String nome;
					nome = rs.getString(1);
					usuarios.add(nome);
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao ler os clientes!", e);
			} finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return usuarios;
		}
		
	
	


	

}
