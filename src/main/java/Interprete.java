public class Interprete {
    private Stack<byte[]> stack;
    private Opcodes<byte[]> vm;

    public Interprete() {
        this.stack = new Stack<>();
        this.vm = new Opcodes<>(stack);
    }

    public boolean execute(String operation) {

        if (isHex(operation)) {
            byte[] data = hexToBytes(operation);
            stack.push(data);
            return true;
        }

        if (vm.execute(operation)) {
            return true;
        }

        System.out.println("Instrucción inválida: " + operation);
        return false;
    }

    private boolean isHex(String instruction) {
        return instruction.startsWith("<") && instruction.endsWith(">");
    }

    private byte[] hexToBytes(String instruction) {
        String hex = instruction.substring(1, instruction.length() - 1); //ignora "<>"
        int len = hex.length();

        byte[] data = new byte[len/2];

        for (int i = 0; i < len; i +=2) {
            data[i / 2] = (byte) Integer.parseInt(hex.substring(1, 1+2), 16);
        }
        return data;
    }
}

