package model;

public class Liquido extends Produto {
	private double litros;
	
	public Liquido(){
		super();
	}

	public Liquido(int idProduto, String nome, double valor, double litros) {
		super(idProduto, nome, valor);
		this.litros = litros;
	}

	public double getLitros() {
		return litros;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

}
