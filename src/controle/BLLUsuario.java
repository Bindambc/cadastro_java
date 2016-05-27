package controle;

import java.util.*;
import model.Usuario;
import crud.DAOUsuario;

public class BLLUsuario implements IBLLCrud<Usuario> {

	public int salvar(Usuario entidade) {
		return new DAOUsuario().salvar(entidade);
	}

	public boolean excluir(Usuario entidade) {
		return new DAOUsuario().excluir(entidade);
	}

	public List<Usuario> listar() {
		return new DAOUsuario().listar();
	}

	public Usuario buscarPorCodigo(int codigo) {
		return new DAOUsuario().buscarPorCodigo(codigo);
	}
}