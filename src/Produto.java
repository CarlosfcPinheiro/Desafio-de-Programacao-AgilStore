import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Produto {
    private final String id;
    private String nome;
    private String categoria;
    private int quantidadeEmEstoque;
    private double preco;

    @JsonCreator
    public Produto(@JsonProperty("nome") String nome, @JsonProperty("categoria") String categoria, @JsonProperty("quantidadeEmEstoque") int quantidadeEmEstoque, @JsonProperty("preco") double preco){
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.categoria = categoria;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
    }

    @Override
    public String toString(){
        return (" | " + this.nome + " | " + this.categoria + " | " + this.quantidadeEmEstoque + " unidades | R$ " + this.preco + " |");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getId(){
        return this.id;
    }
}
