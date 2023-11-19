import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ItensCSV {

    //Caminho do arquivo
    private String Arquivo = "./dados/Itens.csv";
    private Item item = new Item();
    Scanner lerInt = new Scanner(System.in);
    Scanner lerString = new Scanner(System.in);
    boolean sair = true;
    ArrayList<Item> estoque = new ArrayList<>();

    private static void escreverDadosNoCSV(String nomeArquivo, ArrayList<Item> estoque) {
        try (BufferedWriter escreve = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Escrever cabeçalho
            escreve.write("Codigo;Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
            // Escrever dados
            for (Item item : estoque) {
                escreve.write(item.getCodigo() + ";" + item.getCategoria() + ";" + item.getNomeProduto() + ";"
                        + item.getValor() + ";" + item.getQuantidade() + ";" + item.getQuantidadeMinima() + "\n");
            }
            // Escrever o flush
            escreve.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Add Item
    public boolean adicionaItemSolicitado (){
        try{
            //Verificar a existencia do arquivo
            boolean arquivoExiste = new File(Arquivo).exists();

            //Abrir o escritor do arquivo e validar se existe
            FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1,true);
            if (!arquivoExiste){
                escreve.write("Codigo;Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
            }
            System.out.println("Informe o Código: ");
            int codigo = lerInt.nextInt();
            boolean codigoNovo = false;

            //verifica se produto está no catálogo
            while (!codigoNovo) {
                Item item = new Item();

                for (Item estoqueItem : estoque) {
                    if (estoqueItem.getCodigo() == codigo) {
                        System.out.println("Informe a quantidade a adicionar do produto " + estoqueItem.getNomeProduto() + " : ");
                        int quantidade = lerInt.nextInt();
                        estoqueItem.setQuantidade(estoqueItem.getQuantidade() + quantidade);
                        System.out.println("Adicionada a quantidade do produto ao estoque com sucesso!! ");

                        break;
                    }

                }
                // Escrever dados atualizados de volta no arquivo CSV
                escreverDadosNoCSV("Itens.csv", estoque);
// Mostrar itens do estoque após a atualização
                mostrarItensEstoque();

                codigoNovo = true;
                sair = true;
            }

            item.setCodigo(codigo);

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
    public boolean mostrarItensEstoque (){
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
            System.out.println(estoque.toString().replace("[", "").replace("]"," "));
        return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    //Modificar item

    //excluir item


    //Relatório

}
