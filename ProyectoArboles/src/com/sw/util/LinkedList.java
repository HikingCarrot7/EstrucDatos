package com.sw.util;

/**
 * @author A15001169
 * @param <E>
 */
public class LinkedList<E> implements List<E>, Predecessor<E>
{

    protected ListNode<E> first;
    protected int size;

    public LinkedList()
    {
        size = 0;
        first = null;
    }

    public LinkedList(LinkedList<E> lista)
    {
        ListNode<E> nodo = lista.first;

        while (nodo != null)
        {
            addLast(nodo.getItem());
            nodo = nodo.getNext();
        }

        size = lista.size;
    }

    public static <T extends Comparable<T>> LinkedList<T> removeDuplicates(LinkedList<T> lista, LinkedList<T> listaAAlmacenarDuplicados)
    {
        LinkedList<T> list = new LinkedList<>();
        list.setFirst(removeDuplicatesHelper(lista.first, listaAAlmacenarDuplicados));
        listaAAlmacenarDuplicados.setFirst(removeDuplicatesHelper(listaAAlmacenarDuplicados.first, new LinkedList<>()));
        return list;
    }

    private static <T extends Comparable<T>> ListNode<T> removeDuplicatesHelper(ListNode<T> head, LinkedList<T> listaAAlmacenarRepetidos)
    {
        if (head == null || head.getNext() == null)
            return head;

        head.setNext(removeDuplicatesHelper(head.getNext(), listaAAlmacenarRepetidos));

        if (head.getItem() == head.getNext().getItem())
            listaAAlmacenarRepetidos.addFirst(head.getItem());

        return head.getItem() == head.getNext().getItem() ? head.getNext() : head;
    }

    public static <T extends Comparable<T>> LinkedList<T> mergeLists(LinkedList<T> lista1, LinkedList<T> lista2)
    {
        LinkedList<T> mergedList = new LinkedList<>();
        mergedList.setFirst(mergeListsHelper(new LinkedList<>(lista1).first, new LinkedList<>(lista2).first));
        SortableLinkedList.insertionSort(mergedList);
        return mergedList;
    }

    private static <T extends Comparable<T>> ListNode<T> mergeListsHelper(ListNode<T> head1, ListNode<T> head2)
    {
        if (head1 == null)
            return head2;

        if (head2 == null)
            return head1;

        if (head1.getItem().compareTo(head2.getItem()) <= 0)
        {
            head1.setNext(mergeListsHelper(head1.getNext(), head2));
            return head1;

        } else
        {
            head2.setNext(mergeListsHelper(head2.getNext(), head1));
            return head2;
        }
    }

    @Override
    public E addFirst(E elemento)
    {
        first = new ListNode<>(isEmpty() ? null : first, elemento);
        size++;
        return elemento;
    }

    @Override
    public E addLast(E elemento)
    {
        if (isEmpty())
            first = new ListNode<>(elemento);

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

        E element = first.getItem();
        first = first.getNext();

        size--;
        return element;
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
            return null;

        E element = getLastNode().getItem();
        ListNode<E> node = first;

        for (int i = 0; i < size - 2; i++)
            node = node.getNext();

        if (node.getNext() != null)
            node.setNext(null);
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

        ListNode<E> nodo = first;
        String result = "[" + nodo.getItem().toString();

        while (nodo.getNext() != null)
        {
            nodo = nodo.getNext();
            result += "->" + nodo.getItem().toString();
        }

        return result + "]";
    }

    private ListNode<E> getLastNode()
    {
        ListNode<E> nodo = first;

        while (nodo.getNext() != null)
            nodo = nodo.getNext();

        return nodo;
    }

    public E first()
    {
        return first.getItem();
    }

    public void setFirst(ListNode<E> first)
    {
        this.first = first;
    }

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public ListNode<E> getNext()
    {
        return first;
    }

    @Override
    public void setNext(ListNode<E> next)
    {
        first = next;
    }

}
