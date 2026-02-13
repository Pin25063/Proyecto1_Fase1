public class Interprete {
    private Stack<byte[]> stack;
    private Opcodes<byte[]> vm;

    public Interprete() {
        this.stack = new Stack<>();
        this.vm = new Opcodes<>(stack);
    }
    

    public boolean execute(String operation) {
        boolean fueInstruccion = vm.execute(operation);

        if (!fueInstruccion) {
            System.out.println("No es opcode, es dato: " + operation);

            return false;
        } 
        return true;
    } 
}

