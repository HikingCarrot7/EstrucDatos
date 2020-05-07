package com.sw.model.util;

/**
 *
 * @author HikingCarrot7
 * @param <E>
 */
public interface Predecessor<E>
{

    public ListNode<E> getNext();

    public void setNext(ListNode<E> next);

}
