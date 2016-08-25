public class CombinatoriaUtils {

    private CombinatoriaUtils() {
    }

    /**
     * n!
     */
    public static long factorial(long numero) {
        if (numero == 0) {
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }

    }
}
