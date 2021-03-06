package util;

public class DoublyLinkedList<E> extends LinkedList<E>
{

    protected DoublyLinkedNode<E> first;

    public DoublyLinkedList()
    {
        size = 0;
    }

    @Override
    public E addLast(E element)
    {
        if (isEmpty())
            first = new DoublyLinkedNode<>(element);

        else
        {
            DoublyLinkedNode<E> lastNode = getLastNode();
            DoublyLinkedNode<E> nuevoNodo = new DoublyLinkedNode<>(element);
            lastNode.setNext(nuevoNodo);
            nuevoNodo.setPrev(lastNode);
        }

        size++;
        return element;
    }

    @Override
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

    @Override
    public E removeFirst()
    {
        if (isEmpty())
            throw new ListaVaciaException();

        E element = first.getDato();
        first = first.getNext();

        if (!isEmpty())
            first.setPrev(null);

        size--;
        return element;
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
            throw new ListaVaciaException();

        DoublyLinkedNode<E> lastNode = getLastNode();
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
            return "";

        DoublyLinkedNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += " <=> " + nodo.getDato().toString();
        }

        return result;
    }

    private DoublyLinkedNode<E> getLastNode()
    {
        if (isEmpty())
            throw new ListaVaciaException();

        DoublyLinkedNode<E> nodo = first;

        while (nodo.getNext() != null)
            nodo = nodo.getNext();

        return nodo;
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

}
