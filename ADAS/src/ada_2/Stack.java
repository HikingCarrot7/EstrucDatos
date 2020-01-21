package ada_2;

import dequestack.StackEmptyException;

public interface Stack<E>
{

    public E push(E element);

    public E pop() throws StackEmptyException;

    public E peek() throws StackEmptyException;

    public boolean isEmpty();

    public int size();
}
