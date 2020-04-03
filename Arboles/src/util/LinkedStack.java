package util;

/**
 *
 * @author A15001169
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E>
{

    private final LinkedList<E> lista;

    public LinkedStack()
    {
        lista = new LinkedList<>();
    }

    @Override
    public E push(E element)
    {
        return lista.addFirst(element);
    }

    @Override
    public E pop() throws PilaVaciaException
    {
        if (lista.isEmpty())
            throw new PilaVaciaException();

        return lista.removeFirst();
    }

    @Override
    public E peek() throws PilaVaciaException
    {
        if (lista.isEmpty())
            throw new PilaVaciaException();

        return lista.first();
    }

    @Override
    public boolean isEmpty()
    {
        return lista.isEmpty();
    }

    @Override
    public int size()
    {
        return lista.size();
    }

    @Override
    public String toString()
    {
        return lista.toString();
    }

}
