package util;

public class DequeList<E> extends DoublyLinkedList<E> implements Deque<E>
{

    private DoublyLinkedNode<E> last;

    @Override
    public E insertFirst(E element)
    {
        if (isEmpty())
            first = last = new DoublyLinkedNode<>(element);

        else
        {
            DoublyLinkedNode<E> firstNode = new DoublyLinkedNode<>(first, element);
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
            first = last = new DoublyLinkedNode<>(element);

        else
        {
            DoublyLinkedNode<E> lastNode = new DoublyLinkedNode<>(element);
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

        E element = first.getDato();
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

        E element = last.getDato();
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

        DoublyLinkedNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += " -> " + nodo.getDato().toString();
        }

        return result;
    }

    @Override
    public E first() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        return first.getDato();
    }

    @Override
    public E last() throws DequeEmptyException
    {
        if (isEmpty())
            throw new DequeEmptyException();

        return last.getDato();
    }

}
