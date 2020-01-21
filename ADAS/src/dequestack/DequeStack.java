package dequestack;

import deque.DequeList;
import excepciones.PilaVaciaException;
import interfaces.Stack;

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
    public E pop() throws PilaVaciaException
    {
        if (isEmpty())
            throw new PilaVaciaException();

        return list.removeLast();
    }

    @Override
    public E peek() throws PilaVaciaException
    {
        if (isEmpty())
            throw new PilaVaciaException();

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
        return list.toString();
    }
}
