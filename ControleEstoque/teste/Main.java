package teste;
import classes.Categoria;
import classes.ControleEstoque;
import classes.Item;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ControleEstoque controleEstoque = new ControleEstoque();
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;
        int codigo = 0;

        while (opcao != 6) {
            System.out.println("--- Bem vindo ao controle de estoque ---");
            opcao = 0;
            System.out.println("Escolha sua operação:\n" +
                    "1. Adicionar Item no Estoque\n" +
                    "2. Remover item do Estoque\n" +
                    "3. Modificar quantidade do item\n" +
                    "4. Lista de Itens\n" +
                    "5. Relatório de itens abaixo do estoque mínimo\n" +
                    "6. Sair do menu/Salvar alterações");
            opcao = leitura.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Informe o código do produto para adicionar:");
                    codigo = leitura.nextInt();
                    if (controleEstoque.vericaCodigo(codigo)!=null) {
                        System.out.println("Código inválido, produto já existente ! ! !");
                    }else{
                        controleEstoque.addItem(questionarioItem(codigo));
                    }
                    break;
                case 2:
                    System.out.println("Informe o código do produto para remover:");
                    codigo = leitura.nextInt();
                    if (controleEstoque.vericaCodigo(codigo)==null) {
                        System.out.println("Código inválido, produto inexistente ! ! ! ");
                    }else{
                        controleEstoque.removeItem(codigo);
                        System.out.println("Produto removido");
                    }
                    break;
                case 3:
                    System.out.println("Informe o código do produto para modificar a quantidade:");
                    codigo = leitura.nextInt();
                    leitura.nextLine();
                    if (controleEstoque.vericaCodigo(codigo)==null) {
                        System.out.println("Código inválido, produto inexistente ! ! ! ");
                    }else{
                        System.out.println("Informe a quantidade desejada:");
                        System.out.println("OBS: Números positivos somam e Números negativos subtraem");
                        int quantidade = leitura.nextInt();
                        controleEstoque.modificarItem(codigo,quantidade);
                        System.out.println("Produto alterado ! ! !");
                    }
                    break;
                case 4:
                    System.out.println();
                    controleEstoque.apresentaEstoque();
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    controleEstoque.itensAbaixoDoEstoqueMinimo();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Salvando informações no arquivo . . .");
                    try {
                        controleEstoque.salvarAlteracoes();
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar arquivo, favor tente novamente ! ! !");
                        System.out.println(e + e.getMessage());
                        throw new RuntimeException(e);
                    }
                    System.out.println("Saindo do menu.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println();
                    break;
            }
        }
    }

    public static Item questionarioItem(int codigo) {
        Item item = new Item();
        Scanner leitura = new Scanner(System.in);

        boolean sair = false;

        while (!sair) {
            try {

                item.setCodigo(codigo);

                System.out.println("Informe a Categoria: \n1. Tênis\n2. Camisa\n3. Calça\n4. Bermuda\n5. Chinelo\n6. Boné");
                int categoria = leitura.nextInt();
                leitura.nextLine();

                item.setCategoria(Categoria.values()[categoria - 1]);

                System.out.println("Informe o Nome do Produto: ");
                item.setNomeProduto(leitura.nextLine());

                System.out.println("Informe o Valor: ");
                item.setValor(leitura.nextDouble());

                System.out.println("Informe a Quantidade:");
                item.setQuantidade(leitura.nextInt());

                System.out.println("Informe a Quantidade Mínima: ");
                item.setQuantidadeMinima(leitura.nextInt());
                leitura.nextLine();

                sair = true; // Define como true para sair do loop

                System.out.println("Produto adicionado");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção Inválida. Tente Novamente.");
            }
        }

        return item;
    }
}