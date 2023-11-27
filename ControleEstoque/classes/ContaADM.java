package classes;

import java.util.Scanner;
public class ContaADM extends ItensCSV{

    private String login = "admin";
    private String senha = "admin";
    private boolean sair = true;
    Scanner ler = new Scanner(System.in);
    int escolha = 0;
    public void menuADM (){
        System.out.println("Bem Víndo ao Ambiente do Administrador");
        while (sair) {
            System.out.println("Escolha sua operação:\n1. Adicionar classes.Item no Estoque\n2. Remover item do Estoque\n3. Modificar quantidade do item\n4. Lista de Itens" +
                    "\n5. Relatório de itens abaixo do estoque minímo\n6. Sair do menu");
            escolha = ler.nextInt();
            switch (escolha){
                case 1: //Adiciona classes.Item no Estoque
                    System.out.println();
                    adicionaItensSolicitados();
                    System.out.println();
                    break;
                case 2: //Remover item do Estoque
                    System.out.println();
                    System.out.println("Informe o codigo do item para ser removido: ");
                    int codigoRemocao = ler.nextInt();
                    if (removerItem(codigoRemocao) == false){
                        System.out.println("\nCódigo não existente\n");
                    }
                    else{
                        System.out.println("\nclasses.Item removido com sucesso\n");
                    }
                    break;
                case 3: //Modificar quantidade do item na lista
                    System.out.println();
                    System.out.println("Informe o codigo do item para ser modificado: ");
                    int codigoModificacao = ler.nextInt();
                    System.out.println("Informe a quantidade desejada: ");
                    System.out.println("*OBS: Números Positivos Somam e Números Negativos Subtraem*");
                    int quantidade = ler.nextInt();
                    if (modificarItem(codigoModificacao,quantidade) == false){
                        System.out.println("\nCódigo não existente\n");
                    }
                    else{
                        System.out.println("\nclasses.Item modificado com sucesso\n");
                    }
                    break;
                case 4: //Mostrar Itens do Estoque
                    System.out.println();
                    mostrarItensEstoque();
                    System.out.println();
                    break;
                case 5: //Relatório de itens Faltantes do Estoque
                    System.out.println();
                    itensEstoqueAbaixo();
                    System.out.println();
                    break;
                case 6: // Finaliza operação
                    System.out.println();
                    System.out.println("Operação Finalizada.");
                    System.out.println();
                    sair = false;
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção invalida. Tente novamente.");
                    System.out.println();
                    break;
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
