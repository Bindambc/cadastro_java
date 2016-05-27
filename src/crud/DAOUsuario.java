package crud;

import java.sql.*;

import java.util.*;
import model.Usuario;
import config.Conexao;

public class DAOUsuario implements IDAOCrud<Usuario> {


	public int salvar(Usuario entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		ResultSet resultado = null;
		int codigo = 0;
		String sql;
		try {

		if(entidade.getIdUsuario() == 0){
			sql = "insert into Usuario(login, senha) values(?, ?);";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setString(1, entidade.getLogin());
			sqlParametro.setString(2, entidade.getSenha());
			sqlParametro.executeUpdate();
			sql = "select last_insert_id() as Codigo";
			sqlParametro = conexao.prepareStatement(sql);
			resultado = sqlParametro.executeQuery();
			if (resultado.next()) {
			codigo = resultado.getInt("Codigo");
		}
		}
		else{
			sql = "update Usuario set login = ?, senha = ? where idUsuario = ?;";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setString(1, entidade.getLogin());
			sqlParametro.setString(2, entidade.getSenha());
			sqlParametro.setInt(3, entidade.getIdUsuario());
			sqlParametro.executeUpdate();
			codigo = entidade.getIdUsuario();
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

	public boolean excluir(Usuario entidade) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement sqlParametro = null;
		String sql;
		boolean teste = false;
		try {
			sql = "delete from Usuario where idUsuario = ?;";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setInt(1, entidade.getIdUsuario());
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

	public List<Usuario>  listar() {

		Connection conexao = new Conexao().geraConexao();
		List<Usuario> lista = new ArrayList<Usuario>();
		Statement consulta = null;
		ResultSet resultado = null;
		Usuario entidade = null;
		String sql;
		try {
			sql = "select * from Usuario order by idUsuario, login, senha;";
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
			entidade = new Usuario();
			 entidade.setIdUsuario(resultado.getInt("idUsuario"));
			 entidade.setLogin(resultado.getString("login"));
			 entidade.setSenha(resultado.getString("senha"));
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

	public Usuario  buscarPorCodigo(int codigo) {

		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Usuario entidade = null;
		String sql;
		try {
			sql = "select * from Usuario where idUsuario = ?;";
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, codigo);
			resultado = consulta.executeQuery();
			if (resultado.next()) {
			entidade = new Usuario();
			 entidade.setIdUsuario(resultado.getInt("idUsuario"));
			 entidade.setLogin(resultado.getString("login"));
			 entidade.setSenha(resultado.getString("senha"));
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