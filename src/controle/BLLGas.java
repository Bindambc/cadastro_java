package controle;

import java.util.*;
import model.Gas;
import crud.DAOGas;

public class BLLGas implements IBLLCrud<Gas> {

	public int salvar(Gas entidade) {
		return new DAOGas().salvar(entidade);
	}

	public boolean excluir(Gas entidade) {
		return new DAOGas().excluir(entidade);
	}

	public List<Gas> listar() {
		return new DAOGas().listar();
	}

	public Gas buscarPorCodigo(int codigo) {
		return new DAOGas().buscarPorCodigo(codigo);
	}
}