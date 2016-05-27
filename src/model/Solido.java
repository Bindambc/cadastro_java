package model;

public class Solido extends Produto {
	private double metrosCubicos;

	public Solido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solido(int idProduto, String nome, double valor, double metrosCubicos) {
		super(idProduto, nome, valor);
		this.metrosCubicos = metrosCubicos;
	}

	public double getMetrosCubicos() {
		return metrosCubicos;
	}

	public void setMetrosCubicos(double metrosCubicos) {
		this.metrosCubicos = metrosCubicos;
	}

}
