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
    ArrayList<Item> estoque = new ArrayList<>();

    public boolean adicionaItensSolicitados (){
    try{
        //Verificar a existencia do arquivo
        FileWriter escreve = getFileWriter();
        ArrayList<Item> estoque = getEstoque();

        boolean sair = true;

        while(sair) {
            System.out.println("Informe o Código: ");
            int codigo_temp = lerInt.nextInt();

            if(estoque != null) {
                for (Item valida : estoque) {
                    if (valida.getCodigo() == codigo_temp) {
                        System.out.println("Código já existe");
                        System.out.println(valida.toString());
                        sair = false;
                        estoque = null;
                        return false;
                    } else {
                        item.setCodigo(codigo_temp);
                        sair = false;
                    }
                }
            }
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
            System.out.println("Informe o Nome do Produto: ");
            item.setNomeProduto(lerString.nextLine());
            //lerInt.nextLine();
            System.out.println("Informe o Valor: ");
            item.setValor(lerInt.nextDouble());
            System.out.println("Informe Quantidade:");
            item.setQuantidade(lerInt.nextInt());
            System.out.println("Informe Quantidade Minima: ");
            item.setQuantidadeMinima(lerInt.nextInt());
            System.out.println("Finalizado");

            //Escrever o produto na tabela
            escreve.write(item.getCodigo()+";"+ item.getCategoria()+";"+ item.getNomeProduto()+";"+ item.getValor()+";"+ item.getQuantidade()+";"+ item.getQuantidadeMinima()+"\n");
        }

        //Escreve o flush
        escreve.flush();

        //Fecha o escritor
        escreve.close();
        return true;

    }catch (IOException e){
        e.printStackTrace();
    }
    return false;
    }

    private FileWriter getFileWriter() throws IOException {
        boolean arquivoExiste = new File(Arquivo).exists();

        //Abrir o escritor do arquivo e validar se existe
        FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1,true);
        if (!arquivoExiste){
            escreve.write("Codigo;Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
        }
        return escreve;
    }

    //Mostrar itens da Lista - Opção 4
    public String mostrarItensEstoque (){

          return getEstoque().toString().replace("[", "").replace("]"," ");
    }

    public ArrayList<Item> getEstoque() {
        try (BufferedReader br = new BufferedReader(new FileReader(Arquivo))) {
            br.readLine(); // Ignorando a primeira linha (cabeçalho)
            String itensDaLista = br.readLine();
            while (itensDaLista != null) {
                String[] fields = itensDaLista.split(";");

                if (fields.length >= 6) { // Verifica se há pelo menos 6 campos
                    int codigo = 0;
                    try {
                        codigo = Integer.parseInt(fields[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter código para inteiro: " + e.getMessage());
                        // Lide com o erro, se necessário
                    }

                    Categoria categoria = Categoria.valueOf(fields[1].toUpperCase());
                    String nomeProduto = fields[2];

                    double valor = 0.0;
                    try {
                        valor = Double.parseDouble(fields[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter valor para double: " + e.getMessage());
                        // Lide com o erro, se necessário
                    }

                    int quantidade = 0;
                    try {
                        quantidade = Integer.parseInt(fields[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter quantidade para inteiro: " + e.getMessage());
                        // Lide com o erro, se necessário
                    }

                    int quantidadeMinima = 0;
                    try {
                        quantidadeMinima = Integer.parseInt(fields[5]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter quantidade mínima para inteiro: " + e.getMessage());
                        // Lide com o erro, se necessário
                    }

                    estoque.add(new Item(codigo, categoria, nomeProduto, valor, quantidade, quantidadeMinima));
                }

                itensDaLista = br.readLine();
            }
            return estoque;
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
        return null;
    }


    /*public ArrayList<Item> getEstoque (){
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
    }*/

   /* public boolean codigoExisteNoEstoque(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(Arquivo))) {
            br.readLine(); // Ignorando a primeira linha (cabeçalho)
            String itensDaLista = br.readLine();
            while (itensDaLista != null) {
                String[] fields = itensDaLista.split(";");
                int codigoExistente = Integer.parseInt(fields[0]);

                if (codigoExistente == codigo) {
                    return true; // O código já existe no estoque
                }

                itensDaLista = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false; // O código não foi encontrado no estoque
    }*/

    //Modificar item

    //excluir item


    //Relatório


}
