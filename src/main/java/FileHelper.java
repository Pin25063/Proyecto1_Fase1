import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Clase auxiliar encargada de leer archivos de texto
 * Devuelve una lista de cadenas, en donde cada una representa una línea del
 * archivo
 */

public class FileHelper {

    /**
     * Lee un archivo de texto y devuelve una lista de cadenas de sus líneas
     * 
     * @param filePath ruta del archivo a leer
     * @return una lista de cadenas, cada una representando una línea del archivo
     * @throws IOException si ocurre un error al leer el archivo o si este no existe
     * 
     * @pre filePath no es nulo ni vacío, y el archivo existe en la ruta
     *      especificada
     * @post se devuelve una lista de cadenas con las líneas del archivo
     *       se lanza una excepción si ocurre un error durante la lectura
     *       la lista puede estar vacía si el archivo no tiene líneas
     */
    public static List<String> readFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}
