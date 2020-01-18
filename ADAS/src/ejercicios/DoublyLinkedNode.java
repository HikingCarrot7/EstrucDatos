package ejercicios;

public class DoublyLinkedNode<E>
{

    private DoublyLinkedNode<E> next;
    private DoublyLinkedNode<E> prev;
    private E dato;

    public DoublyLinkedNode(E dato)
    {
        this.dato = dato;
    }

    public DoublyLinkedNode(DoublyLinkedNode<E> next, E dato)
    {
        this.next = next;
        this.dato = dato;
    }

    public DoublyLinkedNode(DoublyLinkedNode<E> next, DoublyLinkedNode<E> prev, E dato)
    {
        this.next = next;
        this.prev = prev;
        this.dato = dato;
    }

    public DoublyLinkedNode<E> getNext()
    {
        return next;
    }

    public void setNext(DoublyLinkedNode<E> next)
    {
        this.next = next;
    }

    public DoublyLinkedNode<E> getPrev()
    {
        return prev;
    }

    public void setPrev(DoublyLinkedNode<E> prev)
    {
        this.prev = prev;
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
