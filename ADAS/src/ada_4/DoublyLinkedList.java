package ada_4;

import excepciones.ListaVaciaException;
import interfaces.List;
import nodos.DoublyLinkedNode;

public class DoublyLinkedList<E> implements List<E>
{

    private DoublyLinkedNode<E> first;
    private int size;

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
            DoublyLinkedNode<E> nodo = getLastNode();
            DoublyLinkedNode<E> nuevoNodo = new DoublyLinkedNode<>(element);
            nodo.setNext(nuevoNodo);
            nuevoNodo.setPrev(nodo);
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

    public String reversed()
    {
        if (isEmpty())
            return "";

        DoublyLinkedNode<E> nodo = getLastNode();
        String result = nodo.getDato().toString();

        while (nodo.getPrev() != null)
        {
            nodo = nodo.getPrev();
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

    public E getFirst()
    {
        return first.getDato();
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return size;
    }

}
