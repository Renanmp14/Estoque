import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ItensCSV {

    //Caminho do arquivo
    private String Arquivo = "dados/Itens.csv";
    private Item item = new Item();
    Scanner lerInt = new Scanner(System.in);
    Scanner lerString = new Scanner(System.in);
    boolean sair = true;
    ArrayList<Item> estoque = new ArrayList<>();

    public boolean adicionaItensSolicitados (){
    try{
        //Verificar a existencia do arquivo
        boolean arquivoExiste = new File(Arquivo).exists();

        //Abrir o escritor do arquivo e validar se existe
        FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1,true);
        if (!arquivoExiste){
            escreve.write("Codigo;Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
        }
        System.out.println("Informe o Código: ");
        item.setCodigo(lerInt.nextInt());

        while(sair) {
                System.out.println("Informe a Categoria: \n1. Tênis\n2. Camisa\n3. Calça\n4. Bermuda\n5. Chinelo\n6. Bonê");
                int categoria = lerInt.nextInt();
                switch (categoria){
                    case 1:
                        item.setCategoria(Categoria.TENIS);
                        sair = false;
                        break;
                    case 2:
                        item.setCategoria(Categoria.CAMISA);
                        sair = false;
                        break;
                    case 3:
                        item.setCategoria(Categoria.CALCA);
                        sair = false;
                        break;
                    case 4:
                        item.setCategoria(Categoria.BERMUDA);
                        sair = false;
                        break;
                    case 5:
                        item.setCategoria(Categoria.CHINELO);
                        sair = false;
                        break;
                    case 6:
                        item.setCategoria(Categoria.BONE);
                        sair = false;
                        break;
                    default:
                        System.out.println("Opção Invalida. Tente Novamente.");
                        break;
                }
            }
            System.out.println("Informe o Produto: ");
            item.setNomeProduto(lerString.nextLine());
            System.out.println("Informe o Valor: ");
            item.setValor(lerInt.nextDouble());
            System.out.println("Informe Quantidade:");
            item.setQuantidade(lerInt.nextInt());
            System.out.println("Informe Quantidade Minima: ");
            item.setQuantidadeMinima(lerInt.nextInt());
            System.out.println("Finalizado");

            //Escrever o produto na tabela
            escreve.write(item.getCodigo()+";"+ item.getCategoria()+";"+ item.getNomeProduto()+";"+ item.getValor()+";"+ item.getQuantidade()+";"+ item.getQuantidadeMinima()+"\n");

            //Escreve o flush
            escreve.flush();

            //Fecha o escritor
            escreve.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    //Mostrar itens da Lista
    public String mostrarItensEstoque (){

          return getEstoque().toString().replace("[", "").replace("]"," ");
    }

    public ArrayList<Item> getEstoque (){
        try(BufferedReader br = new BufferedReader(new FileReader(Arquivo))){
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
                estoque.add(new Item(codigo, categoria, nomeProduto, valor,quantidade,quantidadeMinima));

                itensDaLista = br.readLine();
            }
            return estoque;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Modificar item

    //excluir item

    //Relatório


}
