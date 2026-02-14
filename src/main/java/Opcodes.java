import java.util.HashMap;
import java.util.Map;

public class Opcodes<T> {

    private Stack<byte[]> stack;
    private Map<String, Runnable> opcodeMap;

    public Opcodes(Stack<byte[]> stack) {
        this.stack = stack; // utiliza la pila del intérprete
        this.opcodeMap = new HashMap<>();
        init();
    }

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
    }

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

    public void PUSH_DATA() {
        // Implementation for pushing data onto the stack
    }

    public void OP_DROP() {
        stack.pop();
    }

    public void OP_DUP() {
        byte[] top = (byte[]) stack.pop();
        stack.push(top);
        stack.push(top);
    }

    public void OP_EQUAL() {
        byte[] a = stack.pop();
        byte[] b = stack.pop();
        if (java.util.Arrays.equals(a, b)) {
            stack.push(new byte[] { 1 });
        } else {
            stack.push(new byte[] { 0 });
        }
    }

    public boolean OP_VERIFY() {
        byte[] top = stack.pop();
        if (top.length == 1 && top[0] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OP_EQUALVERIFY() {
        OP_EQUAL();
        return OP_VERIFY();
    }

    public void OP_HASH160() {
    }

    public void OP_CHECKSIG() {
    }

    public boolean execute(String token) {
        Runnable operation = opcodeMap.get(token);
        if (operation != null) {
            operation.run();
            return true;
        } else {
            return false;
        }
    }

}