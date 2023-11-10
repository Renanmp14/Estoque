import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ItensCSVADM {

    //Caminho do arquivo
    private static String Arquivo = "./dados/Itens.csv";
    private static Itens itens = new Itens();
    static Scanner lerInt = new Scanner(System.in);
    static Scanner lerString = new Scanner(System.in);

    //Escrever em um arquivo com parametros no Main
    /*
    public static void AdicionaItens (Itens it){
        try{
            //Verificar a existencia do arquivo
            boolean arquivoExiste = new File(Arquivo).exists();

            //Abrir o escritor do arquivo e validar se existe
            FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1,true);
            if (!arquivoExiste){
                escreve.write("Código;Categoria;Produto;Valor;Quantidade\n");
            }

            //Escrever o produto na tabela
            escreve.write(it.getCodigo()+";"+it.getCategoria()+";"+it.getProduto()+";"+it.getValor()+";"+it.getQuantdade()+"\n");

            //Escreve o flush
            escreve.flush();

            //Fecha o escritor
            escreve.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    */

    public static void AdicionaItensSolicitados (){
        try{
            //Verificar a existencia do arquivo
            boolean arquivoExiste = new File(Arquivo).exists();

            //Abrir o escritor do arquivo e validar se existe
            FileWriter escreve = new FileWriter(Arquivo, StandardCharsets.ISO_8859_1,true);
            if (!arquivoExiste){
                escreve.write("Código;Categoria;Produto;Valor;Quantidade\n");
            }
            System.out.println("Informe o Código: ");
            itens.setCodigo(lerInt.nextInt());
            System.out.println("Informe a Categoria: ");
            itens.setCategoria(lerInt.nextInt());
            System.out.println("Informe o Produto: ");
            itens.setProduto(lerString.nextLine());
            System.out.println("Informe o Valor: ");
            itens.setValor(lerInt.nextDouble());
            System.out.println("Informe Quantidade:");
            itens.setQuantdade(lerInt.nextInt());
            System.out.println("Finalizado");

            //Escrever o produto na tabela
            escreve.write(itens.getCodigo()+";"+itens.getCategoria()+";"+itens.getProduto()+";"+itens.getValor()+";"+itens.getQuantdade()+"\n");

            //Escreve o flush
            escreve.flush();

            //Fecha o escritor
            escreve.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static Itens getItens() {
        return itens;
    }

    public static void setItens(Itens itens) {
        ItensCSVADM.itens = itens;
    }

    //Modificar item

    //excluir item

    //Mostrar itens da Lista

    //Relatório

}
