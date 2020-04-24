package arbolesavl;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <I>
 */
@FunctionalInterface
public interface Comparador<L, I>
{

    public int comparar(L lista, I item);
}
