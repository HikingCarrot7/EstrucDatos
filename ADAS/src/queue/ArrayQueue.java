package queue;

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
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int elementosMaximos)
    {
        elements = (E[]) new Object[elementosMaximos];
        size = 0;
    }

    @Override
    public E enqueue(E element)
    {
        return null;
    }

    @Override
    public E dequeue()
    {
        return null;
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

    @Override
    public int size()
    {
        return size;
    }

}
