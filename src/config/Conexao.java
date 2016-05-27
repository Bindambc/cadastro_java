package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection geraConexao(){
		Connection conexao = null;
		String url = "jdbc:mysql://localhost/trabalho";
		String usuario = "root";
		String senha = "";
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}

}
