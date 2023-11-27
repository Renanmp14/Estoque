package classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ControleEstoque {

    //Caminho do arquivo
    private String Arquivo = "dados/Itens.csv";
    private Item item = new Item();
    Scanner lerInt = new Scanner(System.in);
    Scanner lerString = new Scanner(System.in);
    ArrayList<Item> estoque = new ArrayList<>();

    //Adicionar item no Estoque - Opção 1
    public boolean adicionaItensSolicitados() {
        try {
            //Verificar a existencia do arquivo
            FileWriter escreve = getFileWriter();
            boolean sair = true;
            while (sair) {
                System.out.println("Informe o Código: ");
                int codigo_temp = lerInt.nextInt();
                if (vericaCódigo(codigo_temp) != null) {
                    System.out.println("Código já existe");
                    sair = false;
                    return false;
                } else {
                    item.setCodigo(codigo_temp);
                    sair = false;
                }

                System.out.println("Informe a classes.Categoria: \n1. Tênis\n2. Camisa\n3. Calça\n4. Bermuda\n5. Chinelo\n6. Bonê");
                int categoria = lerInt.nextInt();
                switch (categoria) {
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
                escreve.write(item.getCodigo() + ";" + item.getCategoria() + ";" + item.getNomeProduto() + ";" + item.getValor() + ";" + item.getQuantidade() + ";" + item.getQuantidadeMinima() + "\n");
            }


            //Escreve o flush
            escreve.flush();

            //Fecha o escritor
            escreve.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    //Remover classes.Item no Estoque - Opção 2
    public boolean removerItem(int codigo) {
        try {
            String path = "dados/Itens.csv";
            File arquivoOriginal = new File("dados/Itens.csv");
            File arquivoTemporario = new File("ControleEstoque/Itens.csv");

            if (vericaCódigo(codigo) == null) {
                return false;
            }

            ArrayList<Item> estoqueTemporario = getEstoque();

            BufferedWriter escreve = new BufferedWriter(new FileWriter(arquivoTemporario, StandardCharsets.ISO_8859_1));

            escreve.write("Codigo;classes.Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");

            for (Item item : estoqueTemporario) {
                if (item.getCodigo() != codigo) {
                    // Escrever o item no arquivo temporário, exceto o que está sendo removido
                    escreve.write(item.getCodigo() + ";" + item.getCategoria() + ";" +
                            item.getNomeProduto() + ";" + item.getValor() + ";" +
                            item.getQuantidade() + ";" + item.getQuantidadeMinima() + "\n");
                }
            }

            escreve.close();

            // Excluir o arquivo original
            arquivoOriginal.delete();

            // Renomear o arquivo temporário para o nome do arquivo original
            arquivoTemporario.renameTo(new File(path));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Modificar quantidade do Estoque - Opção 3
    public boolean modificarItem(int codigo, int quantidade) {
        try {
            String path = "dados/Itens.csv";
            File arquivoOriginal = new File("dados/Itens.csv");
            File arquivoTemporario = new File("ControleEstoque/Itens.csv");
            if (vericaCódigo(codigo) == null){
                return false;
            }
            ArrayList<Item> estoqueTemporario = getEstoque();
            BufferedWriter escreve = new BufferedWriter(new FileWriter(arquivoTemporario, StandardCharsets.ISO_8859_1));
            escreve.write("Codigo;classes.Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");

            for (Item item : estoqueTemporario) {
                if (item.getCodigo() == codigo) {
                    item.setQuantidade(item.getQuantidade() + quantidade);
                }
                if (item.getQuantidade() <= 0 ){
                    item.setQuantidade(0);
                }
                // Escrever o item atualizado no arquivo temporário
                escreve.write(item.getCodigo() + ";" + item.getCategoria() + ";" +
                        item.getNomeProduto() + ";" + item.getValor() + ";" +
                        item.getQuantidade() + ";" + item.getQuantidadeMinima() + "\n");
            }

            escreve.close();

            // Excluir o arquivo original
            arquivoOriginal.delete();

            arquivoTemporario.renameTo(new File(path));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Mostrar itens da Lista - Opção 4
    public void mostrarItensEstoque() {
        ArrayList<Item> estoque = getEstoque();
        System.out.printf("%-15s%-15s%-35s%-13s%-15s%-15s%n",
                "CÓDIGO", "CATEGORIA", "NOME DO PRODUTO",
                "VALOR", "QUANTIDADE", "ESTOQUE MINIMO");
        System.out.println("----------------------------------------------" +
                "-------------------------------------------------------------");
        if (estoque != null) {
            for (Item estoque_temp : estoque) {
                System.out.printf("%-15d%-15s%-35s%-13.2f%-15d%-10d%n", estoque_temp.getCodigo(),
                        estoque_temp.getCategoria().toString(),
                        estoque_temp.getNomeProduto(),
                        estoque_temp.getValor(),
                        estoque_temp.getQuantidade(),
                        estoque_temp.getQuantidadeMinima());
            }
        }
        System.out.println("---------------------------------------------------" +
                "--------------------------------------------------------");
    }
    //Mostrar itens com estoque abaixo - Opção 5
    public void itensEstoqueAbaixo() {
        ArrayList<Item> estoque = getEstoque();
        boolean verificacao = false;
        if (estoque != null){
            for (Item estoque_temp : estoque) {
                if (estoque_temp.getQuantidadeMinima() > estoque_temp.getQuantidade()) {
                    verificacao = true;
                }
            }
        }
        if (!verificacao)
            System.out.println("Não tem produtos com estoque abaixo do mínimo.");
        else {
            System.out.printf("%-15s%-15s%-35s%-13s%-15s%-15s%n",
                    "CÓDIGO", "CATEGORIA", "NOME DO PRODUTO",
                    "VALOR", "QUANTIDADE", "ESTOQUE MINIMO");
            System.out.println("----------------------------------------------" +
                    "-------------------------------------------------------------");
            for (Item estoque_temp : estoque) {
                if (estoque_temp.getQuantidadeMinima() > estoque_temp.getQuantidade()) {
                    System.out.printf("%-15d%-15s%-35s%-13.2f%-15d%-10d%n",
                            estoque_temp.getCodigo(), estoque_temp.getCategoria().toString(),
                            estoque_temp.getNomeProduto(), estoque_temp.getValor(),
                            estoque_temp.getQuantidade(),
                            estoque_temp.getQuantidadeMinima());
                }
            }
            System.out.println("----------------------------------------------" +
                    "-------------------------------------------------------------");
        }
    }

    //Valida a existencia do arquivo no diretório
    private FileWriter getFileWriter() throws IOException {
        boolean arquivoExiste = new File(Arquivo).exists();

        //Abrir o escritor do arquivo e validar se existe
        FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1, true);
        if (!arquivoExiste) {
            escreve.write("Codigo;classes.Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
        }
        return escreve;
    }
    //Cria o estoque Temporario
    public ArrayList<Item> getEstoque() {
        estoque.clear();
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
                    }

                    Categoria categoria = Categoria.valueOf(fields[1].toUpperCase());
                    String nomeProduto = fields[2];

                    double valor = 0.0;
                    try {
                        valor = Double.parseDouble(fields[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter valor para double: " + e.getMessage());
                    }

                    int quantidade = 0;
                    try {
                        quantidade = Integer.parseInt(fields[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter quantidade para inteiro: " + e.getMessage());
                    }

                    int quantidadeMinima = 0;
                    try {
                        quantidadeMinima = Integer.parseInt(fields[5]);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter quantidade mínima para inteiro: " + e.getMessage());
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
    //Verifica a existencia do código
    public Item vericaCódigo(int codigo) {
        ArrayList<Item> estoque = getEstoque();
        if (estoque != null) {
            for (Item estoque_temp : estoque) {
                if (estoque_temp.getCodigo() == codigo) {
                    estoque.clear();
                    return estoque_temp;
                }
            }
        }
        return null;
    }

}




