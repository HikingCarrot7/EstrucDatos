package ada_2;

import java.util.Arrays;

/**
 * @param <E>
 * @author A15001169
 */
public class Pila<E>
{

    private E top;
    private int size;
    private E[] elementos;
    
    @SuppressWarnings("unchecked")
    public Pila(int elementosMaximos)
    {
        elementos = (E[]) new Object[elementosMaximos];
    }


    @SuppressWarnings("unchecked")
	public Pila()
    {
        elementos = (E[]) new Object[10];
    }

    public E push(E element)
    {

        if (isFull())
            throw new PilaLlenaException();

        top = element;
        elementos[size++] = element;

        return element;

    }

    public E pop()
    {

        if (isEmpty())
            throw new PilaVaciaException();

        E element = elementos[--size];
        top = size == 0 ? null : elementos[size - 1];
        elementos[size] = null;

        return element;

    }

    public E peek()
    {

        if (isEmpty())
            throw new PilaVaciaException();

        return top;

    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    public boolean isFull()
    {
        return size == elementos.length;
    }

    @Override
    public String toString()
    {
        return Arrays.asList(elementos).toString();
    }

}
