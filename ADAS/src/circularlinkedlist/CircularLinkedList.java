package circularlinkedlist;

import ada_4.DoublyLinkedList;
import excepciones.ListaVaciaException;
import interfaces.List;
import nodos.ListNode;

/**
 *
 * @author HikingC7
 * @param <E>
 */
public class CircularLinkedList<E> extends DoublyLinkedList<E> implements List<E>
{

    private ListNode<E> first;
    private int size;

    public CircularLinkedList()
    {
        size = 0;
    }

    @Override
    public E addFirst(E element)
    {
        if (isEmpty())
        {
            first = new ListNode<>(element);
            first.setNext(first);

        } else
        {
            ListNode<E> firstNode = new ListNode<>(first, element);
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
            first = new ListNode<>(element);
            first.setNext(first);

        } else
            getLastNode().setNext(new ListNode<>(first, element));

        size++;
        return element;
    }

    @Override
    public E removeFirst()
    {
        if (isEmpty())
            throw new ListaVaciaException();

        E element = first.getDato();
        ListNode<E> lastNode = getLastNode();

        if (lastNode == first)
            first = null;

        else
        {
            first = first.getNext();
            lastNode.setNext(first);
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
        ListNode<E> node = first;

        for (int i = 0; i < size - 2; i++)
            node = node.getNext();

        if (node == first)
            first = null;
        else
            node.setNext(first);

        size--;
        return element;
    }

    private ListNode<E> getLastNode()
    {
        ListNode<E> node = first;

        while (node.getNext() != first)
            node = node.getNext();

        return node;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";

        int contador = 0;
        ListNode<E> nodo = first;
        String result = "->" + nodo.getDato().toString();

        while (nodo.getNext() != first)
        {
            nodo = nodo.getNext();
            result += "->" + nodo.getDato().toString();
            contador += nodo.getDato().toString().length() + 2;
        }

        result += "-\n|";

        for (int i = 0; i < contador + 8; i++)
            result += "_";

        return result + "|\n";
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

}
