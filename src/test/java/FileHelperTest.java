import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class FileHelperTest {

    @Test
    public void doesFileHelperWork() throws IOException {
        String datos = FileHelper.readFile("src/main/resources/data/Datos.txt");
        String[] instructions = datos.split(" ");
        assertNotNull(instructions);
        assertTrue(instructions.length > 0);
    }
}