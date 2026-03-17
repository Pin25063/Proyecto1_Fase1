import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de implementar el conjunto de instrucciones (Opcodes) de
 * Bitcoin Script.
 * <p>
 * Los métodos de esta clase están divididos según las especificaciones del
 * proyecto en las siguientes categorías:
 * </p>
 * <ul>
 * <li><b>1. Literales y empuje de datos:</b> OP_0, OP_1, OP_2, OP_3, OP_4,
 * OP_5, OP_6, OP_7, OP_8, OP_9, OP_10, OP_11, OP_12, OP_13, OP_14, OP_15,
 * OP_16.</li>
 * <li><b>2. Operaciones de pila:</b> OP_DROP, OP_DUP, OP_SWAP, OP_OVER.</li>
 * <li><b>3. Lógica y comparación:</b> OP_EQUAL, OP_EQUALVERIFY, OP_NOT,
 * OP_BOOLAND, OP_BOOLOR.</li>
 * <li><b>4. Aritmética básica:</b> OP_ADD, OP_SUB, OP_LESSTHAN, OP_GREATERTHAN,
 * OP_LESSTHANOREQUAL, OP_GREATERTHANOREQUAL, OP_NUMEQUALVERIFY.</li>
 * <li><b>5. Control de flujo:</b> OP_VERIFY, OP_RETURN. <i>(Nota: OP_IF,
 * OP_NOTIF, OP_ELSE y OP_ENDIF se evalúan directamente en la clase
 * Interprete).</i></li>
 * <li><b>6. Criptografía y Firmas:</b> OP_HASH160, OP_CHECKSIG,
 * OP_CHECKSIGVERIFY, OP_CHECKMULTISIG, OP_CHECKMULTISIGVERIFY.</li>
 * </ul>
 * * @param <T> tipo de datos almacenados en la pila (generalmente byte[])
 */
public class Opcodes<T> {

    private Stack<byte[]> stack;
    private Map<String, Runnable> opcodeMap;

    public Opcodes(Stack<byte[]> stack) {
        this.stack = stack; // utiliza la pila del intérprete
        this.opcodeMap = new HashMap<>();
        init();
    }

    /**
     * Inicializa el mapa de opcodes con sus respectivas implementaciones
     * Cada opcode se asocia a un método que implementa su lógica específica
     */
    private void init() {
        opcodeMap.put("OP_0", this::OP_0);
        opcodeMap.put("OP_1", this::OP_1);
        opcodeMap.put("OP_2", this::OP_2);
        opcodeMap.put("OP_3", this::OP_3);
        opcodeMap.put("OP_4", this::OP_4);
        opcodeMap.put("OP_5", this::OP_5);
        opcodeMap.put("OP_6", this::OP_6);
        opcodeMap.put("OP_7", this::OP_7);
        opcodeMap.put("OP_8", this::OP_8);
        opcodeMap.put("OP_9", this::OP_9);
        opcodeMap.put("OP_10", this::OP_10);
        opcodeMap.put("OP_11", this::OP_11);
        opcodeMap.put("OP_12", this::OP_12);
        opcodeMap.put("OP_13", this::OP_13);
        opcodeMap.put("OP_14", this::OP_14);
        opcodeMap.put("OP_15", this::OP_15);
        opcodeMap.put("OP_16", this::OP_16);
        opcodeMap.put("OP_DROP", this::OP_DROP);
        opcodeMap.put("OP_DUP", this::OP_DUP);
        opcodeMap.put("OP_EQUAL", this::OP_EQUAL);
        opcodeMap.put("OP_VERIFY", this::OP_VERIFY);
        opcodeMap.put("OP_EQUALVERIFY", this::OP_EQUALVERIFY);
        opcodeMap.put("OP_HASH160", this::OP_HASH160);
        opcodeMap.put("OP_CHECKSIG", this::OP_CHECKSIG);
        opcodeMap.put("OP_CHECKSIGVERIFY", this::OP_CHECKSIGVERIFY);
        opcodeMap.put("OP_SWAP", this::OP_SWAP);
        opcodeMap.put("OP_OVER", this::OP_OVER);
        opcodeMap.put("OP_NOT", this::OP_NOT);
        opcodeMap.put("OP_BOOLAND", this::OP_BOOLAND);
        opcodeMap.put("OP_BOOLOR", this::OP_BOOLOR);
        opcodeMap.put("OP_ADD", this::OP_ADD);
        opcodeMap.put("OP_SUB", this::OP_SUB);
        opcodeMap.put("OP_LESSTHAN", this::OP_LESSTHAN);
        opcodeMap.put("OP_GREATERTHAN", this::OP_GREATERTHAN);
        opcodeMap.put("OP_LESSTHANOREQUAL", this::OP_LESSTHANOREQUAL);
        opcodeMap.put("OP_GREATERTHANOREQUAL", this::OP_GREATERTHANOREQUAL);
        opcodeMap.put("OP_NUMEQUALVERIFY", this::OP_NUMEQUALVERIFY);
        opcodeMap.put("OP_RETURN", this::OP_RETURN);
        opcodeMap.put("OP_CHECKMULTISIG", this::OP_CHECKMULTISIG);
        opcodeMap.put("OP_CHECKMULTISIGVERIFY", this::OP_CHECKMULTISIGVERIFY);
    }

    // ==========================================
    // 1. LITERALES Y EMPUJE DE DATOS
    // ==========================================

    /**
     * OP_0 a OP_16: Empujan el número correspondiente (0-16) en la pila como un
     * array de bytes
     * <p>
     * Categoría: Literales y empuje de datos.
     */
    public void OP_0() {
        stack.push(new byte[] { 0 });
    }

    public void OP_1() {
        stack.push(new byte[] { 1 });
    }

    public void OP_2() {
        stack.push(new byte[] { 2 });
    }

    public void OP_3() {
        stack.push(new byte[] { 3 });
    }

    public void OP_4() {
        stack.push(new byte[] { 4 });
    }

    public void OP_5() {
        stack.push(new byte[] { 5 });
    }

    public void OP_6() {
        stack.push(new byte[] { 6 });
    }

    public void OP_7() {
        stack.push(new byte[] { 7 });
    }

    public void OP_8() {
        stack.push(new byte[] { 8 });
    }

    public void OP_9() {
        stack.push(new byte[] { 9 });
    }

    public void OP_10() {
        stack.push(new byte[] { 10 });
    }

    public void OP_11() {
        stack.push(new byte[] { 11 });
    }

    public void OP_12() {
        stack.push(new byte[] { 12 });
    }

    public void OP_13() {
        stack.push(new byte[] { 13 });
    }

    public void OP_14() {
        stack.push(new byte[] { 14 });
    }

    public void OP_15() {
        stack.push(new byte[] { 15 });
    }

    public void OP_16() {
        stack.push(new byte[] { 16 });
    }

    // ==========================================
    // 2. OPERACIONES DE PILA
    // ==========================================

    /**
     * OP_DUP: Duplica el elemento en el tope de la pila y lo empuja nuevamente al
     * tope.
     * <p>
     * Categoría: Operaciones de Pila.
     * </p>
     */
    public void OP_DUP() {
        if (stack.isEmpty()) {
            throw new RuntimeException("El stack está vacío");
        }
        byte[] top = (byte[]) stack.peek();
        byte[] copy = top.clone(); // Clonamos el array para evitar referencias compartidas

        stack.push(copy);
    }

    /**
     * OP_DROP: Elimina el elemento en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Pila.
     * </p>
     */
    public void OP_DROP() {
        stack.pop();
    }

    /**
     * OP_SWAP: Intercambia de posición los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Pila.
     * </p>
     * 
     * @throws RuntimeException si hay menos de 2 elementos.
     */
    public void OP_SWAP() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para swap");
        } else {
            byte[] a = stack.pop();
            byte[] b = stack.pop();
            stack.push(a);
            stack.push(b);
        }
    }

    /**
     * OP_OVER: Duplica el segundo elemento desde el tope de la pila y lo empuja al
     * tope
     * <p>
     * Categoría: Operaciones de Pila.
     * </p>
     */
    public void OP_OVER() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para over");
        } else {
            byte[] a = stack.pop();
            byte[] b = stack.pop();
            stack.push(b);
            stack.push(a);
            stack.push(b);
        }
    }

    // ==========================================
    // 3. LÓGICA Y COMPARACIÓN
    // ==========================================

    /**
     * OP_EQUAL: Compara los dos elementos en el tope de la pila.
     * Empuja 1 si son exactamente iguales, o 0 si son diferentes.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_EQUAL() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para igualar");
        }
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        if (java.util.Arrays.equals(a, b)) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[] { 0 });
        }
    }

    /**
     * OP_EQUALVERIFY: Compara los dos elementos en el tope de la pila y verifica
     * que sean iguales.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_EQUALVERIFY() {
        OP_EQUAL();
        OP_VERIFY();
    }

    /**
     * OP_NOT: Invierte el valor booleano del elemento en el tope.
     * Si es falso (vacío o 0) empuja 1. Si es verdadero, empuja 0.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_NOT() {
        if (stack.isEmpty()) {
            throw new RuntimeException("No hay elementos en el stack para NOT");
        }
        byte[] a = stack.pop();
        if (isTrue(a)) {
            stack.push(new byte[0]);
        } else {
            stack.push(new byte[] { 1 });
        }
    }

    /**
     * OP_BOOLAND: Realiza una operación lógica AND entre los dos elementos del
     * tope.
     * Empuja 1 si ambos son verdaderos, o 0 en caso contrario.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_BOOLAND() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para BOOLAND");
        }
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        if (isTrue(a) && isTrue(b)) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_BOOLOR: Realiza una operación lógica OR entre los dos elementos del
     * tope.
     * Empuja 1 si al menos uno es verdadero, o 0 en caso contrario.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_BOOLOR() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para BOOLOR");
        }
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        if (isTrue(a) || isTrue(b)) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    // ==========================================
    // 4. ARITMÉTICA BÁSICA
    // ==========================================

    /**
     * OP_ADD: Suma los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones Aritméticas.
     * </p>
     */
    public void OP_ADD() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para ADD");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        stack.push(longToBytes(a + b));
    }

    /**
     * OP_SUB: Resta el elemento en el tope de la pila del segundo elemento.
     * <p>
     * Categoría: Operaciones Aritméticas.
     * </p>
     */
    public void OP_SUB() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para SUB");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        stack.push(longToBytes(b - a));
    }

    /**
     * OP_LESSTHAN: Compara los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Comparación.
     * </p>
     */
    public void OP_LESSTHAN() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para LESSTHAN");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        if (b < a) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_GREATERTHAN: Compara los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Comparación.
     * </p>
     */
    public void OP_GREATERTHAN() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para GREATERTHAN");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        if (b > a) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_LESSTHANOREQUAL: Compara los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Comparación.
     * </p>
     */
    public void OP_LESSTHANOREQUAL() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para LESSTHANOREQUAL");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        if (b <= a) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_GREATERTHANOREQUAL: Compara los dos elementos en el tope de la pila.
     * <p>
     * Categoría: Operaciones de Comparación.
     * </p>
     */
    public void OP_GREATERTHANOREQUAL() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para GREATERTHANOREQUAL");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        if (b >= a) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_NUMEQUALVERIFY: Compara los dos elementos en el tope de la pila como
     * números.
     * Si son iguales, empuja 1, sino empuja 0.
     * Luego verifica que el resultado sea verdadero (1) usando OP_VERIFY.
     * <p>
     * Categoría: Operaciones de Comparación.
     * </p>
     */
    public void OP_NUMEQUALVERIFY() {
        if (stack.size() < 2) {
            throw new RuntimeException("No hay suficientes elementos en el stack para NUMEQUALVERIFY");
        }
        long a = bytesToLong(stack.pop());
        long b = bytesToLong(stack.pop());
        if (a == b) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
        OP_VERIFY();
    }

    // =========================================
    // 5. CONTROL DE FLUJO
    // =========================================

    /**
     * OP_VERIFY: Verifica que el elemento en el tope de la pila sea verdadero (no
     * vacío y no [0])
     * Si es falso, detiene la ejecución del script y devuelve false.
     * Si es verdadero, simplemente lo elimina y continúa con la ejecución.
     * <p>
     * Categoría: Control de flujo.
     * </p>
     */
    public void OP_VERIFY() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack vacío en OP_VERIFY");

        byte[] top = stack.pop();

        // Si es vacío o es [0], fallamos.
        boolean esFalse = (top.length == 0) || (top.length == 1 && top[0] == 0);

        if (esFalse) {
            // AL LANZAR EXCEPCIÓN, EL INTÉRPRETE SE DETIENE Y DEVUELVE FALSE
            throw new RuntimeException("OP_VERIFY falló: Tope de pila era falso");
        }
        // Si es verdadero, no hace nada y el programa sigue (que es lo correcto)
    }

    /**
     * OP_RETURN: Invalida el script
     * Detiene al intérprete y devuelve false.
     *
     */
    public void OP_RETURN() {
        throw new RuntimeException("OP_RETURN ejecutado. Script explícitamente inválido.");
    }

    // =========================================
    // 6. CRIPTOGRAFÍA Y FIRMAS
    // =========================================

    public void OP_HASH160() {
    }

    /**
     * OP_CHECKSIG: Simula la verificación de una firma digital.
     * Toma dos elementos del tope de la pila (firma y clave pública)
     * y verifica si la firma "contiene" la clave pública (simulación simple).
     * <p>
     * Categoría: Criptográficas y Firmas.
     * </p>
     */
    public void OP_CHECKSIG() {
        if (stack.size() < 2) {
            stack.push(new byte[0]);
            return;
        }

        byte[] pubKey = stack.pop();
        byte[] signature = stack.pop();

        String pubKeyStr = bytesToHex(pubKey);
        String sigStr = bytesToHex(signature);

        if (sigStr.contains(pubKeyStr)) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[0]);
        }
    }

    /**
     * OP_CHECKSIGVERIFY: Verifica la firma digital del mensaje.
     * <p>
     * Categoría: Operaciones de Verificación.
     * </p>
     */
    public void OP_CHECKSIGVERIFY() {
        OP_CHECKSIG();
        OP_VERIFY();
    }

    /**
     * OP_CHECKMULTISIG: Simula la verificación de múltiples firmas digitales.
     * Toma una serie de elementos del tope de la pila (firmas y llaves públicas)
     * y verifica si las firmas "contienen" las llaves públicas (simulación simple).
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_CHECKMULTISIG() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack underflow en OP_CHECKMULTISIG (Falta N)");
        }

        // Sacar N (Cantidad total de llaves públicas)
        int n = (int) bytesToLong(stack.pop());
        if (stack.size() < n) {
            throw new RuntimeException("Faltan llaves públicas en la pila");
        }

        // Sacar las N llaves públicas (lo hacemos en reversa para mantener el orden
        // original de izquierda a derecha)
        byte[][] pubKeys = new byte[n][];
        for (int i = n - 1; i >= 0; i--) {
            pubKeys[i] = stack.pop();
        }

        if (stack.isEmpty()) {
            throw new RuntimeException("Stack underflow en OP_CHECKMULTISIG (Falta M)");
        }

        // Sacar M (Cantidad de firmas requeridas)
        int m = (int) bytesToLong(stack.pop());
        if (m > n) {
            throw new RuntimeException("M (firmas requeridas) no puede ser mayor que N (llaves públicas)");
        }
        // Verificamos si hay suficientes elementos para las firmas y el elemento dummy
        if (stack.size() < m + 1) {
            throw new RuntimeException("Faltan firmas o el elemento dummy (OP_0)");
        }

        // Sacar las M firmas (en reversa para mantener orden original)
        byte[][] signatures = new byte[m][];
        for (int i = m - 1; i >= 0; i--) {
            signatures[i] = stack.pop();
        }

        stack.pop(); // Sacamos el elemento Dummy y lo ignoramos

        // Lógica de validación
        int matchCount = 0;
        int sigIndex = 0;
        int pubIndex = 0;

        // Iteramos comparando en orden
        while (sigIndex < m && pubIndex < n) {
            String sigStr = bytesToHex(signatures[sigIndex]);
            String pubStr = bytesToHex(pubKeys[pubIndex]);

            // Si la firma "contiene" la llave (nuestra lógica mock)
            if (sigStr.contains(pubStr)) {
                matchCount++;
                sigIndex++; // Avanzamos a la siguiente firma
                pubIndex++; // Avanzamos a la siguiente llave
            } else {
                // Si la firma no pertenece a esta llave, probamos con la siguiente llave
                // pública
                pubIndex++;
            }
        }

        // Resultado final
        if (matchCount == m) {
            stack.push(new byte[] { 1 }); // TRUE
        } else {
            stack.push(new byte[0]); // FALSE
        }
    }

    /**
     * OP_CHECKMULTISIGVERIFY: Verifica múltiples firmas digitales y verifica el
     * resultado.
     * <p>
     * Categoría: Lógica y Comparación.
     * </p>
     */
    public void OP_CHECKMULTISIGVERIFY() {
        OP_CHECKMULTISIG();
        OP_VERIFY();
    }

    // =========================================
    // MÉTODOS AUXILIARES
    // =========================================

    /**
     * @param bytes
     * @return String
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * @param token
     * @return boolean
     */
    public boolean execute(String token) {
        Runnable operation = opcodeMap.get(token);
        if (operation != null) {
            operation.run();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param data
     * @return
     */
    private boolean isTrue(byte[] data) {
        if (data.length == 0) {
            return false;
        }
        if (data.length == 1 && data[0] == 0) {
            return false;
        }
        return true;
    }

    /**
     * Convierte un array de bytes en un valor long, asumiendo que el array
     * representa un número en formato little-endian. Si el array está vacío,
     * devuelve 0.
     *
     * @param data
     * @return
     */
    private long bytesToLong(byte[] data) {
        if (data.length == 0) {
            return 0;
        }

        return new java.math.BigInteger(data).longValue();
    }

    /**
     * Convierte un valor long a un array de bytes en formato little-endian.
     * 
     * @param value
     * @return
     */
    private byte[] longToBytes(long value) {
        if (value == 0) {
            return new byte[0];
        }
        return java.math.BigInteger.valueOf(value).toByteArray();
    }

}