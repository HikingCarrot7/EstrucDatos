package model;

import java.util.List;

/**
 *
 * @author A15001169
 */
public class QuickSort
{

    public static <E extends Comparable<E>> List<E> quicksort(List<E> array) throws InterruptedException
    {
        return quicksortHelper(array, 0, array.size() - 1);
    }

    protected static <E extends Comparable<E>> List<E> quicksortHelper(List<E> array, int izq, int der) throws InterruptedException
    {
        if (Thread.currentThread().isInterrupted())
            throw new InterruptedException("El ordenamiento fue cancelado.");

        if (izq >= der)
            return array;

        int i = izq, d = der;

        if (izq != der)
        {
            int pivote = izq;

            while (izq != der)
            {
                while (array.get(der).compareTo(array.get(pivote)) >= 0 && izq < der)
                    der--;

                while (array.get(izq).compareTo(array.get(pivote)) < 0 && izq < der)
                    izq++;

                if (der != izq)
                    swap(array, der, izq);
            }

            if (izq == der)
            {
                quicksortHelper(array, i, izq - 1);
                quicksortHelper(array, izq + 1, d);
            }

        } else
            return array;

        return array;
    }

    public static <T> void swap(List<T> array, int i, int j)
    {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

}
