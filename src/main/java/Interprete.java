public class Interprete {
    private Stack<byte[]> stack;
    private Opcodes<byte[]> vm;

    public Interprete() {
        this.stack = new Stack<>();
        this.vm = new Opcodes<>(stack);
    }

    public boolean execute(String script) {

        String[] instructions = script.trim().split("\\s+");

        for (String instruction : instructions) {
            if (isHex(instruction)) {
                byte[] data = hexToBytes(instruction);
                stack.push(data);
            } else if (vm.execute(instruction)) {

            } else {
                System.out.println("Instrucción inválida: " + instruction);
            }
            return false;
        }

        return true;
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

