import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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
    
    @Test
    public void OpDropTest() {
        opcodes.OP_1();
        opcodes.OP_2();

        opcodes.OP_DROP();

        assertArrayEquals(new byte[] { 1 }, stack.pop());
    }

    @Test
    public void OpDupTest() {
        opcodes.OP_5();
        opcodes.OP_DUP();

        assertArrayEquals(new byte[] { 5 }, stack.pop());
        assertArrayEquals(new byte[] { 5 }, stack.pop());
    }

    @Test
    public void OpEqualTrueTest() {
        opcodes.OP_1();
        opcodes.OP_1();

        opcodes.OP_EQUAL();

        assertArrayEquals(new byte[] { 1 }, stack.pop());
    }

     @Test
    public void OpEqualFalseTest() {
        opcodes.OP_1();
        opcodes.OP_2();

        opcodes.OP_EQUAL();

        assertArrayEquals(new byte[] { 0 }, stack.pop());
    }

    @Test 
    public void OpVerifyTrueTest() {
        opcodes.OP_1();
        opcodes.OP_VERIFY();
    }

    @Test(expected = RuntimeException.class)
    public void OpVerifyFalseTest() {
        //tope es 0
        opcodes.OP_0();
        opcodes.OP_VERIFY();

        //array vacio
        stack.clear();
        stack.push(new byte[0]);
        opcodes.OP_VERIFY();

        // no hay nada en el stack
        stack.clear();
        opcodes.OP_VERIFY();
    }

    @Test
    public void OpEqualVerifyTrueTest() {
        opcodes.OP_9();
        opcodes.OP_9();

        opcodes.OP_EQUALVERIFY();
    }

    @Test(expected = RuntimeException.class)
    public void OpEqualVerifyFalseTest() {
        opcodes.OP_7();
        opcodes.OP_6();

        opcodes.OP_EQUALVERIFY();
    }

    @Test
    public void OpCheckSigTrueTest() {
        byte[] sig = new byte[] {(byte)0xAA, (byte)0xBB, (byte)0xCC};
        byte[] key = new byte[] {(byte)0xBB};

        stack.push(sig);
        stack.push(key);

        opcodes.OP_CHECKSIG();
        assertArrayEquals(new byte[] { 1 }, stack.pop());
    }

    @Test
    public void OpCheckSigFalseTest() {
        byte[] sig = new byte[] {(byte)0xAA, (byte)0xBB, (byte)0xCC};
        byte[] key = new byte[] {(byte)0xFF};

        stack.push(sig);
        stack.push(key);

        opcodes.OP_CHECKSIG();
        assertArrayEquals(new byte[0], stack.pop());
    }

    @Test
    public void OpCheckSigOneElementTest() {
        stack.push(new byte[] { (byte)0xAA});

        opcodes.OP_CHECKSIG();
        assertArrayEquals(new byte[0], stack.pop());
    }

    @Test
    public void OpCheckSigVerifyTrueTest() {
        byte[] sig = new byte[] {(byte)0xAA, (byte)0xBB, (byte)0xCC};
        byte[] key = new byte[] {(byte)0xAA};

        stack.push(sig);
        stack.push(key);

        opcodes.OP_CHECKSIGVERIFY();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = RuntimeException.class) 
    public void OpCheckSigVerifyFalseTest() {
        byte[] sig = new byte[] {(byte)0xAA, (byte)0xBB, (byte)0xCC};
        byte[] key = new byte[] {(byte)0xFF};

        stack.push(sig);
        stack.push(key);

        opcodes.OP_CHECKSIGVERIFY();
    }

    @Test 
    public void OPswapTest() {
        opcodes.OP_1();
        opcodes.OP_2();

        opcodes.OP_SWAP();

        assertArrayEquals(new byte[] { 1 }, stack.pop());
        assertArrayEquals(new byte[] { 2 }, stack.pop());
    }

    @Test 
    public void OpOverTest() {
        opcodes.OP_1();
        opcodes.OP_2();

        opcodes.OP_OVER();

        assertArrayEquals(new byte[] { 1 }, stack.pop());
        assertArrayEquals(new byte[] { 2 }, stack.pop());
        assertArrayEquals(new byte[] { 1 }, stack.pop());
    }

    @Test
    public void OpAddTest() {
        opcodes.OP_5();
        opcodes.OP_15();

        opcodes.OP_ADD();

        assertArrayEquals(new byte[] { 20 }, stack.pop());
    }

    @Test
    public void OpSubTest() {
        opcodes.OP_10();
        opcodes.OP_5();

        opcodes.OP_SUB();

        assertArrayEquals(new byte[] { 5 }, stack.pop());
    }

    @Test
    public void OpLessThanTest() {
        opcodes.OP_3();
        opcodes.OP_9();

        opcodes.OP_LESSTHAN();

        assertArrayEquals(new byte[] { 1 }, stack.pop());

        stack.clear();
        opcodes.OP_9();
        opcodes.OP_3();

        opcodes.OP_LESSTHAN();
        assertArrayEquals(new byte [0],stack.pop());
    }

    @Test
    public void OpGreaterThanTest() {
        opcodes.OP_10();
        opcodes.OP_7();

        opcodes.OP_GREATERTHAN();

        assertArrayEquals(new byte[] { 1 }, stack.pop());

        stack.clear();
        opcodes.OP_9();
        opcodes.OP_11();

        opcodes.OP_GREATERTHAN();
        assertArrayEquals(new byte [0],stack.pop());
    }

    @Test 
    public void OpLessThanOrEqualTest() {
        opcodes.OP_4();
        opcodes.OP_4();

        opcodes.OP_LESSTHANOREQUAL();
        assertArrayEquals(new byte[] { 1 }, stack.pop());

        stack.clear();
        opcodes.OP_4();
        opcodes.OP_7();

        opcodes.OP_LESSTHANOREQUAL();
        assertArrayEquals(new byte[] { 1 }, stack.pop());

        stack.clear();
        opcodes.OP_8();
        opcodes.OP_7();

        opcodes.OP_LESSTHANOREQUAL();
        assertArrayEquals(new byte[0], stack.pop());
    }

    @Test
    public void OpGreaterThanOrEqualTest() {
        opcodes.OP_10();
        opcodes.OP_10();

        opcodes.OP_GREATERTHANOREQUAL();
        assertArrayEquals(new byte[] { 1 },stack.pop());

        stack.clear();
        opcodes.OP_10();
        opcodes.OP_5();

        opcodes.OP_GREATERTHANOREQUAL();
        assertArrayEquals(new byte[] { 1 },stack.pop());

        stack.clear();
        opcodes.OP_10();
        opcodes.OP_16();

        opcodes.OP_GREATERTHANOREQUAL();
        assertArrayEquals(new byte[0],stack.pop());
    }

    @Test
    public void OpNumEqualVerifyTrueTest() {
        opcodes.OP_10();
        opcodes.OP_10();

        opcodes.OP_NUMEQUALVERIFY();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void OpNumEqualVerifyFalseTest() {
        opcodes.OP_3();
        opcodes.OP_7();
        opcodes.OP_NUMEQUALVERIFY();
    } 

    @Test(expected = RuntimeException.class)
    public void OpReturnTest() {
        opcodes.OP_RETURN();
    }
    
}

