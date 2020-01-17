package ada_3;

/**
 *
 * @author A15001169
 * @param <E>
 */
public class LinkedList<E>
{

    private ListNode<E> first;
    private int size;

    public LinkedList()
    {
        size = 0;
        first = null;
    }

    public void insertarInicio(E elemento)
    {

        if (isEmpty())
            first = new ListNode<>(elemento);

        else
        {
            ListNode<E> nuevoNodo = new ListNode<>(elemento, first);
            first = nuevoNodo;
        }

        size++;

    }

    public void insertarAlFinal(E elemento)
    {

        if (isEmpty())
            first = new ListNode<>(elemento);

        else
        {

            ListNode<E> nodo = first;

            while (nodo.getNext() != null)
                nodo = nodo.getNext();

            nodo.setNext(new ListNode<>(elemento));
        }

        size++;

    }

    public boolean isEmpty()
    {
        return first == null;
    }

    @Override
    public String toString()
    {

        if (first == null)
            return "La lista está vacía.";

        ListNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += "->" + nodo.getDato().toString();
        }

        return result;

    }

    public int size()
    {
        return size;
    }

}
