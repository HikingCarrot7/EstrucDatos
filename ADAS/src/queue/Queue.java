package queue;

public interface Queue<E>
{

    public E enqueue(E element);

    public E dequeue();

    public E front();

    public boolean isEmpty();

    public int size();
}
