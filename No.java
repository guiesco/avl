package testeavl;

public class No {
  
	private No esquerda;
	private No direita;
	private No pai;
	private int chave;
	private int balanceamento;

	//construtor
	public No(int k) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setChave(k);
	}

	//transforma a chave de int para string
	public String toString() {
		return Integer.toString(getChave());
	}

	//busca a chave do valor
	public int getChave() {
		return chave;
	}

	//seta a chave
	public void setChave(int chave) {
		this.chave = chave;
	}

	//busca o valor do balanceamento
	public int getBalanceamento() {
		return balanceamento;
	}

	//seta balanceamento
	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	//pega o pai
	public No getPai() {
		return pai;
	}

	//seta o pai
	public No setPai(No pai) {
		this.pai = pai;
		return pai;
	}

	//pega filho da direita
	public No getDireita() {
		return direita;
	}

	//seta filho na direita
	public No setDireita(No direita) {
		this.direita = direita;
		return direita;
	}

	//pega filho da esquerda
	public No getEsquerda() {
		return esquerda;
	}

	//seta filho na esquerda
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
}