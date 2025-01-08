
import com.fasterxml.jackson.core.JsonProcessingException;

// Ajeitar alguma formatação no terminal
// Ver algum problema de lógica
// Fazer README no repo

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        estoque.carregarProdutosDoJson("./produtos.json");
        boolean continuarPrograma = true;
        System.out.println("========================================");
        System.out.println("      SEJA BEM VINDO À AGILSTORE!");
        System.out.println("========================================");

        while (continuarPrograma) {
            int opcao = mostrarOpcoes();
            switch (opcao){
                case 1:
                    estoque.mostrarEstoque();
                    break;

                case 2:
                    Produto produto = receberInformacoesProduto();
                    estoque.adicionarProduto(produto);
                    System.out.println("\nProduto adicionado com sucesso!");
                    break;

                case 3:
                    String id = receberIdProduto();
                    Produto produtoEscolhido = estoque.checarProduto(id);
                    if (produtoEscolhido == null) {
                        System.out.println("\nNão existe produto com id: " + id);
                        break;
                    }
                    estoque.excluirProduto(id);
                    System.out.println("\nProduto excluído com sucesso!");
                    break;

                case 4:
                    id = receberIdProduto();
                    produtoEscolhido = estoque.checarProduto(id);
                    if (produtoEscolhido == null) {
                        System.out.println("\nNão existe produto com id: " + id);
                        break;
                    }
                    int opcaoCampo = escolherCampo();
                    estoque.atualizarProduto(produtoEscolhido, opcaoCampo);
                    break;

                case 5:
                    String buscaRegex = receberRegex();

                    estoque.pesquisarProduto(buscaRegex);
                    break;

                case 6:
                    estoque.salvarProdutosEmJson("./produtos.json");

                    System.out.println("\nFinalizando programa...");
                    continuarPrograma = false;
                    break;

                default:
                    System.out.println("\nEssa opção é inválida...\n");
            }
        }
    }

    public static int mostrarOpcoes(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n[1] MOSTRAR PRODUTOS");
        System.out.println("[2] ADICIONAR PRODUTO");
        System.out.println("[3] EXCLUIR PRODUTO");
        System.out.println("[4] ATUALIZAR PRODUTO");
        System.out.println("[5] PESQUISAR PRODUTO");
        System.out.println("[6] SAIR E SALVAR ESTOQUE");
        System.out.print("Escolha uma das opções acima de acordo com o índice: ");

        return sc.nextInt();
    }

    public static Produto receberInformacoesProduto(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Digite o nome da categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Digite a quantidade do produto: ");
        int quantidade = sc.nextInt();
        System.out.print("Digte o preço do produto: ");
        double preco = sc.nextDouble();

        return new Produto(nome, categoria, quantidade, preco);
    }

    public static int escolherCampo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n[1] NOME");
        System.out.println("[2] CATEGORIA");
        System.out.println("[3] QUANTIDADE EM ESTOQUE");
        System.out.println("[4] PREÇO");
        System.out.print("Escolha qual campo você deseja alterar: ");
        return sc.nextInt();
    }

    public static String receberIdProduto(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o ID do produto: ");
        return sc.nextLine();
    }

    public static String receberRegex(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite uma busca por um nome de um produto: ");
        return sc.nextLine();
    }
}