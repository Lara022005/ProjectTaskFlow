package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDataBase;
import Model.Fornecedor;

public class FornecedorDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Fornecedor fornecedor) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Fornecedor values(?, ?, ?, ?)");
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getEndereco());
			stmt.setString(4, fornecedor.getTelefone());

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}

	}
	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Fornecedor> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Fornecedor");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getString(1));
				fornecedor.setNome(rs.getString(2));
				fornecedor.setCnpj(rs.getString(3));				
				fornecedor.setEndereco(rs.getString(4));
				fornecedor.setTelefone(rs.getString(5));

				fornecedores.add(fornecedor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return fornecedores;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Fornecedor fornecedor) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Fornecedor set nomeFornecedor = ?, cnpjFornecedor = ?, \r\n"
					+ "enderecoFornecedor = ?, telefoneFornecedor = ? where idFornecedor = ? or cnpjFornecedor = ?");
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());		
			stmt.setString(3, fornecedor.getEndereco());
			stmt.setString(4, fornecedor.getTelefone());
			stmt.setString(5, fornecedor.getId());
			stmt.setString(6, fornecedor.getCnpj());

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

	public void delete(Fornecedor fornecedor) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Fornecedor where idFornecedor = ? or cnpjFornecedor = ?");		
			stmt.setString(1, fornecedor.getId());
			stmt.setString(2, fornecedor.getCnpj());

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
	public ArrayList<Fornecedor> search(Fornecedor fornecedor2){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Fornecedor where cnpjFornecedor like ? or nomeFornecedor like ?");
			stmt.setString(1, "%"+fornecedor2.getCnpj()+"%");
			stmt.setString(2, "%"+fornecedor2.getNome()+"%");

			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getString(1));
				fornecedor.setNome(rs.getString(2));
				fornecedor.setCnpj(rs.getString(3));							
				fornecedor.setEndereco(rs.getString(4));
				fornecedor.setTelefone(rs.getString(5));

				fornecedores.add(fornecedor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return fornecedores;

	}

}
