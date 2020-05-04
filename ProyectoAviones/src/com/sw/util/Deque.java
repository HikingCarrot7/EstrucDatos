package com.sw.util;

public interface Deque<E>
{

    public E first() throws DequeEmptyException;

    public E last() throws DequeEmptyException;

    public E insertLast(E element);

    public E insertFirst(E element);

    public E removeLast() throws DequeEmptyException;

    public E removeFirst() throws DequeEmptyException;

    public boolean isEmpty();

    public int size();
}
