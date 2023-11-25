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
            System.out.println("Escolha sua operação:\n1. Adicionar Item no Estoque\n2. Remover item do Estoque\n3. Modificar quantidade do item\n4. Lista de Itens" +
                    "\n5. Relatório de itens abaixo do estoque minímo\n6. Sair do menu");
            escolha = ler.nextInt();
            switch (escolha){
                case 1: //Adiciona Item no Estoque
                    System.out.println();
                    adicionaItensSolicitados();
                    System.out.println();
                    break;
                case 2: //Remover item do Estoque
                    System.out.println("Informe o codigo do item para ser removido: ");
                    int codigoRemocao = ler.nextInt();
                    if (removerItem(codigoRemocao) == false){
                        System.out.println("Código não existente");
                    }
                    break;
                case 3: //Modificar quantidade do item na lista
                    System.out.println("Informe o codigo do item para ser modificado: ");
                    int codigoModificacao = ler.nextInt();
                    System.out.println("Informe a quantidade desejada: ");
                    int quantidade = ler.nextInt();
                    if (modificarItem(codigoModificacao,quantidade) == false){
                        System.out.println("Código não existente");
                    }
                    break;
                case 4: //Mostrar Itens do Estoque
                    System.out.println();
                    System.out.println(mostrarItensEstoque());
                    estoque.clear();
                    System.out.println();
                    break;
                case 5: //Relatório de itens Faltantes do Estoque
                    System.out.println();
                    String resultado = itenEstoqueAbaixo();
                    if(resultado.isEmpty()){
                        System.out.println("Não tem produtos com estoque abaixo do mínimo.");
                    }
                    else{
                        System.out.println(resultado);
                    }
                    estoque.clear();
                    System.out.println();
                    break;
                case 6: // Finaliza operação
                    System.out.println("Operação Finalizada.");
                    sair = false;
                    break;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
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
