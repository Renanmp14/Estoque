public class Itens {

    private int codigo;
    private Categoria categoria;
    private String produto;
    private double valor;
    private int quantdade;
    private int quantidadeMinima;

    public Itens (){

    }

    // Método construtor


    public Itens(int codigo, Categoria categoria, String produto, double valor, int quantdade, int quantidadeMinima) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.produto = produto;
        this.valor = valor;
        this.quantdade = quantdade;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantdade() {
        return quantdade;
    }

    public void setQuantdade(int quantdade) {
        this.quantdade = quantdade;
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
}
