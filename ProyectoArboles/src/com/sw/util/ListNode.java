package com.sw.util;

/**
 *
 * @author A15001169
 */
public class ListNode<E> extends Node<E> implements RecorridoHaciaAdelante<E>
{

    private ListNode<E> next;

    public ListNode(E dato)
    {
        super(dato);
    }

    public ListNode(ListNode<E> next, E dato)
    {
        super(dato);
        this.next = next;
    }

    @Override
    public ListNode<E> getNext()
    {
        return next;
    }

    public void setNext(ListNode<E> next)
    {
        this.next = next;
    }

}
