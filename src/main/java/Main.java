import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        String datos = "";
        Opcodes<byte[]> opcodes = new Opcodes();
        try {
            datos = FileHelper.readFile("src/main/resources/data/Datos.txt");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        String[] Instructions = datos.split(" ");
    }
}