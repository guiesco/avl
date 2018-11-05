/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeavl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author root
 */
public class TesteAVL {
    ArvoreAvl arvore = new ArvoreAvl();
    int elemento;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TesteAVL teste = new TesteAVL();
        teste.opcoes();
    }
    
    public void opcoes() {
        System.out.println("Escolha a ação desejada:");
        System.out.println("1 - Incluir elemento");
        System.out.println("--");
        System.out.println("2 - Excluir elemento");
        System.out.println("--");
        System.out.println("3 - Verificar se a árvore está balanceada");
        System.out.println("--");
        System.out.println("4 - Imprimir árvore");
        System.out.println("--");
        System.out.println("5 - Sair");
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        acao(opcao);
    }
    
    public void acao(int opcaoEscolhida) {
        Scanner sc = new Scanner(System.in);
        switch (opcaoEscolhida) {
            case 1: System.out.println("Digite o elemento que será adicionado:");
                    elemento = sc.nextInt();
                    arvore.inserir(elemento);
                    opcoes();
                    break;
            case 2: System.out.println("Digite o elemento que será removido:");
                    elemento = sc.nextInt();
                    arvore.remover(elemento);
                    opcoes();
                    break;
            case 3: 
                if (arvore.getBalanceamento() < 3) {
                    if (arvore.getBalanceamento() == 0){
                        System.out.println("A árvore está perfeitamente balanceada, com indice " + arvore.getBalanceamento());
                        opcoes();
                    }else{
                        System.out.println("A árvore está balanceada, com indice " + arvore.getBalanceamento());
                        System.out.println("");
                        System.out.println("");
                        opcoes();
                    }
                }
                    break;
            case 4:
                arvore.mostraArvore();
                opcoes();
                    break;
            case 5:
                System.out.println("Adiósm muchacho!");
                break;

            default: opcoes();
        }
    }
    
}
