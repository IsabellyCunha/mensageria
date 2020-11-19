package br.mensageria.app.request;

public class TransferenciaRequest {
	private double valor;
	private String hashOrigem;
	private String hashDestino;
	private String tipo;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getHashOrigem() {
		return hashOrigem;
	}
	public void setHashOrigem(String hashOrigem) {
		this.hashOrigem = hashOrigem;
	}
	public String getHashDestino() {
		return hashDestino;
	}
	public void setHashDestino(String hashDestino) {
		this.hashDestino = hashDestino;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
