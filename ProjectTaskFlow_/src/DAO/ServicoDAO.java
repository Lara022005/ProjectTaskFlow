package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.Servico;

public class ServicoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Servico servico) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Servico values(?, ?, ?)");
			stmt.setString(1, servico.getNome());
			stmt.setString(2, servico.getPrecoUni());
			stmt.setString(3, servico.getDescricao());									

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}
	}
	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Servico> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Servico> servico1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Servico");
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Servico servico = new Servico();
				servico.setId("" + i);
				servico.setNome(rs.getString(2));				
				servico.setPrecoUni(rs.getString(3));
				servico.setDescricao(rs.getString(4));			

				servico1.add(servico);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return servico1;
	}	
	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Servico servico) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Servico set  Nome_Servico = ?, Preco = ?, Descricao_Servico = ? where   Descricao_Servico = ? or  Nome_Servico = ?");						
			stmt.setString(1, servico.getNome());
			stmt.setString(2, servico.getPrecoUni());
			stmt.setString(3, servico.getDescricao());						
			stmt.setString(4, servico.getDescricao());
			stmt.setString(5, servico.getNome());
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

	public void delete(Servico servico) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Servico where Id_Servico = ? or Nome_Servico = ?");		
			stmt.setString(1, servico.getId());
			stmt.setString(2, servico.getNome());

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
		public ArrayList<Servico> search(Servico servico1){

			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Servico> servicos = new ArrayList<>();

			try {
				stmt = con.prepareStatement("select * from Servico where Id_Servico like ? or Nome_Servico like ? ");
				stmt.setString(1, "%"+servico1.getId()+"%");
				stmt.setString(2, "%"+servico1.getNome()+"%");
				rs = stmt.executeQuery();
				int i = 1;

				while(rs.next()) { // so ira funcionar enquanto estiver linha 				
					Servico servico = new Servico();				
					servico.setId("" + i);
					servico.setNome(rs.getString(2));				
					servico.setPrecoUni(rs.getString(3));
					servico.setDescricao(rs.getString(4));			
					
					servicos.add(servico);
					i++;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}
			finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return servicos;

		}
		
		public ArrayList<String> readServicoByNome() {
			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<String> servicos = new ArrayList<>();

			try {
				stmt = con.prepareStatement("SELECT Nome_Servico FROM Servico");
				rs = stmt.executeQuery();

				while (rs.next()) {
					String nome;
					nome = rs.getString(1);
					servicos.add(nome);
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao ler os clientes!", e);
			} finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return servicos;
		}

}
