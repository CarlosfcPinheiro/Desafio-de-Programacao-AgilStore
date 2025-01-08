import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.SerializationFeature;
import de.vandermeer.asciitable.AsciiTable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Estoque {
    private HashMap<String, Produto> mapEstoque;

    public Estoque(){
        this.mapEstoque = new HashMap<>();
    }

    public void adicionarProduto(Produto produto){
        this.mapEstoque.put(produto.getId(), produto);
    }

    public void excluirProduto(String id){
        this.mapEstoque.remove(id);
    }

    public void pesquisarProduto(String buscaRegex){
        Pattern pattern = Pattern.compile(buscaRegex, Pattern.CASE_INSENSITIVE);
        Map<String, Produto> resultadoBusca = new HashMap<>();

        for (Map.Entry<String, Produto> entry : mapEstoque.entrySet()){
            Produto produto = entry.getValue();
            if (pattern.matcher(produto.getNome()).find()){
                resultadoBusca.put(produto.getId(), produto);
            }
        }

        if (resultadoBusca.isEmpty()){
            System.out.println("\nNenhum produto encontrado!");
            return;
        }

        AsciiTable tabelaEstoque = new AsciiTable();

        tabelaEstoque.addRule();
        tabelaEstoque.addRow("ID", "NOME", "CATEGORIA", "QUANTIDADE", "PRECO");
        tabelaEstoque.addRule();

        for (Produto produto : resultadoBusca.values()){
            tabelaEstoque.addRow(
                    produto.getId(),
                    produto.getNome(),
                    produto.getCategoria(),
                    produto.getQuantidadeEmEstoque(),
                    produto.getPreco());
            tabelaEstoque.addRule();
        }

        String tabelaRenderizada = tabelaEstoque.render();
        System.out.println(tabelaRenderizada);
    }

    public Produto checarProduto(String id){
        return this.mapEstoque.get(id);
    }

    public void atualizarProduto(Produto produtoEscolhido, int opcao){
        Scanner sc = new Scanner(System.in);

        switch (opcao){
            case 1:
                System.out.print("Digite um novo nome: ");
                String nome = sc.nextLine();
                produtoEscolhido.setNome(nome);
                break;
            case 2:
                System.out.print("Digite um nova categoria: ");
                String categoria = sc.nextLine();
                produtoEscolhido.setCategoria(categoria);
                break;
            case 3:
                System.out.print("Digite um nova quantidade: ");
                int quantidade = sc.nextInt();
                produtoEscolhido.setQuantidadeEmEstoque(quantidade);
                break;
            case 4:
                System.out.print("Digite um novo preço: ");
                double preco = sc.nextDouble();
                produtoEscolhido.setPreco(preco);
                break;
            default:
                System.out.println("\nEssa opção é inválida.");
        }
    }

    public void mostrarEstoque(){
        if (this.mapEstoque.isEmpty()){
            System.out.println("\nO Estoque está vazio.");
        }
        else {
            AsciiTable tabelaEstoque = new AsciiTable();

            tabelaEstoque.addRule();
            tabelaEstoque.addRow("ID", "NOME", "CATEGORIA", "QUANTIDADE", "PRECO");
            tabelaEstoque.addRule();

            for (Produto produto : this.mapEstoque.values()){
                tabelaEstoque.addRow(
                        produto.getId(),
                        produto.getNome(),
                        produto.getCategoria(),
                        produto.getQuantidadeEmEstoque() + " unidades",
                        "R$ " + produto.getPreco());
                tabelaEstoque.addRule();
            }

            String tabelaRenderizada = tabelaEstoque.render();
            System.out.println(tabelaRenderizada);
        }
    }

    public void salvarProdutosEmJson(String caminho){
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminho), this.mapEstoque);
            System.out.println("Produtos salvos com sucesso em " + caminho);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void carregarProdutosDoJson(String caminho){
        File arquivo = new File(caminho);
        try{
            if (!arquivo.exists() || arquivo.length() == 0) {
                Files.write(Paths.get(caminho), "{}".getBytes()); // Inicializa um JSON vazio
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try{
            this.mapEstoque = mapper.readValue(new File(caminho), new TypeReference<HashMap<String, Produto>>() {
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}