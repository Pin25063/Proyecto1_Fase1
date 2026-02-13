public class Interprete {
    private Stack<byte[]> stack = new Stack<>();
    private Opcodes<byte[]> vm = new Opcodes<>();
    

    public boolean execute(String operation) {
        boolean fueInstruccion = vm.execute(operation);

        if (!fueInstruccion) {
            System.out.println("No es opcode, es dato: " + operation);

            return false;
        } 
        return true;
    } 
}

