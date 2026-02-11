public class Opcodes<T> {

    private Stack<T> stack = new Stack<>();

    public void PUSH_DATA() {
        // Implementation for pushing data onto the stack
    }

    public void OP_DROP() {
        stack.pop();
    }

    public void OP_DUP() {
        T top = stack.pop();
        stack.push(top);
        stack.push(top);
    }

    public void OP_EQUAL() {
        T a = stack.pop();
        T b = stack.pop();
        if (a.equals(b)) {
            stack.push((T) Boolean.TRUE);
        } else {
            stack.push((T) Boolean.FALSE);
        }
    }

    public boolean OP_VERIFY() {
        T top = stack.pop();
        if (top == Boolean.TRUE) {
            return true;
        } else {
            return false;
        }
    }
}