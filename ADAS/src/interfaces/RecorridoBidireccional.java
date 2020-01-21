package interfaces;

import nodos.DoublyLinkedNode;

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
