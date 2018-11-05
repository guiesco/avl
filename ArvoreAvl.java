package testeavl;

import java.util.ArrayList;

public class ArvoreAvl {

  protected No raiz;
  int balanceamento;
  String[] msgNiveis;

  	//inicia um valor e insere na arvore
	public void inserir(int k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
	}

	//faz verificações necessarias e acha  ao inserir
	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.raiz = aInserir;

		} else {

			if (aInserir.getChave() < aComparar.getChave()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getChave() > aComparar.getChave()) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}

			} else {
				// O nó já existe
			}
		}
	}

	//verifica o nivel de balanceamento no nodo atual
	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
  
	}

	//remove o valor desejado da arvore
	public void remover(int k) {
		removerAVL(this.raiz, k);
	}

	//faz verificações antes de remover
	public void removerAVL(No atual, int k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getChave() > k) {
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getChave() < k) {
				removerAVL(atual.getDireita(), k);

			} else if (atual.getChave() == k) {
				removerNoEncontrado(atual);
			}
		}
	}

	//faz verificações antes de remover
	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	//faz rotação simples à esquerda
	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	//faz rotação simples à direita
	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	//faz rotação dupla à esquerda e depois direita
	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	//faz rotação dupla à direita e depois esquerda
	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	//diz o sucessor do numero atual
	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	//diz a altura da arvore: 0 para folhas, maior na raiz
	public int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	//coloca o balanceamento no elemento No
	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	//retorna um arraylist com os valores em ordem
	final public ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(raiz, ret);
		return ret;
	}

	//Faz os laços e a recursividade do inOrder
	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
	}

	//busca o valor do balanceamento da arvore
	public int getBalanceamento() {
		return balanceamento;
	}

	//diz se a arvore está vazia
	public boolean isEmpty() {
		return raiz == null;
	}

	//monta e mostra a Arvore
	public void mostraArvore() {
		if (isEmpty()){
			System.out.println("Árvore vazia!");
			return;
		}
		String separador = String.valueOf("  |__");
		System.out.println(this.raiz.toString()+"("+altura(raiz)+")");
		mostraSubArvore(raiz.getEsquerda(),  separador);
		mostraSubArvore(raiz.getDireita(), separador);
	}

	//laço recusrsivo para mostrar a arvore completa
	private void mostraSubArvore(No no, String separador) {
		if (no != null) {
			No pai = no.getPai();
			if (no.equals(pai.getEsquerda()) == true) {
				System.out.println(separador+no.toString()+"(altura "+altura(no)+")"+" (ESQ)");
			}else{
				System.out.println(separador+no.toString()+"(altura "+altura(no)+")"+" (DIR)");
			}
			mostraSubArvore(no.getEsquerda(),  "     "+separador);
			mostraSubArvore(no.getDireita(), "     "+separador);
		}
	}

}