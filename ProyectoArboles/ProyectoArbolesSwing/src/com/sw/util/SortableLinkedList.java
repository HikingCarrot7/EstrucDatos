package com.sw.util;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public class SortableLinkedList<T extends Comparable<? super T>> extends LinkedList<T>
{

    protected void addInOrder(T target)
    {
        Predecessor<T> prev = this;
        ListNode<T> node = getNext();

        while (node != null && node.getDato().compareTo(target) < 0)
        {
            prev = node;
            node = node.getNext();
        }

        prev.setNext(new ListNode<>(node, target));
    }

    public static <T extends Comparable<? super T>> void insertionSort(LinkedList<T> listaAOrdenar)
    {
        SortableLinkedList<T> newList = new SortableLinkedList<>();

        while (!listaAOrdenar.isEmpty())
            newList.addInOrder(listaAOrdenar.removeFirst());

        listaAOrdenar.setFirst(newList.first);
    }
}
