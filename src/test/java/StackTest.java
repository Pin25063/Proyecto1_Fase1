import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack<byte[]> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPushAndPop() {
        byte[] data = new byte[]{1, 2, 3};
        stack.push(data);
        assertFalse(stack.isEmpty());

        byte[] popped = stack.pop();
        assertArrayEquals(data, popped);
        assertTrue(stack.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testPopEmptyStackThrowsException() {
        stack.pop(); // Debería lanzar RuntimeException
    }

    @Test
    public void testClear() {
        stack.push(new byte[]{1});
        stack.push(new byte[]{2});
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}