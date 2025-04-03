package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDataBase;
import Model.Produto;

public class ProdutoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Produto produto) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("insert into Produto values(?, ?, ?, ?, ?, ?)");		
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getCodBarra());
			stmt.setString(3, produto.getDataFab());
			stmt.setString(4, produto.getDataVal());
			stmt.setString(5, produto.getPrecoUni());
			stmt.setString(6, produto.getEstoque());									

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Produto> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));			
				produto.setNome(rs.getString(2));
				produto.setCodBarra(rs.getString(3));			
				produto.setDataFab(rs.getString(4));
				produto.setDataVal(rs.getString(5));				
				produto.setPrecoUni(rs.getString(6));
				produto.setEstoque(rs.getString(7));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Produto produto) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {
//------------------------------------ ver para tirar ------------------------
			stmt = con.prepareStatement("update Produto set nomeProduto = ?, codBarra = ? , dataFab = ?,dataVal = ?, \r\n"
					+ "precoUnitario = ?, estoque = ? where idProduto = ? or nomeProduto = ? or codBarra = ?");			
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getCodBarra());
			stmt.setString(3, produto.getDataFab());
			stmt.setString(4, produto.getDataVal());
			stmt.setString(5, produto.getPrecoUni());
			stmt.setString(6, produto.getEstoque());
			stmt.setString(7, produto.getNome());
			stmt.setString(8, produto.getCodBarra());


			stmt.executeUpdate();
			System.out.println("Atualizar com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  delete apagar (DELETE) --------------------------------------- 	
	public void delete(Produto produto) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Produto where idProduto = ? or codBarra = ?");		
			stmt.setString(1, produto.getId());
			stmt.setString(2, produto.getCodBarra());

			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}
	// ---------------------------------------  search pesquisar (SELECT + LIKE) --------------------------------------- 
	public ArrayList<Produto> search(Produto produto1){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto where nomeProduto like ? or codBarra like ?");			
			stmt.setString(1,"%" +produto1.getNome() +"%");
			stmt.setString(2, "%"+produto1.getCodBarra()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));			
				produto.setNome(rs.getString(2));
				produto.setCodBarra(rs.getString(3));			
				produto.setDataFab(rs.getString(4));
				produto.setDataVal(rs.getString(5));				
				produto.setPrecoUni(rs.getString(6));
				produto.setEstoque(rs.getString(7));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	public ArrayList<Produto> searchID(Produto produto1){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto where nomeProduto like ? or codBarra like ?");		
			stmt.setString(1,"%" +produto1.getNome() +"%");
			stmt.setString(2, "%"+produto1.getCodBarra()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));			
				produto.setNome(rs.getString(2));
				produto.setCodBarra(rs.getString(3));			
				produto.setDataFab(rs.getString(4));
				produto.setDataVal(rs.getString(5));				
				produto.setPrecoUni(rs.getString(6));
				produto.setEstoque(rs.getString(7));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	//	--------------------- retorna estoque baixo ---------------------

	public ArrayList<Produto> getByEstoque(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select*from Produto where estoque < 15");
			rs = stmt.executeQuery();

			int i = 1;
			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));			
				produto.setNome(rs.getString(2));
				produto.setCodBarra(rs.getString(3));			
				produto.setDataFab(rs.getString(4));
				produto.setDataVal(rs.getString(5));				
				produto.setPrecoUni(rs.getString(6));
				produto.setEstoque(rs.getString(7));

				produtos.add(produto);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}
	public ArrayList<String> readProdutoByNome() {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT nomeProduto FROM Produto");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nome;
				nome = rs.getString(1);
				produtos.add(nome);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os produtos!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtos;
	}

}
