package ada_3;

/**
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

    public E insertarAlInicio(E elemento)
    {
        if (isEmpty())
            first = new ListNode<>(elemento);
        else
            first = new ListNode<>(elemento, first);

        size++;
        return elemento;
    }

    public E insertarAlFinal(E elemento)
    {
        if (isEmpty())
            first = new ListNode<>(elemento);

        else
            getLastNode().setNext(new ListNode<>(elemento));

        size++;
        return elemento;
    }

    public E removeFirst()
    {
        if (isEmpty())
            return null;

        E element = first.getDato();
        first = first.getNext();

        size--;
        return element;
    }

    public E removeLast()
    {
        if (isEmpty())
            return null;

        E element = getLastNode().getDato();
        ListNode<E> node = first;

        for (int i = 0; i < size - 2; i++)
            node = node.getNext();

        if (node.getNext() != null)
            node.setNext(null);
        else
            first = null;

        size--;
        return element;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";

        ListNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += "->" + nodo.getDato().toString();
        }

        return result;
    }

    private ListNode<E> getLastNode()
    {
        ListNode<E> nodo = first;

        while (nodo.getNext() != null)
            nodo = nodo.getNext();

        return nodo;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return size;
    }

}
