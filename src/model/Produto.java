package model;

public class Produto {
	private int idProduto;
	private String nome;
	private double valor;

	public Produto() {
	}

	public Produto(int idProduto, String nome, double valor) {
		super();
		this.idProduto=idProduto;
		this.nome = nome;
		this.valor = valor;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
