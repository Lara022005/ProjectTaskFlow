package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.Funcionario;
import Model.Usuario;


public class FuncionarioDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Funcionario funcionario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Funcionario values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getEndereco());
			stmt.setString(5, funcionario.getTelefone());		
			stmt.setString(6, funcionario.getGenero());
			stmt.setString(7, funcionario.getDataAdmissao());			
			stmt.setString(8, funcionario.getDataNasc());

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
	public ArrayList<Funcionario> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> Funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario");
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Funcionario funcionario = new Funcionario();				
				funcionario.setId("" + i);
				funcionario.setNome(rs.getString(2));
				funcionario.setCpf(rs.getString(3));
				funcionario.setEmail(rs.getString(4));
				funcionario.setEndereco(rs.getString(5));
				funcionario.setTelefone(rs.getString(6));			
				funcionario.setGenero(rs.getString(7));
				funcionario.setDataAdmissao(rs.getString(8));
				funcionario.setDataNasc(rs.getString(9));				

				Funcionarios.add(funcionario);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return Funcionarios;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Funcionario funcionario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Funcionario set Nome_Funcionario = ?, Cpf_Funcionario = ?, Email_Funcionario = ?,Endereco_Funcionario = ?,\r\n"
					+ "Telefone_Funcionario = ?, Genero_Funcionario = ?, Data_Admissao = ?, Data_Nasc = ? \r\n"
					+ "where Id_Funcionario = ? or Cpf_Funcionario = ?");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getEndereco());
			stmt.setString(5, funcionario.getTelefone());		
			stmt.setString(6, funcionario.getGenero());
			stmt.setString(7, funcionario.getDataAdmissao());			
			stmt.setString(8, funcionario.getDataNasc());
			stmt.setString(9, funcionario.getId());
			stmt.setString(10, funcionario.getCpf());

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
	public void delete(Funcionario funcionario) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Funcionario where Id_Funcionario = ? or Cpf_Funcionario = ?");		
			stmt.setString(1, funcionario.getId());
			stmt.setString(2, funcionario.getCpf());

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
	public ArrayList<Funcionario>  search(Funcionario funcionario1){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> Funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario where Cpf_Funcionario like ? or Nome_Funcionario like ?");
			stmt.setString(1, funcionario1.getCpf());
			stmt.setString(2, funcionario1.getNome());
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Funcionario funcionario = new Funcionario();
				funcionario.setId("" + i);
				funcionario.setNome(rs.getString(2));
				funcionario.setCpf(rs.getString(3));
				funcionario.setEmail(rs.getString(4));
				funcionario.setEndereco(rs.getString(5));
				funcionario.setTelefone(rs.getString(6));			
				funcionario.setGenero(rs.getString(7));
				funcionario.setDataAdmissao(rs.getString(8));
				funcionario.setDataNasc(rs.getString(9));	

				Funcionarios.add(funcionario);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return Funcionarios;

	}

// ----------------------------- pesquisar pelo id funcionario e mostrar na tela --------------------------
	public Funcionario searchName(Usuario usuario){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Funcionario funcionario = new Funcionario();

		try {
			stmt = con.prepareStatement("select Nome_Funcionario from Funcionario where Id_Funcionario = ?");
			stmt.setString(1, usuario.getIdFuncionario());
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				funcionario.setNome(rs.getString(1));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return funcionario;

	}

}





