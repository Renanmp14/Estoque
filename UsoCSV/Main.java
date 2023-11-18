import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ContaADM adm = new ContaADM();
        boolean finalizar = true;
        Scanner ler = new Scanner(System.in);
        Scanner lerLogin = new Scanner(System.in);
        Scanner lerSenha = new Scanner(System.in);

        while (finalizar) {
            System.out.println("---Software de Estoque---");
            System.out.println("Escolha o método de entrada:\n1. Perfil ADM:\n2. Perfil Cliente\n3. Finalizar sessão");
            int acesso = ler.nextInt();
            switch (acesso) {
                case 1:
                    System.out.println("Informe o Login:");
                    String acLogin = lerLogin.nextLine();
                    System.out.println("Informe a Senha:");
                    String acSenha = lerSenha.nextLine();

                    if (acLogin.equals(adm.getLogin()) && acSenha.equals(adm.getSenha())) {
                        adm.menuADM();
                    } else {
                        System.out.println("Credenciais Erradas.");
                    }
                    break;
                case 2:
                    System.out.println("Em manutenção.");
                    break;
                case 3:
                    System.out.println("Muito obrigado pelo acesso, até a Proxíma.");
                    finalizar = false;
                    break;
                default:
                    System.out.println("Opções Invalidas. Tente Novamente.");
                    break;
            }
        }
    }
}
