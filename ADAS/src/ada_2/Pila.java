package ada_2;

import java.util.Arrays;

/**
 *
 * @author A15001169
 * @param <E>
 */
public class Pila<E>
{

    private E top;
    private int size;
    private E[] elementos;

    public Pila(int elementosMaximos)
    {
        elementos = (E[]) new Object[elementosMaximos];
    }

    public E push(E element)
    {

        if (isFull())
            throw new PilaLLenaException();

        top = element;
        elementos[size++] = element;

        return element;

    }

    public E pop()
    {

        E element = elementos[size - 1];
        top = elementos[--size];
        elementos[size] = null;

        return element;

    }

    public E peek()
    {

        if (estaVacia())
            throw new PilaVaciaException();

        return top;

    }

    public E top()
    {

        if (estaVacia())
            throw new PilaVaciaException();

        return top;

    }

    public int size()
    {
        return size;
    }

    public boolean estaVacia()
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
