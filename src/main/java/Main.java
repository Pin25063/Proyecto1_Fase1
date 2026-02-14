import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> scripts = FileHelper.readFile("src/main/resources/data/Datos.txt");
            Interprete interprete = new Interprete();

            for (String script : scripts) {
                if (!script.trim().isEmpty()) {
                    // validación final temporal
                    if (interprete.execute(script)) {
                        System.out.println("Script válido");
                    } else {
                        System.out.println("Script inválido");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}