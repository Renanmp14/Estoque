import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String path = "dados/Itens.csv";
        ArrayList<Itens> estoque = new ArrayList<>();

        //Itens it = new Itens("1234",3,"Camisa Verde",235.45, 70 );
        Itens it = new Itens(1234,Categoria.CAMISA,"Camisa Verde",235.45, 70,10);

        ItensCSVADM.AdicionaItens(it);

        //System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));

        //ItensCSVADM.AdicionaItensSolicitados();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            String itensDaLista = br.readLine();
            while(itensDaLista != null){
                String[] fields = itensDaLista.split(";");
                int codigo = Integer.parseInt(fields[0]);
                Categoria categoria = Categoria.valueOf(fields[1].toUpperCase());
                String nomeProduto = fields[2];
                double valor = Double.parseDouble(fields[3]);
                int quantidade = Integer.parseInt(fields[4]);
                int quantidadeMinima = Integer.parseInt(fields[5]);
                estoque.add(new Itens(codigo, categoria, nomeProduto, valor,quantidade,quantidadeMinima));

                itensDaLista = br.readLine();
            }
            System.out.println(estoque.toString().replace("[", "").replace("]"," "));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
