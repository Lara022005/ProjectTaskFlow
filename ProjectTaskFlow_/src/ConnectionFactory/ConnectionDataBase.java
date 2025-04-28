package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDataBase {
		
	private static final String Driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://192.168.70.148:51860;encrypt=false;databaseName=TaskFlow;user=sa;password=Senailab05";
	private static final String user = "sa";
	private static final String password = "Senailab05";
	
	
	/**
	 *  Metodo responsavel por conectar o banco de dados
	 * 
	 * 
	 * @return
	 * Sem retorno
	 */
	public static Connection getConnection() {

		try {
			Class.forName(Driver);
			System.out.println("Conexão bem sucedida!");
			return DriverManager.getConnection(URL, user, password );
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao conectar!", e);
		
		}
}
	/**
	 *  Metodo responsavel por fechar a conexão com banco.
	 * 
	 *
	 * @param con - Objeto do tipo connection que será fechado.
	 *
	 */
	public static void closeConnection(Connection con) {

		try {
			if(con != null) { 
				
				con.close();
				System.out.println("Conexão fechada!");
		}
	
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		
		}		
	}
	 /**
	  * Sobrecargado metodo responssavel por fechar a conexão do banco de dados.
	  * @param con - Objeto do tipo Connection que será fechado.
	  * @param stmt - Objeto do tipo PreparedStatement que será fechado.
	  */
	 public static void closeConnection(Connection con, PreparedStatement stmt) {
		 
		 closeConnection(con);
		 
			 try {
				 if(stmt != null) {
					 stmt.close();
				 } 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 /**
	  * Sobrecargado metodo responssavel por fechar a conexão do banco de dados.
	  * @param con - Objeto do tipo Connection que será fechado.
	  * @param stmt - Objeto do tipo PreparedStatement que será fechado.
	  * @param rs - Objeto do tipo ResultSet que será fechado.
	  */
	 public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		 
		 closeConnection(con, stmt);
		 
			 try {
				 if(rs != null) {
					 rs.close();
					 System.out.println("Conexão fechada");
				 } 
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
	 }
		 
	 		 		 	 

}
