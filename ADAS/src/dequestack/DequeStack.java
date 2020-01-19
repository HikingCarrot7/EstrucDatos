package dequestack;

import ada_2.Stack;
import deque.DequeList;

public class DequeStack<E> implements Stack<E>
{

    private DequeList<E> list;

    public DequeStack()
    {
        list = new DequeList<>();
    }

    @Override
    public E push(E element)
    {
        return list.insertLast(element);
    }

    @Override
    public E pop() throws StackEmptyException
    {
        if (isEmpty())
            throw new StackEmptyException();

        return list.removeLast();
    }

    @Override
    public E peek() throws StackEmptyException
    {
        if (isEmpty())
            throw new StackEmptyException();

        return list.last();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            throw new StackEmptyException();

        return list.toString();
    }
}
