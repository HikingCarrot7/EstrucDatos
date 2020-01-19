package deque;

public class DequeList<E> implements Deque<E>
{
    private DequeListNode<E> first;
    private DequeListNode<E> last;
    private int size;

    @Override
    public E insertFirst(E element)
    {
        if (isEmpty())
            first = last = new DequeListNode<>(element);

        else
        {
            DequeListNode<E> firstNode = new DequeListNode<>(element, first);
            first.setPrev(firstNode);
            first = firstNode;
        }

        size++;
        return element;
    }

    @Override
    public E insertLast(E element)
    {
        if (isEmpty())
            first = last = new DequeListNode<>(element);

        else
        {
            DequeListNode<E> lastNode = new DequeListNode<>(element);
            last.setNext(lastNode);
            lastNode.setPrev(last);
            last = lastNode;
        }

        size++;
        return element;
    }

    @Override
    public E removeFirst() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        E element = first.getElement();
        first = first.getNext();

        if (first == null)
            last = null;
        else
            first.setPrev(null);

        size--;
        return element;
    }

    @Override
    public E removeLast() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        E element = last.getElement();
        last = last.getPrev();

        if (last == null)
            first = null;
        else
            last.setNext(null);

        size--;
        return element;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "La cola está vacía.";

        DequeListNode<E> nodo = first;
        String result = nodo.getElement().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += " -> " + nodo.getElement().toString();
        }

        return result;
    }

    public String reversed()
    {
        if (isEmpty())
            return "La cola está vacía.";

        DequeListNode<E> nodo = last;
        String result = nodo.getElement().toString();

        while (nodo.getPrev() != null)
        {
            nodo = nodo.getPrev();
            result += " <- " + nodo.getElement().toString();
        }

        return result;
    }

    @Override
    public E first() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        return first.getElement();
    }

    @Override
    public E last() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        return last.getElement();
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public int size()
    {
        return size;
    }

}
