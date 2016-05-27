package model;

public class Gas extends Produto {
	private double pressao;

	public Gas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gas(int idProduto, String nome, double valor, double pressao) {
		super(idProduto, nome, valor);
		this.pressao = pressao;

	}

	public double getPressao() {
		return pressao;
	}

	public void setPressao(double pressao) {
		this.pressao = pressao;
	}

}
