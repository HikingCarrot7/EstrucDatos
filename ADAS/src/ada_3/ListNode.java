package ada_3;

/**
 *
 * @author A15001169
 * @param <E>
 */
public class ListNode<E>
{

    private E dato;
    private ListNode<E> next;

    public ListNode(E dato)
    {
        this.dato = dato;
    }

    public ListNode(E dato, ListNode<E> next)
    {
        this.dato = dato;
        this.next = next;
    }

    public ListNode<E> getNext()
    {
        return next;
    }

    public void setNext(ListNode<E> next)
    {
        this.next = next;
    }

    public E getDato()
    {
        return dato;
    }

    public void setDato(E dato)
    {
        this.dato = dato;
    }

}
