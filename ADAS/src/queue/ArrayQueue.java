package queue;

import excepciones.ColaLlenaException;
import excepciones.ColaVaciaException;
import interfaces.Queue;
import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E>
{

    private int front;
    private int size;
    private E[] elements;

    @SuppressWarnings("unchecked")
    public ArrayQueue()
    {
        elements = (E[]) new Object[10];
        size = 0;
        front = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int elementosMaximos)
    {
        elements = (E[]) new Object[elementosMaximos];
        size = 0;
        front = 0;
    }

    @Override
    public E enqueue(E element)
    {
        if (isFull())
            throw new ColaLlenaException();

        elements[(front + size++) % elements.length] = element;
        return element;
    }

    @Override
    public E dequeue()
    {
        if (isEmpty())
            throw new ColaVaciaException();

        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public E front()
    {
        return elements[front];
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == elements.length;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public String toString()
    {
        return Arrays.asList(elements).toString();
    }

}
