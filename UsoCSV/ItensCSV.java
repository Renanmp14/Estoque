import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ItensCSV {

    //Caminho do arquivo
    private String Arquivo = "./dados/Itens.csv";
    private Itens itens = new Itens();
    Scanner lerInt = new Scanner(System.in);
    Scanner lerString = new Scanner(System.in);
    boolean sair = true;
    ArrayList<Itens> estoque = new ArrayList<>();

    //Add Item
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
            itens.setCodigo(lerInt.nextInt());

            while(sair) {
                System.out.println("Informe a Categoria: \n1. Tênis\n2. Camisa\n3. Calça\n4. Bermuda\n5. Chinelo\n6. Bonê");
                int categoria = lerInt.nextInt();
                switch (categoria){
                    case 1:
                        itens.setCategoria(Categoria.TENIS);
                        sair = false;
                        break;
                    case 2:
                        itens.setCategoria(Categoria.CAMISA);
                        sair = false;
                        break;
                    case 3:
                        itens.setCategoria(Categoria.CALCA);
                        sair = false;
                        break;
                    case 4:
                        itens.setCategoria(Categoria.BERMUDA);
                        sair = false;
                        break;
                    case 5:
                        itens.setCategoria(Categoria.CHINELO);
                        sair = false;
                        break;
                    case 6:
                        itens.setCategoria(Categoria.BONE);
                        sair = false;
                        break;
                    default:
                        System.out.println("Opção Invalida. Tente Novamente.");
                        break;
                }
            }
            System.out.println("Informe o Produto: ");
            itens.setNomeProduto(lerString.nextLine());
            System.out.println("Informe o Valor: ");
            itens.setValor(lerInt.nextDouble());
            System.out.println("Informe Quantidade:");
            itens.setQuantidade(lerInt.nextInt());
            System.out.println("Informe Quantidade Minima: ");
            itens.setQuantidadeMinima(lerInt.nextInt());
            System.out.println("Finalizado");

            //Escrever o produto na tabela
            escreve.write(itens.getCodigo()+";"+itens.getCategoria()+";"+itens.getNomeProduto()+";"+itens.getValor()+";"+itens.getQuantidade()+";"+itens.getQuantidadeMinima()+"\n");

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
                estoque.add(new Itens(codigo, categoria, nomeProduto, valor,quantidade,quantidadeMinima));

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
