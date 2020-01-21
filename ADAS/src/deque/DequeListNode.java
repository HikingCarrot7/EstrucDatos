package deque;

public class DequeListNode<E>
{

    private E element;
    private DequeListNode<E> next;
    private DequeListNode<E> prev;

    public DequeListNode(E element)
    {
        this.element = element;
    }

    public DequeListNode(E element, DequeListNode<E> next)
    {
        this.element = element;
        this.next = next;
    }

    public DequeListNode(E element, DequeListNode<E> next, DequeListNode<E> prev)
    {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public E getElement()
    {
        return element;
    }

    public void setElement(E element)
    {
        this.element = element;
    }

    public DequeListNode<E> getNext()
    {
        return next;
    }

    public void setNext(DequeListNode<E> next)
    {
        this.next = next;
    }

    public DequeListNode<E> getPrev()
    {
        return prev;
    }

    public void setPrev(DequeListNode<E> prev)
    {
        this.prev = prev;
    }

}
