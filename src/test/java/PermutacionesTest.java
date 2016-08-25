import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PermutacionesTest {

    @Parameter(value = 0)
    public String[] elementos;

    @Parameter(value = 1)
    public int resultado;

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                {

                        new String[]
                {
                        "1", "2", "3"
                }, 6
                },
                {

                        new String[]
                {
                        "0", "1"
                }, 2
                },
                {

                        new String[]
                {
                        "1", "2", "3", "4", "5", "6",
                }, 720
                }

        });
    }

    @Test
    // @Ignore
    public void testGetCombinations() {
        System.out.println("-----");
        Permutaciones<String> instance = new Permutaciones<>(Arrays.asList(this.elementos));
        // assertEquals(this.resultado, instance.getCombinations().size());
        int i = 0;
        while (instance.hasNext()) {
            print(instance.next());
            i++;
        }
        assertEquals(this.resultado, i);
    }

    // @Test
    // public void testNumOfCombinations() {
    // Permutaciones<String> instance = new Permutaciones<>(Arrays.asList(this.elementos), this.elementos.length);
    // // assertEquals(this.resultado, instance.numOfCombinations());
    // }
    //
    private void print(Collection<String> permutacion) {

        System.out.println(permutacion.stream().reduce("", (String a, String e) -> {
            return a + e;
        }));

    }
}
