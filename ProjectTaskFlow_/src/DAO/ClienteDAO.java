package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDataBase;
import Model.Cliente;


//  ----------------------------------- metodos C.R.U.D --------------------------------------------------------

public class ClienteDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Cliente cliente) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Cliente values(?, ?, ?, ?, ?)");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getEmail());	

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
	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Cliente> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Cliente");
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Cliente cliente = new Cliente();
				cliente.setId("" + i);
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEndereco(rs.getString(4));
				cliente.setTelefone(rs.getString(5));
				cliente.setEmail(rs.getString(6));				

				clientes.add(cliente);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return clientes;
	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Cliente cliente) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Cliente set Nome_Cliente = ?, Cpf_Cliente = ?,Endereco_Cliente = ?, \r\n"
					+ "Telefone_Cliente = ?, Email_Cliente = ? where Cpf_Cliente = ?");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());		
			stmt.setString(5, cliente.getEmail());		
			stmt.setString(6, cliente.getCpf());

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

	// ---------------------------------------  delete apagar (DELETE) --------------------------------------- 	
	public void delete(Cliente cliente) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Cliente where Id_Cliente = ? or Cpf_Cliente = ?");		
			stmt.setString(1, cliente.getId());
			stmt.setString(2, cliente.getCpf());

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
	// ---------------------------------------  search pesquisar (SELECT + LIKE) --------------------------------------- 
	public ArrayList<Cliente> search(Cliente cliente1){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Cliente where Cpf_Cliente like ? or Nome_Cliente like ?");
			stmt.setString(1, "%"+cliente1.getCpf()+"%");
			stmt.setString(2, "%"+cliente1.getNome()+"%");
			rs = stmt.executeQuery();
			

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Cliente cliente = new Cliente();
				cliente.setId(rs.getString(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEndereco(rs.getString(4));
				cliente.setTelefone(rs.getString(5));
				cliente.setEmail(rs.getString(6));	

				clientes.add(cliente);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return clientes;
	}
// -------------------------------- puxar somente nome do cliente -------------------------
	public ArrayList<String> readClienteByNome() {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> clientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT Nome_Cliente FROM Cliente");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nome;
				nome = rs.getString(1);
				clientes.add(nome);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os clientes!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return clientes;
	}

	// ---------------------------------------  search pesquisar apenas CPF (SELECT + LIKE) --------------------------------------- 
		public ArrayList<Cliente> searchCPF(Cliente cliente1){

			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Cliente> clientes = new ArrayList<>();

			try {
				stmt = con.prepareStatement("select * from Cliente where Cpf_Cliente like ?");
				stmt.setString(1, "%"+cliente1.getCpf()+"%");				
				rs = stmt.executeQuery();
				

				while(rs.next()) { // so ira funcionar enquanto estiver linha 				
					Cliente cliente = new Cliente();
					cliente.setId(rs.getString(1));
					cliente.setNome(rs.getString(2));
					cliente.setCpf(rs.getString(3));
					cliente.setEndereco(rs.getString(4));
					cliente.setTelefone(rs.getString(5));
					cliente.setEmail(rs.getString(6));	

					clientes.add(cliente);				
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}
			finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return clientes;
		}
}

