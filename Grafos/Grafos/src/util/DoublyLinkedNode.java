package util;

/**
 *
 * @author A15001169
 */
public class DoublyLinkedNode<E> extends Node<E> implements RecorridoBidireccional<E>
{

    private DoublyLinkedNode<E> next;
    private DoublyLinkedNode<E> prev;

    public DoublyLinkedNode(E dato)
    {
        super(dato);
    }

    public DoublyLinkedNode(DoublyLinkedNode<E> next, E dato)
    {
        super(dato);
        this.next = next;
    }

    public DoublyLinkedNode(DoublyLinkedNode<E> next, DoublyLinkedNode<E> prev, E dato)
    {
        super(dato);
        this.next = next;
        this.prev = prev;
    }

    @Override
    public DoublyLinkedNode<E> getNext()
    {
        return next;
    }

    public void setNext(DoublyLinkedNode<E> next)
    {
        this.next = next;
    }

    @Override
    public DoublyLinkedNode<E> getPrev()
    {
        return prev;
    }

    public void setPrev(DoublyLinkedNode<E> prev)
    {
        this.prev = prev;
    }

}
