package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDataBase;
import Model.ProdutoVenda;



public class ProdutoVendaDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(ProdutoVenda produtoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into ProdutoVenda values(?, ?, ?)");
			stmt.setString(1, produtoVenda.getIdProduto());
			stmt.setString(2, produtoVenda.getIdVenda());
			stmt.setString(3, produtoVenda.getQuantidade());				

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}

	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<ProdutoVenda> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ProdutoVenda> produtoVenda1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from ProdutoVenda");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				ProdutoVenda produtoVenda = new ProdutoVenda();
				produtoVenda.setId(rs.getString(1));
				produtoVenda.setIdVenda(rs.getString(2));
				produtoVenda.setIdProduto(rs.getString(3));				
				produtoVenda.setQuantidade(rs.getString(4));				

				produtoVenda1.add(produtoVenda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtoVenda1;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(ProdutoVenda produtoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update ProdutoVenda set Fk_Venda = ?, Fk_Produto = ?, Quantidade_pv = ? \r\n"
					+ "where Id_ProdutoVenda = ? and Fk_Venda = ?\r\n");			
			stmt.setString(1, produtoVenda.getIdVenda());		
			stmt.setString(2, produtoVenda.getIdProduto());
			stmt.setString(3, produtoVenda.getQuantidade());
			stmt.setString(4, produtoVenda.getId());	
			stmt.setString(5, produtoVenda.getIdVenda());

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

	public void delete(ProdutoVenda produtoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from ProdutoVenda where Id_ProdutoVenda = ?");		
			stmt.setString(1, produtoVenda.getId());		

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
	public ArrayList<ProdutoVenda> search(ProdutoVenda produtoVenda2){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ProdutoVenda> produtoVendas = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from ProdutoVenda where Id_ProdutoVenda like ? or Fk_Venda like ? ");
			stmt.setString(1, "%"+produtoVenda2.getId()+"%");
			stmt.setString(2, "%"+produtoVenda2.getIdVenda()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				ProdutoVenda produtoVenda = new ProdutoVenda();
				produtoVenda.setId(rs.getString(1));
				produtoVenda.setIdVenda(rs.getString(2));
				produtoVenda.setIdProduto(rs.getString(3));				
				produtoVenda.setQuantidade(rs.getString(4));				

				produtoVendas.add(produtoVenda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return produtoVendas;
	}

}
