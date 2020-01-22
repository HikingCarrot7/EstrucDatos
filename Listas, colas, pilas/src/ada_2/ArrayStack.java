package ada_2;

import excepciones.PilaLlenaException;
import excepciones.PilaVaciaException;
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
    private final E[] ELEMENTOS;

    @SuppressWarnings("unchecked")
    public ArrayStack(int elementosMaximos)
    {
        ELEMENTOS = (E[]) new Object[elementosMaximos];
    }

    @SuppressWarnings("unchecked")
    public ArrayStack()
    {
        ELEMENTOS = (E[]) new Object[10];
    }

    @Override
    public E push(E element)
    {

        if (isFull())
            throw new PilaLlenaException();

        top = element;
        ELEMENTOS[size++] = element;

        return element;

    }

    @Override
    public E pop()
    {

        if (isEmpty())
            throw new PilaVaciaException();

        E element = ELEMENTOS[--size];
        top = size == 0 ? null : ELEMENTOS[size - 1];
        ELEMENTOS[size] = null;

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
        return size == ELEMENTOS.length;
    }

    @Override
    public String toString()
    {
        return Arrays.asList(ELEMENTOS).toString();
    }

}
