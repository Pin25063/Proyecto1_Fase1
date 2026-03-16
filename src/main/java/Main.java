/**
 * Clase principal del programa
 * 
 * Permite ejecutar scripts desde un archivo de texto
 * 
 * El usuario puede activar el modo trace para visualizar
 * la evolución de la pila después de cada instrucción ejecutada
 */

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Desea activar el modo trace? (S/N): ");
        String option = scanner.nextLine();
        option = option.toLowerCase();
        boolean trace = switch (option) {
            case "s" -> true;
            default -> false;
        };
        try {
            List<String> scripts = FileHelper.readFile("src/main/resources/data/Datos.txt");
            Interprete interprete = new Interprete();
            interprete.setTrace(trace);
            for (String script : scripts) {
                System.out.println();
                System.out.println("Ejecutando script: " + script);
                if (!script.trim().isEmpty()) {
                    // validación final temporal
                    if (interprete.execute(script)) {
                        System.out.println("Script válido");
                    } else {
                        if (interprete.getError().isEmpty()) {
                            System.out.println("Script inválido");
                        } else {
                            System.out.println("Script inválido (" + interprete.getError() + ")");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}