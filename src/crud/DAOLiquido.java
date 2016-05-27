package crud;

import java.sql.*;

import java.util.*;
import model.Liquido;
import config.Conexao;

public class DAOLiquido implements IDAOCrud<Liquido> {

	public int salvar(Liquido entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		ResultSet resultado = null;
		int codigo = 0;
		String sql;
		try {

			if (entidade.getIdProduto() == 0) {
				sql = "insert into Liquido(nome, valor, litros) values(?, ?, ?);";
				sqlParametro = conexao.prepareStatement(sql);
				sqlParametro.setString(1, entidade.getNome());
				sqlParametro.setDouble(2, entidade.getValor());
				sqlParametro.setDouble(3, entidade.getLitros());
				sqlParametro.executeUpdate();
				sql = "select last_insert_id() as Codigo";
				sqlParametro = conexao.prepareStatement(sql);
				resultado = sqlParametro.executeQuery();
				if (resultado.next()) {
					codigo = resultado.getInt("Codigo");
				}
			} else {
				sql = "update Liquido set nome = ?, valor = ?, litros = ? where idLiquido = ?;";
				sqlParametro = conexao.prepareStatement(sql);
				sqlParametro.setString(1, entidade.getNome());
				sqlParametro.setDouble(2, entidade.getValor());
				sqlParametro.setDouble(3, entidade.getLitros());
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

	public boolean excluir(Liquido entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		String sql;
		boolean teste = false;
		try {
			sql = "delete from Liquido where idLiquido = ?;";
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

	public List<Liquido> listar() {

		Connection conexao = new Conexao().geraConexao();
		List<Liquido> lista = new ArrayList<Liquido>();
		Statement consulta = null;
		ResultSet resultado = null;
		Liquido entidade = null;
		String sql;
		try {
			sql = "select * from Liquido order by idLiquido, nome, valor, litros;";
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
				entidade = new Liquido();
				entidade.setIdProduto(resultado.getInt("idLiquido"));
				entidade.setNome(resultado.getString("nome"));
				entidade.setValor(resultado.getDouble("valor"));
				entidade.setLitros(resultado.getDouble("litros"));
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

	public Liquido buscarPorCodigo(int codigo) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Liquido entidade = null;
		String sql;
		try {
			sql = "select * from Liquido where idLiquido = ?;";
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, codigo);
			resultado = consulta.executeQuery();
			if (resultado.next()) {
				entidade = new Liquido();
				entidade.setIdProduto(resultado.getInt("idLiquido"));
				entidade.setNome(resultado.getString("nome"));
				entidade.setValor(resultado.getDouble("valor"));
				entidade.setLitros(resultado.getDouble("litros"));
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