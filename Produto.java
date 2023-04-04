

public class Produto {
    String nome;
    String id;
    int quant;
    double preco;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Produto(String nome, String id, int quant, double preco) {
        this.nome = nome;
        this.id = id;
        this.quant = quant;
        this.preco = preco;
    }

    public Produto() {
        this.nome = nome;
        this.id = id;
        this.quant = quant;
        this.preco = preco;
    }

    public String toString(){
        return "Nome Produto : " + nome + " Id: " + id + " Quantidade em estoque: " + quant + " Preco: " + preco;

    }


}
