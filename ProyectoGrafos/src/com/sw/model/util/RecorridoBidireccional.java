package com.sw.model.util;

/**
 *
 * @author A15001169
 * @param <E>
 */
public interface RecorridoBidireccional<E>
{

    public DoublyLinkedNode<E> getNext();

    public DoublyLinkedNode<E> getPrev();

}
