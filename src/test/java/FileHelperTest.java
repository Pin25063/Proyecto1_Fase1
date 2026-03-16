import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelperTest {

    @Test
    public void doesFileHelperWork() throws IOException {
        List<String> scripts = FileHelper.readFile("src/main/resources/data/Datos.txt");
        assertNotNull(scripts);
        assertFalse(scripts.isEmpty());

        for (String script : scripts) {
            assertNotNull(script);
            assertFalse(script.trim().isEmpty());
        }
    }

    @Test(expected = IOException.class)
    public void FileDoesNotExist() throws IOException {
        FileHelper.readFile("src/main/resources/data/ArchivoInexistente.txt");
    }
}