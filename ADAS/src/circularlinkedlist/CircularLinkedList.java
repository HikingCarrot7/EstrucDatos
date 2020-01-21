package circularlinkedlist;

import ada_3.List;
import ada_3.ListNode;
import ada_3.ListaVaciaException;

/**
 *
 * @author HikingC7
 */
public class CircularLinkedList<E> implements List<E>
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
            ListNode<E> firstNode = new ListNode<>(element, first);
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
            getLastNode().setNext(new ListNode<>(element, first));

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
        first = first.getNext();
        lastNode.setNext(first);
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

        ListNode<E> nodo = first;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != first)
        {
            System.out.println(nodo);
            nodo = nodo.getNext();
            result += "->" + nodo.getDato().toString();
        }

        return result + "->";
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

}
