public class Main {
    public static void main(String[] args) {

        Itens it = new Itens(1234,Categoria.CAMISA,"Camisa Verde",235.45, 70,10);

       ItensCSVADM.AdicionaItens(it);

        //ItensCSVADM.AdicionaItensSolicitados();
    }
}
