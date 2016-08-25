import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CombinacionesTest {

    @Parameter(value = 0)
    public String[] elementos;

    @Parameter(value = 1)
    public int tamano;

    @Parameter(value = 2)
    public int resultado;

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                {

                        new String[]
                {
                        "1", "2", "3"
                }, 3, 1
                },
                {

                        new String[]
                {
                        "1", "2", "3"
                }, 1, 3
                },
                {

                        new String[]
                {
                        "1", "2", "3", "4", "5", "6",
                }, 3, 20
                }

        });
    }

    @Test
    // @Ignore
    public void testGetCombinations() {
        System.out.println("-----");
        Combinaciones<String> instance = new Combinaciones<>(Arrays.asList(this.elementos), this.tamano);
        // assertEquals(this.resultado, instance.getCombinations().size());
        int i = 0;
        while (instance.hasNext()) {
            print(instance.next());
            i++;
        }
        assertEquals(this.resultado, i);
    }

    @Test
    public void testNumOfCombinations() {
        Combinaciones<String> instance = new Combinaciones<>(Arrays.asList(this.elementos), this.tamano);
        assertEquals(this.resultado, instance.count());
    }

    private void print(Collection<String> combinacion) {

        System.out.println(combinacion.stream().reduce("", (String a, String e) -> {
            return a + e;
        }));

    }
}
