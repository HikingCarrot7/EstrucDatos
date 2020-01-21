package ada_3;

import interfaces.List;
import nodos.ListNode;

/**
 * @author A15001169
 * @param <E>
 */
public class LinkedList<E> implements List<E>
{

    private ListNode<E> front;
    private int size;

    public LinkedList()
    {
        size = 0;
        front = null;
    }

    @Override
    public E addFirst(E elemento)
    {
        if (isEmpty())
            front = new ListNode<>(elemento);
        else
            front = new ListNode<>(front, elemento);

        size++;
        return elemento;
    }

    @Override
    public E addLast(E elemento)
    {
        if (isEmpty())
            front = new ListNode<>(elemento);

        else
            getLastNode().setNext(new ListNode<>(elemento));

        size++;
        return elemento;
    }

    @Override
    public E removeFirst()
    {
        if (isEmpty())
            return null;

        E element = front.getDato();
        front = front.getNext();

        size--;
        return element;
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
            return null;

        E element = getLastNode().getDato();
        ListNode<E> node = front;

        for (int i = 0; i < size - 2; i++)
            node = node.getNext();

        if (node.getNext() != null)
            node.setNext(null);
        else
            front = null;

        size--;
        return element;
    }

    public static <T extends Comparable<T>> LinkedList<T> mergeLists(LinkedList<T> lista1, LinkedList<T> lista2)
    {
        LinkedList<T> mergedList = new LinkedList<>();
        mergedList.setFront(mergeListsHelper(lista1.front(), lista2.front()));
        return mergedList;
    }

    private static <T extends Comparable<T>> ListNode<T> mergeListsHelper(ListNode<T> nodo1, ListNode<T> nodo2)
    {
        if (nodo1 == null)
            return nodo2;

        if (nodo2 == null)
            return nodo1;

        if (nodo1.getDato().compareTo(nodo2.getDato()) <= 0)
        {
            nodo1.setNext(mergeListsHelper(nodo1.getNext(), nodo2));
            return nodo1;

        } else
        {
            nodo2.setNext(mergeListsHelper(nodo2.getNext(), nodo1));
            return nodo2;
        }

    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";

        ListNode<E> nodo = front;
        String result = nodo.getDato().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += "->" + nodo.getDato().toString();
        }

        return result;
    }

    private ListNode<E> getLastNode()
    {
        ListNode<E> nodo = front;

        while (nodo.getNext() != null)
            nodo = nodo.getNext();

        return nodo;
    }

    public ListNode<E> front()
    {
        return front;
    }

    public void setFront(ListNode<E> front)
    {
        this.front = front;
    }

    @Override
    public boolean isEmpty()
    {
        return front == null;
    }

    public int size()
    {
        return size;
    }

}
