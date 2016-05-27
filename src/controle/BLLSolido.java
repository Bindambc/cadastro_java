package controle;

import java.util.*;
import model.Solido;
import crud.DAOSolido;

public class BLLSolido implements IBLLCrud<Solido> {

	public int salvar(Solido entidade) {
		return new DAOSolido().salvar(entidade);
	}

	public boolean excluir(Solido entidade) {
		return new DAOSolido().excluir(entidade);
	}

	public List<Solido> listar() {
		return new DAOSolido().listar();
	}

	public Solido buscarPorCodigo(int codigo) {
		return new DAOSolido().buscarPorCodigo(codigo);
	}
}