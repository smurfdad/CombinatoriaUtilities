import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CombinatoriaUtilsTest {

    @Test
    public void testFactorial() {
        assertEquals(1, CombinatoriaUtils.factorial(0));
        assertEquals(1, CombinatoriaUtils.factorial(1));
        assertEquals(2, CombinatoriaUtils.factorial(2));
        assertEquals(2432902008176640000L, CombinatoriaUtils.factorial(20));
    }

}
