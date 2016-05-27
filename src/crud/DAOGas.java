package crud;

import java.sql.*;

import java.util.*;
import model.Gas;
import config.Conexao;

public class DAOGas implements IDAOCrud<Gas> {

	public int salvar(Gas entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		ResultSet resultado = null;
		int codigo = 0;
		String sql;
		try {

			if (entidade.getIdProduto() == 0) {
				sql = "insert into Gas(nome, valor, pressao) values(?, ?, ?);";
				sqlParametro = conexao.prepareStatement(sql);
				sqlParametro.setString(1, entidade.getNome());
				sqlParametro.setDouble(2, entidade.getValor());
				sqlParametro.setDouble(3, entidade.getPressao());
				sqlParametro.executeUpdate();
				sql = "select last_insert_id() as Codigo";
				sqlParametro = conexao.prepareStatement(sql);
				resultado = sqlParametro.executeQuery();
				if (resultado.next()) {
					codigo = resultado.getInt("Codigo");
				}
			} else {
				sql = "update Gas set nome = ?, valor = ?, pressao = ? where idGas = ?;";
				sqlParametro = conexao.prepareStatement(sql);
				sqlParametro.setString(1, entidade.getNome());
				sqlParametro.setDouble(2, entidade.getValor());
				sqlParametro.setDouble(3, entidade.getPressao());
				sqlParametro.setInt(4, entidade.getIdProduto());
				sqlParametro.executeUpdate();
				codigo = entidade.getIdProduto();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return codigo;
	}

	public boolean excluir(Gas entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		String sql;
		boolean teste = false;
		try {
			sql = "delete from Gas where idGas = ?;";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setInt(1, entidade.getIdProduto());
			sqlParametro.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
				teste = true;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return teste;
	}

	public List<Gas> listar() {

		Connection conexao = new Conexao().geraConexao();
		List<Gas> lista = new ArrayList<Gas>();
		Statement consulta = null;
		ResultSet resultado = null;
		Gas entidade = null;
		String sql;
		try {
			sql = "select * from Gas order by idGas, nome, valor, pressao;";
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
				entidade = new Gas();
				entidade.setIdProduto(resultado.getInt("idGas"));
				entidade.setNome(resultado.getString("nome"));
				entidade.setValor(resultado.getDouble("valor"));
				entidade.setPressao(resultado.getDouble("pressao"));
				lista.add(entidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	public Gas buscarPorCodigo(int codigo) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Gas entidade = null;
		String sql;
		try {
			sql = "select * from Gas where idGas = ?;";
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, codigo);
			resultado = consulta.executeQuery();
			if (resultado.next()) {
				entidade = new Gas();
				entidade.setIdProduto(resultado.getInt("idGas"));
				entidade.setNome(resultado.getString("nome"));
				entidade.setValor(resultado.getDouble("valor"));
				entidade.setPressao(resultado.getDouble("pressao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return entidade;
	}
}