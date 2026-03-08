import java.util.Arrays;
import java.util.List;

public class Interprete {
    private Stack<byte[]> stack;
    private Opcodes<byte[]> vm;
    private Stack<Boolean> executionStack;

    public Interprete() {
        this.stack = new Stack<>();
        this.vm = new Opcodes<>(stack);
        this.executionStack = new Stack<>();

        executionStack.push(true);
    }

    public boolean execute(String script) {

        stack.clear(); // Limpiamos la pila antes de empezar
        executionStack.clear();
        executionStack.push(true);

        // Convertimos el Array a Lista para poder usar .get(i) y manejar índices
        String[] tokensArray = script.trim().split("\\s+");
        List<String> tokens = Arrays.asList(tokensArray);

        int i = 0;

        try {
            while (i < tokens.size()) {
                String token = tokens.get(i);

                // --- CONTROL DE FLUJO ---
                if (token.equals("OP_IF")) {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    byte[] condition = stack.pop();
                    boolean cond = condition.length > 0 && !(condition.length == 1 && condition[0] == 0);
                    executionStack.push(cond);

                    i++;
                    continue;
                }

                else if (token.equals("OP_NOTIF")) {
                    if (stack.isEmpty())
                        return false;
                    byte[] condition = stack.pop();
                    boolean cond = condition.length > 0 && !(condition.length == 1 && condition[0] == 0);
                    executionStack.push(!cond);
                    i++;
                    continue;
                }

                else if (token.equals("OP_ELSE")) {
                    if (executionStack.isEmpty()) {
                        return false;
                    }

                    boolean current = executionStack.pop();
                    executionStack.push(!current);

                    i++;
                    continue;
                }

                else if (token.equals("OP_ENDIF")) {
                    if (executionStack.isEmpty()) {
                        return false;
                    }

                    executionStack.pop();

                    i++;

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
                    System.out.println("Instrucción inválida: " + token);
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error crítico durante ejecución: " + e.getMessage());
            return false;
        }

        return verificarResultado();
    }

    // Helper de verificación adicional
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
}