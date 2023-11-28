package classes;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ControleEstoque {

    private final String PATH_ARQUIVO = "dados/Itens.csv";
    ArrayList<Item> estoque;

    public ControleEstoque() {
        estoque = new ArrayList<>();

        try {
            FileWriter escreve = getFileWriter();

            if (escreve != null) {
                escreve.close(); // Fechar o FileWriter, pois já escreveu o cabeçalho
            }

            estoque = leituraCSV();
        } catch (IOException e) {
            System.out.println("Erro ao inicializar o Controle de Estoque: " + e.getMessage());
        }
    }

    //Valida a existencia do arquivo no diretório
    private FileWriter getFileWriter() throws IOException {
        boolean arquivoExiste = new File(PATH_ARQUIVO).exists();

        //Abrir o escritor do arquivo e validar se existe
        FileWriter escreve = new FileWriter(PATH_ARQUIVO, StandardCharsets.ISO_8859_1, true);
        if (!arquivoExiste) {
            escreve.write("Codigo;classes.Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
        }
        return escreve;
    }

    public ArrayList<Item> leituraCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ARQUIVO))) {
            br.readLine(); // Ignorando a primeira linha (cabeçalho)
            String itensDaLista = br.readLine();
            while (itensDaLista != null) {
                String[] fields = itensDaLista.split(";");

                if (fields.length >= 6) { // Verifica se há pelo menos 6 campos
                    try {
                        int codigo = Integer.parseInt(fields[0]);
                        Categoria categoria = Categoria.valueOf(fields[1].toUpperCase());
                        String nomeProduto = fields[2];
                        double valor = Double.parseDouble(fields[3]);
                        int quantidade = Integer.parseInt(fields[4]);
                        int quantidadeMinima = Integer.parseInt(fields[5]);

                        estoque.add(new Item(codigo, categoria, nomeProduto, valor, quantidade, quantidadeMinima));
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter valor numérico: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao converter categoria: " + e.getMessage());
                    }
                }

                itensDaLista = br.readLine();
            }
            return estoque;
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
        return null;
    }

    public void apresentaEstoque() {
        System.out.printf("%-15s%-15s%-35s%-13s%-15s%-15s%n",
                "CÓDIGO", "CATEGORIA", "NOME DO PRODUTO",
                "VALOR", "QUANTIDADE", "ESTOQUE MINIMO");
        System.out.println("----------------------------------------------" +
                "-------------------------------------------------------------");
        if (estoque != null) {
            for (Item item : estoque) {
                System.out.printf("%-15d%-15s%-35s%-13.2f%-15d%-10d%n", item.getCodigo(),
                        item.getCategoria().toString(),
                        item.getNomeProduto(),
                        item.getValor(),
                        item.getQuantidade(),
                        item.getQuantidadeMinima());
            }
        }
        System.out.println("---------------------------------------------------" +
                "--------------------------------------------------------");
    }

    //Verifica a existencia do código
    public Item vericaCodigo(int codigo) {
        if (estoque != null) {
            for (Item itemDaLista : estoque) {
                if (itemDaLista.getCodigo() == codigo) {
                    return itemDaLista;
                }
            }
        }
        return null;
    }

    public void addItem(Item item){
        estoque.add(item);
    }

    public boolean removeItem(int codigo){
        return estoque.remove(vericaCodigo(codigo));
    }

    public boolean modificarItem(int codigo, int quantidade) {
        for (Item item : estoque) {
            if (item.getCodigo() == codigo) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                if (item.getQuantidade() <= 0)
                    item.setQuantidade(0);

            }
            return true;
        }
        return false;
    }

    public void itensAbaixoDoEstoqueMinimo() {
        boolean temProdutosAbaixoDoMinimo = false;

        if (estoque != null) {
            System.out.printf("%-15s%-15s%-35s%-13s%-15s%-15s%n",
                    "CÓDIGO", "CATEGORIA", "NOME DO PRODUTO",
                    "VALOR", "QUANTIDADE", "ESTOQUE MINIMO");
            System.out.println("----------------------------------------------" +
                    "-------------------------------------------------------------");

            for (Item item : estoque) {
                if (item.getQuantidadeMinima() > item.getQuantidade()) {
                    temProdutosAbaixoDoMinimo = true;

                    System.out.printf("%-15d%-15s%-35s%-13.2f%-15d%-10d%n",
                            item.getCodigo(), item.getCategoria().toString(),
                            item.getNomeProduto(), item.getValor(),
                            item.getQuantidade(),
                            item.getQuantidadeMinima());
                }
            }
        }
        if (!temProdutosAbaixoDoMinimo) {
            System.out.println("Não tem produtos com estoque abaixo do mínimo.");
        }
        System.out.println("----------------------------------------------" +
                "-------------------------------------------------------------");
    }

    public void salvarAlteracoes() throws IOException {
        if (estoque != null) {
            try (BufferedWriter escreve = new BufferedWriter(new FileWriter(PATH_ARQUIVO, StandardCharsets.ISO_8859_1))) {
                escreve.write("Codigo;classes.Categoria;Produto;Valor;Quantidade;QuantidadeMinima\n");
                for (Item item : estoque) {
                    escreve.write(item.getCodigo() + ";" + item.getCategoria() + ";" +
                            item.getNomeProduto() + ";" + item.getValor() + ";" +
                            item.getQuantidade() + ";" + item.getQuantidadeMinima() + "\n");
                }
            }
        } else {
            throw new IOException("O estoque está nulo. Não é possível salvar as alterações.");
        }
    }
}