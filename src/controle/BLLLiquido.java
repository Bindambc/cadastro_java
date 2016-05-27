package controle;

import java.util.*;
import model.Liquido;
import crud.DAOLiquido;

public class BLLLiquido implements IBLLCrud<Liquido> {

	public int salvar(Liquido entidade) {
		return new DAOLiquido().salvar(entidade);
	}

	public boolean excluir(Liquido entidade) {
		return new DAOLiquido().excluir(entidade);
	}

	public List<Liquido> listar() {
		return new DAOLiquido().listar();
	}

	public Liquido buscarPorCodigo(int codigo) {
		return new DAOLiquido().buscarPorCodigo(codigo);
	}
}