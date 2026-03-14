import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertArrayEquals;

public class OpCodeTest {
    private Stack<byte[]> stack;
    private Opcodes<Object> opcodes;

    @Before 
    public void setUp() {
        stack = new Stack<>();
        opcodes = new Opcodes<Object>(stack);
    }

    @Test
    public void NumericOpcodePushTest() {
        opcodes.OP_1();
        assertArrayEquals(new byte[] { 1 }, stack.peek());
        opcodes.OP_VERIFY(); 

        opcodes.OP_2();
        assertArrayEquals(new byte[] { 2 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_3();
        assertArrayEquals(new byte[] { 3 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_4();
        assertArrayEquals(new byte[] { 4 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_5();
        assertArrayEquals(new byte[] { 5 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_6();
        assertArrayEquals(new byte[] { 6 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_7();
        assertArrayEquals(new byte[] { 7 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_8();
        assertArrayEquals(new byte[] { 8 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_9();
        assertArrayEquals(new byte[] { 9 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_10();
        assertArrayEquals(new byte[] { 10 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_11();
        assertArrayEquals(new byte[] { 11 }, stack.peek());

        opcodes.OP_12();
        assertArrayEquals(new byte[] { 12 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_13();
        assertArrayEquals(new byte[] { 13 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_14();
        assertArrayEquals(new byte[] { 14 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_15();
        assertArrayEquals(new byte[] { 15 }, stack.peek());
        opcodes.OP_VERIFY();

        opcodes.OP_16();
        assertArrayEquals(new byte[] { 16 }, stack.peek());
        opcodes.OP_VERIFY();
    }

    @Test(expected = RuntimeException.class) 
        public void Op0ReturnFalseTest() {
            opcodes.OP_0();
            opcodes.OP_VERIFY();
        }
    }

