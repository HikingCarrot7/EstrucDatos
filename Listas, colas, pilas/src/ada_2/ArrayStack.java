package ada_2;

import excepciones.PilaVaciaException;
import excepciones.PilaLlenaException;
import interfaces.Stack;
import java.util.Arrays;

/**
 * @param <E>
 * @author A15001169
 */
public class ArrayStack<E> implements Stack<E>
{

    private E top;
    private int size;
    private E[] elementos;

    @SuppressWarnings("unchecked")
    public ArrayStack(int elementosMaximos)
    {
        elementos = (E[]) new Object[elementosMaximos];
    }

    @SuppressWarnings("unchecked")
    public ArrayStack()
    {
        elementos = (E[]) new Object[10];
    }

    @Override
    public E push(E element)
    {

        if (isFull())
            throw new PilaLlenaException();

        top = element;
        elementos[size++] = element;

        return element;

    }

    @Override
    public E pop()
    {

        if (isEmpty())
            throw new PilaVaciaException();

        E element = elementos[--size];
        top = size == 0 ? null : elementos[size - 1];
        elementos[size] = null;

        return element;

    }

    @Override
    public E peek()
    {

        if (isEmpty())
            throw new PilaVaciaException();

        return top;

    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
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
