package ada_3;

/**
 *
 * @author HikingC7
 */
public interface List<E>
{

    public E addFirst(E element);

    public E addLast(E element);

    public E removeFirst();

    public E removeLast();

    public boolean isEmpty();

}
