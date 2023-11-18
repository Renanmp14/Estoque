import java.util.Scanner;
public class ContaADM extends ItensCSV{

    private String login = "admin";
    private String senha = "admin";
    private boolean sair = true;
    Scanner ler = new Scanner(System.in);
    public boolean menuADM (){
        System.out.println("Bem Víndo ao Ambiente do Administrador");
        while (sair) {
            System.out.println("Escolha sua operação:\n1. Adicionar Item no Estoque\n2. Remover item do Estoque\n3. Modificar quantidade do item\n4. Lista de Itens" +
                    "\n5. Relatório de itens abaixo do estoque minímo\n6. Sair do menu");
            int escolha = ler.nextInt();
            switch (escolha){
                case 1: //Adiciona Item no Estoque
                    adicionaItensSolicitados();
                    break;
                case 2: //Remover item do Estoque
                    System.out.println("Sem Função disponivel ainda");
                    break;
                case 3: //Modificar quantidade do item na lista
                    System.out.println("Sem Função disponivel ainda");
                    break;
                case 4: //Mostrar Itens do Estoque
                    mostrarItensEstoque();
                    break;
                case 5: //Relatório de itens Faltantes do Estoque
                    System.out.println("Sem Função disponivel ainda");
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
        return true;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
