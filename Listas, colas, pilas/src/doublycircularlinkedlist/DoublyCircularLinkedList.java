package doublycircularlinkedlist;

import circularlinkedlist.CircularLinkedList;
import excepciones.ListaVaciaException;
import nodos.DoublyLinkedNode;

/**
 *
 * @author A15001169
 */
public class DoublyCircularLinkedList<E> extends CircularLinkedList<E>
{

    private DoublyLinkedNode<E> first;

    @Override
    public E addFirst(E element)
    {
        if (isEmpty())
        {
            first = new DoublyLinkedNode<>(element);
            first.setNext(first);
            first.setPrev(first);

        } else
        {
            DoublyLinkedNode<E> firstNode = new DoublyLinkedNode<>(first, getLastNode(), element);
            getLastNode().setNext(firstNode);
            first = firstNode;
        }

        size++;
        return element;
    }

    @Override
    public E addLast(E element)
    {
        if (isEmpty())
        {
            first = new DoublyLinkedNode<>(element);
            first.setNext(first);
            first.setPrev(first);

        } else
        {
            DoublyLinkedNode<E> lastNode = new DoublyLinkedNode<>(first, getLastNode(), element);
            getLastNode().setNext(lastNode);
            first.setPrev(lastNode);
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

        if (size == 1)
            first = null;

        else
        {
            first.getNext().setPrev(first.getPrev());
            first.getPrev().setNext(first.getNext());
            first = first.getNext();
        }

        size--;
        return element;
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
            throw new ListaVaciaException();

        E element = getLastNode().getDato();

        if (size == 1)
            first = null;

        else
        {
            getLastNode().getPrev().setNext(first);
            first.setPrev(getLastNode().getPrev());
        }

        size--;
        return element;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";

        int contador = 0;
        DoublyLinkedNode<E> nodo = first;
        String result = "=>" + nodo.getDato().toString();

        while (nodo.getNext() != first)
        {
            nodo = nodo.getNext();
            result += " <=> " + nodo.getDato().toString();
            contador += nodo.getDato().toString().length() + 2;
        }

        result += "=\n|";

        for (int i = 0; i < contador + 18; i++)
            result += "_";

        return result + "|\n";
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

    private DoublyLinkedNode<E> getLastNode()
    {
        return first.getPrev();
    }

}
