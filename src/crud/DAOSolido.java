package crud;

import java.sql.*;

import java.util.*;
import model.Solido;
import config.Conexao;

public class DAOSolido implements IDAOCrud<Solido> {


	public int salvar(Solido entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		ResultSet resultado = null;
		int codigo = 0;
		String sql;
		try {

		if(entidade.getIdProduto() == 0){
			sql = "insert into Solido(nome, valor, metrosCubicos) values(?, ?, ?);";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setString(1, entidade.getNome());
			sqlParametro.setDouble(2, entidade.getValor());
			sqlParametro.setDouble(3, entidade.getMetrosCubicos());
			sqlParametro.executeUpdate();
			sql = "select last_insert_id() as Codigo";
			sqlParametro = conexao.prepareStatement(sql);
			resultado = sqlParametro.executeQuery();
			if (resultado.next()) {
			codigo = resultado.getInt("Codigo");
		}
		}
		else{
			sql = "update Solido set nome = ?, valor = ?, metrosCubicos = ? where idSolido = ?;";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setString(1, entidade.getNome());
			sqlParametro.setDouble(2, entidade.getValor());
			sqlParametro.setDouble(3, entidade.getMetrosCubicos());
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

	public boolean excluir(Solido entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		String sql;
		boolean teste = false;
		try {
			sql = "delete from Solido where idSolido = ?;";
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

	public List<Solido>  listar() {

		Connection conexao = new Conexao().geraConexao();
		List<Solido> lista = new ArrayList<Solido>();
		Statement consulta = null;
		ResultSet resultado = null;
		Solido entidade = null;
		String sql;
		try {
			sql = "select * from Solido order by idSolido, nome, valor, metrosCubicos;";
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
			entidade = new Solido();
			 entidade.setIdProduto(resultado.getInt("idSolido"));
			 entidade.setNome(resultado.getString("nome"));
			 entidade.setValor(resultado.getDouble("valor"));
			 entidade.setMetrosCubicos(resultado.getDouble("metrosCubicos"));
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

	public Solido  buscarPorCodigo(int codigo) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Solido entidade = null;
		String sql;
		try {
			sql = "select * from Solido where idSolido = ?;";
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, codigo);
			resultado = consulta.executeQuery();
			if (resultado.next()) {
			entidade = new Solido();
			 entidade.setIdProduto(resultado.getInt("idSolido"));
			 entidade.setNome(resultado.getString("nome"));
			 entidade.setValor(resultado.getDouble("valor"));
			 entidade.setMetrosCubicos(resultado.getDouble("metrosCubicos"));
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