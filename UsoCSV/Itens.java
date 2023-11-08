public class Itens {

    private int codigo;
    private int categoria;
    private String produto;
    private double valor;
    private int quantdade;

    public Itens (){

    }

    // Método construtor


    public Itens(int codigo, int categoria, String produto, double valor, int quantdade) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.produto = produto;
        this.valor = valor;
        this.quantdade = quantdade;
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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
