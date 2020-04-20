package ada_7;

import excepciones.ColaLlenaException;
import excepciones.ColaVaciaException;
import interfaces.Queue;

public class CircularArrayQueue<E> implements Queue<E>
{

    private int front;
    private int size;
    private final E[] elements;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue()
    {
        elements = (E[]) new Object[10];
        size = 0;
        front = 0;
    }

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int elementosMaximos)
    {
        elements = (E[]) new Object[elementosMaximos];
        size = 0;
        front = 0;
    }

    @Override
    public E enqueue(E element) throws ColaLlenaException
    {
        if (isFull())
            throw new ColaLlenaException();

        elements[(front + size++) % elements.length] = element;
        return element;
    }

    @Override
    public E dequeue() throws ColaVaciaException
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
    public E front() throws ColaVaciaException
    {
        if (isEmpty())
            throw new ColaVaciaException();

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
        if (isEmpty())
            return "";

        String result = elements[front].toString();

        for (int i = (front + 1) % elements.length, j = 1; j < size; i = (i + 1) % elements.length, j++)
            result += " <- " + elements[i].toString();

        return result;
    }

}
