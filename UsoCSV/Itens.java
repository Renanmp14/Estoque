public class Itens {

    private int codigo;
    private Categoria categoria;
    private String nomeProduto;
    private double valor;
    private int quantidade;
    private int quantidadeMinima;

    public Itens (){

    }

    // Método construtor
    public Itens(int codigo, Categoria categoria, String produto, double valor, int quantidade, int quantidadeMinima) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.nomeProduto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    @Override
    public String toString() {
        return  " \n" + "Codigo: " + codigo +
                ", Categoria: " + categoria +
                ", nome do Produto: " + nomeProduto +
                ", valor R$ " + valor +
                ", Quantidade: " + quantidade +
                ", Quantidade Minima:" + quantidadeMinima + "\n";
    }
}
