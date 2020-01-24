package interfaces;

import excepciones.ColaLlenaException;
import excepciones.ColaVaciaException;

public interface Queue<E>
{

    public E enqueue(E element) throws ColaLlenaException;

    public E dequeue() throws ColaVaciaException;

    public E front() throws ColaVaciaException;

    public boolean isEmpty();

    public int size();
}
