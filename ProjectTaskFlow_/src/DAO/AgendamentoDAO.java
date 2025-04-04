package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.Agendamento;


public class AgendamentoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Agendamento agendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Agendamento values( ?, ?, ?, ?, ?)");
			stmt.setString(1, agendamento.getIdServico());
			stmt.setString(2, agendamento.getIdCliente());
			stmt.setString(3, agendamento.getDataAgendamento());
			stmt.setString(4, agendamento.getDescricao());
			stmt.setString(5, agendamento.getHorario());				

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
	public ArrayList<Agendamento> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamento1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Agendamento");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId(rs.getString(1));
				agendamento.setIdServico(rs.getString(2));
				agendamento.setIdCliente(rs.getString(3));
				agendamento.setDataAgendamento(rs.getString(4));				
				agendamento.setDescricao(rs.getString(5));
				agendamento.setHorario(rs.getString(6));	

				agendamento1.add(agendamento);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return agendamento1;
	}	
	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Agendamento agendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("UPDATE Agendamento set Fk_Servico = ?, Fk_Cliente = ?,"
					+ "Data_Agendamento = ?, Descricao_Agendamento = ?, Horario = ? where Id_Agendamento = ?");					
			stmt.setString(1, agendamento.getIdServico());
			stmt.setString(2, agendamento.getIdCliente());						
			stmt.setString(3, agendamento.getDataAgendamento());
			stmt.setString(4, agendamento.getDescricao());
			stmt.setString(5, agendamento.getHorario());
			stmt.setString(6, agendamento.getId());
			

			stmt.executeUpdate();
			System.out.println("Atualizado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}	
	// ---------------------------------------  delete apagar (DELETE) --------------------------------------- 	
	public void delete(Agendamento agendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("delete from Agendamento where Id_Agendamento = ? or Data_Agendamento = ?");	
			stmt.setString(1, agendamento.getId());
			stmt.setString(2, agendamento.getDataAgendamento());

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
	public ArrayList<Agendamento> search(String cpf){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamentos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT Id_Agendamento as Numero_Agendamento, Nome_Servico, Nome_Cliente, Cpf_Cliente, "
					+ "Data_Agendamento, Descricao_Agendamento, Horario FROM Agendamento a, Cliente, Servico,ServicoAgendamento sa"
					+ " where Id_Agendamento = Fk_Agendamento "
					+ "AND Id_Cliente = Fk_Cliente "
					+ "AND Id_Servico = a.Fk_Servico "
					+ "and Cpf_Cliente like ?"); 
			
			stmt.setString(1, "%"+cpf+"%");

			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId(rs.getString(1));
				agendamento.setIdServico(rs.getString(2));
				agendamento.setIdCliente(rs.getString(3));
				agendamento.setDataAgendamento(rs.getString(4));				
				agendamento.setDescricao(rs.getString(5));
				agendamento.setHorario(rs.getString(6));				

				agendamentos.add(agendamento);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return agendamentos;
	}


}
