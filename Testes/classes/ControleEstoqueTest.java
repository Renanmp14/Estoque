package classes;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControleEstoqueTest {
    private static final String TEST_FILE_PATH = "./dados/teste.csv";
    ControleEstoque estoque = new ControleEstoque();

        @Test
        void testGetFileWriter() throws IOException {

            FileWriter fileWriter = null;
            try {
                fileWriter = estoque.getFileWriter();

                assertNotNull(fileWriter);

            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }

            }
        }
    @Test
    void testVerificaCodigo() {
    assertTrue(estoque.vericaCodigo(3434) !=null);
    assertFalse(estoque.vericaCodigo(9000) != null);
    }

    @Test
    void testRemoveItem() {
        assertTrue(estoque.vericaCodigo(8989) !=null);
        estoque.removeItem(8989)  ;
        assertFalse(estoque.vericaCodigo(8989) !=null);
    }
    }
