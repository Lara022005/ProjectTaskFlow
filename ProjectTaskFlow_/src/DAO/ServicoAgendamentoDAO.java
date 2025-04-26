package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.ServicoAgendamento;


public class ServicoAgendamentoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(ServicoAgendamento servicoAgendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into ServicoAgendamento values(?, ?, ?)");
			stmt.setString(1, servicoAgendamento.getIdServico());
			stmt.setString(2, servicoAgendamento.getIdAgendamento());
			stmt.setString(3, servicoAgendamento.getQuantidade());				

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}

	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<ServicoAgendamento> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ServicoAgendamento> servicoAgendamento1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from ServicoVenda");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				ServicoAgendamento servicoAgendamento = new ServicoAgendamento();
				servicoAgendamento.setId(rs.getString(1));
				servicoAgendamento.setIdServico(rs.getString(2));				
				servicoAgendamento.setIdAgendamento(rs.getString(3));
				servicoAgendamento.setQuantidade(rs.getString(4));				

				servicoAgendamento1.add(servicoAgendamento);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return servicoAgendamento1;

	}
	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(ServicoAgendamento servicoAgendamento) {

			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;

			try {

				stmt = con.prepareStatement("update ServicoAgendamento set Fk_Servico = ?, Fk_Agendamento = ?, Quantidade_sa = ? \r\n"
						+ "where Id_ServicoAgendamneto = ? and Fk_Agendamento = ?\r\n");			
				stmt.setString(1, servicoAgendamento.getIdServico());
				stmt.setString(2, servicoAgendamento.getIdAgendamento());		
				stmt.setString(3, servicoAgendamento.getQuantidade());
				stmt.setString(4, servicoAgendamento.getId());	
				stmt.setString(5, servicoAgendamento.getIdAgendamento());

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
	//---------------------------------------  delete --------------------------------------- 
	public void delete(ServicoAgendamento servicoAgendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from ServicoAgendamento where Id_ServicoAgendamento = ?");		
			stmt.setString(1, servicoAgendamento.getId());		

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
		public ArrayList<ServicoAgendamento> search(ServicoAgendamento servicoAgendamento2){

			Connection con = ConnectionDataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<ServicoAgendamento> servicoAgendamentos = new ArrayList<>();

			try {
				stmt = con.prepareStatement("select * from ServicoAgendamento where Id_ServicoAgendamento like ? or Fk_Agendamento like ? ");
				stmt.setString(1, "%"+servicoAgendamento2.getId()+"%");
				stmt.setString(2, "%"+servicoAgendamento2.getIdAgendamento()+"%");
				rs = stmt.executeQuery();

				while(rs.next()) { // so ira funcionar enquanto estiver linha 				
					ServicoAgendamento servicoAgendamento = new ServicoAgendamento();
					servicoAgendamento.setId(rs.getString(1));
					servicoAgendamento.setIdServico(rs.getString(2));				
					servicoAgendamento.setIdAgendamento(rs.getString(3));
					servicoAgendamento.setQuantidade(rs.getString(4));				

					servicoAgendamentos.add(servicoAgendamento);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}
			finally {
				ConnectionDataBase.closeConnection(con, stmt, rs);
			}
			return servicoAgendamentos;
		}

		
}
