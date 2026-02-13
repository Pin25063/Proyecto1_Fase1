import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            String datos = FileHelper.readFile("src/main/resources/data/Datos.txt");
            String[] instructions = datos.trim().split("\\s+");
            Interprete interprete = new Interprete();

            for (String operation : instructions) {
                interprete.execute(operation);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}