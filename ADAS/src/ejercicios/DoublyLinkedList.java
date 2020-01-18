package ejercicios;

public class DoublyLinkedList<E>
{

    private DoublyLinkedNode<E> first;
    private int size;

    public DoublyLinkedList()
    {
        size = 0;
    }

    public E addLast(E element)
    {
        if (isEmpty())
            first = new DoublyLinkedNode<>(element);

        else
        {
            DoublyLinkedNode<E> nodo = getLast();

            DoublyLinkedNode<E> nuevoNodo = new DoublyLinkedNode<>(element);
            nodo.setNext(nuevoNodo);
            nuevoNodo.setPrev(nodo);
        }

        size++;

        return element;
    }

    public E addFirst(E element)
    {
        if (isEmpty())
            first = new DoublyLinkedNode<>(element);

        else
        {
            DoublyLinkedNode<E> nuevoNodo = new DoublyLinkedNode<>(first, element);
            first.setPrev(nuevoNodo);
            first = nuevoNodo;
        }

        size++;

        return element;
    }

    public E removeFirst()
    {
        if (isEmpty())
            return null;

        E element = first.getDato();
        first = first.getNext();

        if (!isEmpty())
            first.setPrev(null);

        size--;
        return element;
    }

    public E removeLast()
    {
        if (isEmpty())
            return null;

        DoublyLinkedNode<E> lastNode = getLast();
        E element = lastNode.getDato();

        if (lastNode.getPrev() != null)
            lastNode.getPrev().setNext(null);
        else
            first = null;

        size--;
        return element;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "La lista está vacía.";

        DoublyLinkedNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += " -> " + nodo.getDato().toString();
        }

        return result;
    }

    public String reversed()
    {
        if (isEmpty())
            return "La lista está vacía.";

        DoublyLinkedNode<E> nodo = getLast();
        String result = nodo.getDato().toString();

        while (nodo.getPrev() != null)
        {
            nodo = nodo.getPrev();
            result += " <- " + nodo.getDato().toString();
        }

        return result;
    }

    private DoublyLinkedNode<E> getLast()
    {
        if (isEmpty())
            return null;

        DoublyLinkedNode<E> nodo = first;

        while (nodo.getNext() != null)
            nodo = nodo.getNext();

        return nodo;
    }

    public E getFirst()
    {
        return first.getDato();
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
