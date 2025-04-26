package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.ConnectionDataBase;
import Model.Agendamento;
import Util.Alerts;
import javafx.scene.control.Alert.AlertType;


public class AgendamentoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Agendamento agendamento) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Agendamento values(?, ?, ?, ?)");			
			stmt.setString(1, agendamento.getIdCliente());
//			stmt.setString(2, agendamento.getIdServico());
			stmt.setString(2, agendamento.getDataAgendamento());
			stmt.setString(3, agendamento.getDescricao());
			stmt.setString(4, agendamento.getHorario());				

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
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId("" + i);				
				agendamento.setIdCliente(rs.getString(2));
				agendamento.setDataAgendamento(rs.getString(3));				
				agendamento.setDescricao(rs.getString(4));
				agendamento.setHorario(rs.getString(5));	
//				agendamento.setIdServico(rs.getString(6));

				agendamento1.add(agendamento);
				i++;
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

	// -------------------------------------- ler os relacionamentos entre as tabelas ----------------------------	
	public ArrayList<Agendamento> readNomes(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamento1 = new ArrayList<>();	
		
		try {
			stmt = con.prepareStatement("SELECT Id_Agendamento, Nome_Cliente, Nome_Servico,Data_Agendamento, Horario"
					+ " FROM Agendamento, Cliente, Servico,ServicoAgendamento sa"
					+ " where Id_Agendamento = sa.Fk_Agendamento "
					+ "AND Id_Cliente = Fk_Cliente "
					+ "AND Id_Servico = sa.Fk_Servico");

			rs = stmt.executeQuery();			
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId("" + i);
				agendamento.setIdCliente(rs.getString(2));			
				agendamento.setIdServico(rs.getString(3));
				agendamento.setDataAgendamento(rs.getString(4));							
				agendamento.setHorario(rs.getString(5));	
				String aux = agendamento.getHorario();
				aux = aux.replace(":00.0000000", "");
				agendamento.setHorario(aux);

				agendamento1.add(agendamento);
				i++;
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

			stmt = con.prepareStatement("UPDATE Agendamento set Fk_Cliente = ?,"
					+ "Data_Agendamento = ?, Descricao_Agendamento = ?, Horario = ? where Id_Agendamento = ?");							
			stmt.setString(1, agendamento.getIdCliente());						
			stmt.setString(2, agendamento.getDataAgendamento());
			stmt.setString(3, agendamento.getDescricao());
			stmt.setString(4, agendamento.getHorario());
			stmt.setString(5, agendamento.getId());


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
	public ArrayList<Agendamento> search(String nome, String cpf){
		
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamentos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT Id_Agendamento as Numero_Agendamento, Nome_Servico, Nome_Cliente, Cpf_Cliente,"				
					+ "    Data_Agendamento, Descricao_Agendamento, Horario FROM Agendamento a "										
					+ "    JOIN ServicoAgendamento sa ON a.Id_Agendamento = sa.Fk_Agendamento"
					+ "    JOIN Servico s ON sa.Fk_Servico = s.Id_Servico\r\n"
					+ "    JOIN Cliente c ON a.Fk_Cliente = c.Id_Cliente\r\n"
					+ " WHERE"
					+ "    (? IS NULL OR Nome_Cliente LIKE ?)"
					+ "    or (? IS NULL OR Cpf_Cliente LIKE ?)"); 

			stmt.setString(1, "%"+nome+"%");
			stmt.setString(2, "%"+nome+"%");
			stmt.setString(3, "%"+cpf+"%");
			stmt.setString(4, "%"+cpf+"%");
					
			rs = stmt.executeQuery();
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId("" + i);			
				agendamento.setIdServico(rs.getString(2));
				agendamento.setIdCliente(rs.getString(3));				
				agendamento.setDataAgendamento(rs.getString(4));				
				agendamento.setDescricao(rs.getString(5));
				agendamento.setHorario(rs.getString(6));
					

				agendamentos.add(agendamento);
				i++;
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
	// --------------------------------------- mostra total de agendamento do dia ---------------------------------

	public String getTotalAgendamento() {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String TotalAgendamento = null;

		try {
			stmt = con.prepareStatement("select count(Id_Agendamento) as TotalAgendamento from Agendamento where DATEDIFF(DD, Data_Agendamento, GETDATE()) = 0;");			
			rs = stmt.executeQuery();

			while(rs.next()) {
				TotalAgendamento = rs.getString(1);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("Erro!", "Erro de conexão!", "Falha ao consultar informações no banco de dados.", AlertType.ERROR);
			throw new RuntimeException("Erro!", e);
		} finally{
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}		

		return TotalAgendamento;
	}
	
// --------------------------------------- ler as tabelas todas juntas
	public ArrayList<Agendamento> readCompleta(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamento1 = new ArrayList<>();	
		
		try {
			stmt = con.prepareStatement("SELECT Id_Agendamento, Nome_Cliente, Nome_Servico,Data_Agendamento, Horario, Descricao_Agendamento"
					+ " FROM Agendamento, Cliente, Servico,ServicoAgendamento sa"
					+ " where Id_Agendamento = sa.Fk_Agendamento "
					+ "AND Id_Cliente = Fk_Cliente "
					+ "AND Id_Servico = sa.Fk_Servico");

			rs = stmt.executeQuery();			
			int i = 1;

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId("" + i);
				agendamento.setIdCliente(rs.getString(2));			
				agendamento.setIdServico(rs.getString(3));
				agendamento.setDataAgendamento(rs.getString(4));							
				agendamento.setHorario(rs.getString(5));	
				String aux = agendamento.getHorario();
				aux = aux.replace(":00.0000000", "");
				agendamento.setHorario(aux);
				agendamento.setDescricao(rs.getString(6));

				agendamento1.add(agendamento);
				i++;
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
// ----------------------- retorna o ultimo id adicionado ------------------------
	public ArrayList<Agendamento> readIDAdcionado(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamento1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM Agendamento ORDER BY Id_Agendamento DESC");					
			rs = stmt.executeQuery();
			

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId(rs.getString(1));								

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
	
	
public ArrayList<Agendamento> searchIdAgendamento(Agendamento agendamento1){
		
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agendamento> agendamentos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * from Agendamento WHERE "
					+ "Fk_Cliente = ? and Data_Agendamento = ? and Horario = ?"); 

			stmt.setString(1, agendamento1.getIdCliente());
			stmt.setString(2, agendamento1.getDataAgendamento());
			stmt.setString(3, agendamento1.getHorario());

					
			rs = stmt.executeQuery();
			

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Agendamento agendamento = new Agendamento();
				agendamento.setId(rs.getString(1));			
				agendamento.setIdCliente(rs.getString(2));				
				agendamento.setDataAgendamento(rs.getString(3));				
				agendamento.setDescricao(rs.getString(4));
				agendamento.setHorario(rs.getString(5));
					

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
