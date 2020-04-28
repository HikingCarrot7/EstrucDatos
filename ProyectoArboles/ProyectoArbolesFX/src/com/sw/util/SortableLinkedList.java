package com.sw.util;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class SortableLinkedList<E extends Comparable<E>> extends LinkedList<E>
{

    protected void addInOrder(E target)
    {
        Predecessor<E> prev = this;
        ListNode<E> node = getNext();

        while (node != null && node.getDato().compareTo(target) < 0)
        {
            prev = node;
            node = node.getNext();
        }

        prev.setNext(new ListNode<>(node, target));
    }

    public static <T extends Comparable<T>> void insertionSort(LinkedList<T> listaAOrdenar)
    {
        SortableLinkedList<T> newList = new SortableLinkedList<>();

        while (!listaAOrdenar.isEmpty())
            newList.addInOrder(listaAOrdenar.removeFirst());

        listaAOrdenar.setFirst(newList.first);
    }
}
