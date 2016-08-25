import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Se llama combinaciones de m elementos tomados de n en n (m ≥ n) a todas las agrupaciones posibles que pueden hacerse con los m elementos
 * de forma que:<br>
 * <ul>
 * <li>No entran todos los elementos.</li>
 * <li>No importa el orden.</li>
 * <li>No se repiten los elementos.</li>
 *
 * @param <T>
 */
public class Combinaciones<E> implements Iterator<Collection<E>>, Iterable<Collection<E>> {
    private int[] indices;

    private final int[] maxIndices;

    private final Object[] elements;

    public Combinaciones(Collection<? extends E> items, int number) {
        if (items == null || number < 1 || items.size() < number) {
            throw new IllegalArgumentException("|items| >= number && number > 1");
        }
        this.elements = items.toArray();
        this.indices = new int[number];
        this.maxIndices = new int[number];
        for (int i = 0; i < number; i++) {
            this.indices[i] = i;
            this.maxIndices[i] = this.elements.length + i - this.indices.length;
        }
    }

    @Override
    public Iterator<Collection<E>> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.indices != null;
    }

    @SuppressWarnings("unchecked")
    private Collection<E> createFromIndices() {
        List<E> result = new ArrayList<>(this.indices.length * 2);
        for (int i = 0; i < this.indices.length; i++) {
            result.add((E) this.elements[this.indices[i]]);
        }
        return result;
    }

    private void incrementIndices() {
        if (this.indices[0] == this.maxIndices[0]) {
            this.indices = null;
            return;
        }
        for (int i = this.indices.length - 1; i >= 0; i--) {
            if (this.indices[i] != this.maxIndices[i]) {
                int val = ++this.indices[i];
                for (int j = i + 1; j < this.indices.length; j++) {
                    this.indices[j] = ++val;
                }
                break;
            }
        }
    }

    @Override
    public Collection<E> next() {
        if (this.indices == null) {
            throw new NoSuchElementException("End of iterator");
        }
        Collection<E> result = createFromIndices();
        incrementIndices();
        return result;
    }

    /**
     * Numero de combinaciones <br>
     * m!/n!(m-n)! <br>
     * m = Numero de elementos<br>
     * n = Tamano del subconjunto de elementos
     *
     * @return
     */
    public long count() {
        int m = this.elements.length;
        int n = this.maxIndices.length;
        return CombinatoriaUtils.factorial(m) / (CombinatoriaUtils.factorial(n) * CombinatoriaUtils.factorial(m - n));
    }

}
