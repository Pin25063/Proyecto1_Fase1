import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InterpreteTest {
    private Interprete interprete;

    @Before
    public void setUp() {
        interprete = new Interprete();
    }

     @Test
    public void simpleTrueScript() {
        boolean result = interprete.execute("OP_1");

        assertTrue(result);
    }

    @Test
    public void simpleFalseScript() {
        boolean result = interprete.execute("OP_0");

        assertFalse(result);
    }

    @Test
    public void equalTrueScript() {
        boolean result = interprete.execute("OP_5 OP_5 OP_EQUAL");

        assertTrue(result);
    }

    @Test
    public void equalFalseScript() {
        boolean result = interprete.execute("OP_5 OP_4 OP_EQUAL");

        assertFalse(result);
    }

    @Test
    public void invalidInstruction() {
        boolean result = interprete.execute("OP_1 OP_FAKE");

        assertFalse(result);
        assertEquals("Instrucción inválida: OP_FAKE", interprete.getError());
    }

    @Test
    public void ConditionalIfTrue() {
        boolean result = interprete.execute("OP_1 OP_IF OP_2 OP_ENDIF");

        assertTrue(result);
    }

    @Test
    public void ConditionalIfFalse() {
        boolean result = interprete.execute("OP_0 OP_IF OP_2 OP_ENDIF");

        assertFalse(result);
    }

    @Test
    public void ConditionalIfElseTrue() {
        boolean result = interprete.execute(
                "OP_0 OP_IF OP_1 OP_ELSE OP_2 OP_ENDIF");

        assertTrue(result);
    }

    @Test
    public void HexDataTest() {
        boolean result = interprete.execute("<01>");

        assertTrue(result);
    }
}
