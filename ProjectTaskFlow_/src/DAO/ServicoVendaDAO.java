package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.ServicoVenda;



public class ServicoVendaDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(ServicoVenda servicoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into ServicoVenda values(?, ?, ?)");
			stmt.setString(1, servicoVenda.getIdServico());
			stmt.setString(2, servicoVenda.getIdVenda());
			stmt.setString(3, servicoVenda.getQuantidade());				

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}

	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<ServicoVenda> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ServicoVenda> servicoVenda1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from ServicoVenda");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				ServicoVenda servicoVenda = new ServicoVenda();
				servicoVenda.setId(rs.getString(1));
				servicoVenda.setIdServico(rs.getString(3));				
				servicoVenda.setIdVenda(rs.getString(2));
				servicoVenda.setQuantidade(rs.getString(4));				

				servicoVenda1.add(servicoVenda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return servicoVenda1;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(ServicoVenda servicoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update ServicoVenda set Fk_Servico = ?, Fk_Venda = ?, Quantidade_sv = ? \r\n"
					+ "where Id_ServicoVenda = ? or Fk_Servico = ?\r\n");			
			stmt.setString(2, servicoVenda.getIdServico());
			stmt.setString(1, servicoVenda.getIdVenda());		
			stmt.setString(3, servicoVenda.getQuantidade());
			stmt.setString(4, servicoVenda.getId());	
			stmt.setString(5, servicoVenda.getIdServico());

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

	public void delete(ServicoVenda servicoVenda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from ServicoVenda where Id_ServicoVenda = ?");		
			stmt.setString(1, servicoVenda.getId());		

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
	public ArrayList<ServicoVenda> search(ServicoVenda servicoVenda2){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ServicoVenda> servicoVendas = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from ServicoVenda where Id_ServicoVenda like ? or Fk_Servico like ? ");
			stmt.setString(1, "%"+servicoVenda2.getId()+"%");
			stmt.setString(2, "%"+servicoVenda2.getIdServico()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				ServicoVenda servicoVenda = new ServicoVenda();
				servicoVenda.setId(rs.getString(1));				
				servicoVenda.setIdServico(rs.getString(3));				
				servicoVenda.setIdVenda(rs.getString(2));
				servicoVenda.setQuantidade(rs.getString(4));				

				servicoVendas.add(servicoVenda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return servicoVendas;
	}

}
