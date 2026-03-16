/**
 * Clase principal encargada de interpretar el script de Bitcoin
 * Implementa la lógica de control de flujo (OP_IF, OP_NOTIF, OP_ELSE, OP_ENDIF) y la delega la ejecución de opcodes
 * Utiliza una pila para almacenar datos y otra para controlar la ejecución de los bloques condicionales
 */

import java.util.Arrays;
import java.util.List;

public class Interprete {
    private Stack<byte[]> stack;
    private Opcodes<byte[]> vm;
    private Stack<Boolean> executionStack;
    private boolean trace = false;
    private String error;

    public Interprete() {
        this.stack = new Stack<>();
        this.vm = new Opcodes<>(stack);
        this.executionStack = new Stack<>();

        executionStack.push(true);
    }

    /**
     * Ejecuta un script de Bitcoin compuesto por una secuencia de instrucciones
     * dado como cadena de texto separada por espacios. El script se procesa token por token utilizando 
     * una pila interna y el conjunto de operaciones definidas en Opcodes.
     * 
     * @param script cadena de texto que representa el script a ejecutar
     * Cada instrucción debe de estar seaparada por espacios
     * @return true si el script finaliza correctamente y el resultado en el tope de la vila es verdadero
     * False si ocurre algún error durante la ejecución o el resultado final es falso
     * 
     * @pre script no es nulo ni vacío
     *      contiene instrucciones válidas según el conjunto de opcodes definidos o valores hexadecimales
     * @post la pila contiene el resultado final de la ejecución del script 
     *       si se da algún error, el atributo error contiene el mensaje correspondiente
     */
    public boolean execute(String script) {

        stack.clear(); // Limpiamos la pila antes de empezar
        executionStack.clear();
        executionStack.push(true);

        // Convertimos el Array a Lista para poder usar .get(i) y manejar índices
        String[] tokensArray = script.trim().split("\\s+");
        List<String> tokens = Arrays.asList(tokensArray);

        int i = 0;
        error = "";

        try {
            while (i < tokens.size()) {
                String token = tokens.get(i);
                boolean shouldExecute = shouldExecute();

                // --- CONTROL DE FLUJO ---
                if (token.equals("OP_IF")) {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    byte[] condition = stack.pop();
                    boolean cond = condition.length > 0 && !(condition.length == 1 && condition[0] == 0);
                    executionStack.push(cond);

                    if (trace) {
                        if (cond) {
                            System.out.println("OP_IF -> condición verdadera");
                        } else {
                            System.out.println("OP_IF -> condición falsa");
                        }
                    }

                    i++;
                    continue;
                }

                else if (token.equals("OP_NOTIF")) {
                    if (stack.isEmpty())
                        return false;
                    byte[] condition = stack.pop();
                    boolean cond = condition.length > 0 && !(condition.length == 1 && condition[0] == 0);
                    executionStack.push(!cond);
                    if (trace) {
                        if (!cond) {
                            System.out.println("OP_NOTIF -> condición verdadera (ejecutando bloque)");
                        } else {
                            System.out.println("OP_NOTIF -> condición falsa (saltando bloque)");
                        }
                    }
                    
                    i++;
                    continue;
                }

                else if (token.equals("OP_ELSE")) {
                    if (executionStack.isEmpty()) {
                        return false;
                    }

                    boolean current = executionStack.pop();
                    executionStack.push(!current);

                    if (trace) {
                        if (!current) {
                            System.out.println("OP_ELSE -> ejecutando bloque ELSE");
                        } else {
                            System.out.println("OP_ELSE -> saltando bloque ELSE");
                        }
                    }

                    i++;
                    continue;
                }

                else if (token.equals("OP_ENDIF")) {
                    if (executionStack.isEmpty()) {
                        return false;
                    }

                    executionStack.pop();

                    if (trace) {
                        System.out.println("OP_ENDIF -> fin del bloque IF");
                    }

                    i++;
                    continue;
                }

                else if (!shouldExecute) {
                    i++; //si no se ejecuta el bloque solo se avanza al siguiente token
                    continue;
                }

                // --- 1. LÓGICA PUSHDATA ---
                if (token.equals("PUSHDATA")) {
                    if (i + 1 >= tokens.size())
                        return false;

                    String sizeHex = tokens.get(i + 1);
                    int bytesToRead = Integer.parseInt(sizeHex, 16);
                    byte[] data = new byte[bytesToRead];

                    for (int j = 0; j < bytesToRead; j++) {
                        if (i + 2 + j < tokens.size()) {
                            String dataToken = tokens.get(i + 2 + j);
                            data[j] = (byte) Integer.parseInt(dataToken, 16);
                        }
                    }
                    stack.push(data);

                    // Saltamos: Opcode + Tamaño + Datos
                    i += (2 + bytesToRead);
                }

                // --- 2. HEXADECIMALES ---
                else if (isHex(token)) {
                    stack.push(hexToBytes(token));
                    i++;
                }

                // --- 3. OPCODES ---
                else if (vm.execute(token)) {
                    // Si execute devuelve true, se ejecutó bien.
                    i++;
                }

                // --- 4. ERROR ---
                else {
                    error = "Instrucción inválida: " + token;
                    return false;
                }
                if (this.trace) {
                    printStack(token);
                }
            }
        } catch (Exception e) {
            error = "Error crítico durante ejecución: " + e.getMessage();
            return false;
        }

        return verificarResultado();
    }

    /**
     * Verifica el resultado final de la ejecución del script
     * Un script se considera válido únicamente si el tope de la pila representa un valor verdadero
     * Un valor se considera falso si:
     * - Es un array de bytes vacío
     * - Es un array de bytes de longitud 1 con el único byte igual a 0
     * @return true si el resultado final es verdadero, false en caso contrario
     * 
     * @pre la ejecución del script ha finalizado
     * @post no se modifica el contenido de la pila
     */
    private boolean verificarResultado() {
        if (stack.isEmpty())
            return false;

        byte[] top = stack.peek();

        if (top.length == 0)
            return false;
        if (top.length == 1 && top[0] == 0)
            return false;

        return true;
    }

    private boolean isHex(String instruction) {
        return instruction.startsWith("<") && instruction.endsWith(">");
    }

    /**
     * Convierte una representación hexadecimal en un arreglo de bytes
     * La cadena de entrada debe de estar delimitada por los símbolos "<>".
     * Se eliminan los delimitadores y se convierte cada par de caracteres en su equivalente byte
     * Si la longitud es impar, se agrega un 0 al inicio para completar el último byte
     * @param instruction cadena de texto que representa un valor hexadecimal, delimitada por "<>"
     * @return un arreglo de bytes correspondiente a la representación hexadecimal dada
     * @throws NumberFormatException si la cadena contiene caracteres no válidos para hexadecimal
     * 
     * @pre instruction no es nulo y debe comenzar y terminar con "<>"
     * @post devuelve un arreglo de bytes con los datos convertidos 
     */
    private byte[] hexToBytes(String instruction) {
        String hex = instruction.substring(1, instruction.length() - 1); // ignora "<>"

        // Corrección de impares
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }

        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return data;
    }

    /**
     * Imprime el contenido actual de la pila junto con el token que se acaba de ejecutar
     * Únicamente se utiliza cuando el modo trace está activado
     * 
     * @param token instrucción ejecutada antes de imprimir el estado de la pila
     * 
     * @pre token no es nulo
     * @post el contenido de la pila no es modificado, únicamente se imprime
     */
    public void printStack(String token){
        System.out.print("Token: " + String.format("%-15s", token));
        System.out.print(" | Stack: [");
        for (int j = 0; j < stack.size(); j++) {
            System.out.print(Arrays.toString(stack.get(j)));
            if (j < stack.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void setTrace(boolean trace){
        this.trace = trace;
    }

    public String getError() {
        return error;
    }

    private boolean shouldExecute() {
        for (int e = 0; e< executionStack.size(); e++) {
            if (!executionStack.get(e)) {
                return false;
            }
        }
        return true;
    }
}