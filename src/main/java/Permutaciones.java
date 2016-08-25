import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Permutación es la variación del orden o de la disposición de los elementos de un conjunto<br>
 * <ul>
 * <li>No entran todos los elementos.</li>
 * <li>No importa el orden.</li>
 * <li>No se repiten los elementos.</li>
 */

public class Permutaciones<T> implements Iterator<Collection<T>>, Iterable<Collection<T>> {
    private T[] array;

    private int[] swaps;

    @SuppressWarnings("unchecked")
    public Permutaciones(Collection<T> array) {
        this((T[]) array.toArray());
    }

    /**
     * Constructor that create a sub-Permutations Iterator (with defined size) of elements in array parameter
     *
     * @param array elements to be permuted
     * @param size size of sub-permutations
     */
    public Permutaciones(final T[] array) {
        this.array = array.clone();
        this.swaps = new int[array.length];
        for (int i = 0; i < this.swaps.length; i++) {
            this.swaps[i] = i;
        }
    }

    /**
     * Calculates next permutation available
     *
     * @return next permutation array or null if no more permutations exist
     */
    @Override
    public Collection<T> next() {
        if (this.array == null) {
            return null;
        }

        T[] res = Arrays.copyOf(this.array, this.swaps.length);
        // Prepare next
        int i = this.swaps.length - 1;
        while (i >= 0 && this.swaps[i] == this.array.length - 1) {
            swap(i, this.swaps[i]); // Undo the swap
            this.swaps[i] = i;
            i--;
        }

        if (i < 0) {
            this.array = null;
        } else {
            int prev = this.swaps[i];
            swap(i, prev);
            int next = prev + 1;
            this.swaps[i] = next;
            swap(i, next);
        }

        return Arrays.asList(res);
    }

    private void swap(int i, int j) {
        T temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    @Override
    public boolean hasNext() {
        return this.array != null;
    }

    @Override
    public Iterator<Collection<T>> iterator() {
        return this;
    }

    public long count() {
        return CombinatoriaUtils.factorial(this.array.length);
    }
}