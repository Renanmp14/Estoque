package teste;
import classes.*;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControleEstoqueTest {
    ArrayList<Item> estoque = new ArrayList<>();
    private static final String TEST_FILE_PATH = "./dados/teste.csv";

    @Test
    public void testGetFileWriter() throws IOException {
        // Crie uma instância da sua classe (a que contém o método getFileWriter)
        ControleEstoque suaClasse = new ControleEstoque();  // Substitua 'SuaClasse' pelo nome real da sua classe

        // Chame o método getFileWriter
        FileWriter fileWriter = null;
        try {
            fileWriter = suaClasse.getFileWriter();

            // Verifique se o FileWriter não é nulo
            assertNotNull(fileWriter);

            // Adicione mais verificações conforme necessário
        } finally {
            // Feche o FileWriter após o teste
            if (fileWriter != null) {
                fileWriter.close();
            }

        }
    }
}