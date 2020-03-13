package model;

import java.util.List;
import test.FunnyStuff;

/**
 *
 * @author A15001169
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class QuickSort
{

    public static <E extends Comparable<E>> void quicksort(List<E> array) throws InterruptedException
    {
        if (array.isEmpty())
            return;

        quicksortHelper(array, 0, array.size() - 1);
    }

    protected static <E extends Comparable<E>> void quicksortHelper(List<E> array, int izq, int der) throws InterruptedException
    {
        if (Thread.currentThread().isInterrupted())
            throw new InterruptedException("El ordenamiento fue cancelado.");

        E pivote = array.get(izq); // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda

        while (i < j)
        {            // mientras no se crucen las búsquedas
            while (array.get(i).compareTo(pivote) <= 0 && i < j)
                i++; // busca elemento mayor que pivote

            while (array.get(j).compareTo(pivote) > 0)
                j--;         // busca elemento menor que pivote

            if (i < j)                      // si no se han cruzado
                swap(array, i, j);
        }

        array.set(izq, array.get(j));
        array.set(j, pivote);

        if (izq < j - 1)
            quicksortHelper(array, izq, j - 1); // ordenamos subarray izquierdo

        if (j + 1 < der)
            quicksortHelper(array, j + 1, der); // ordenamos subarray derecho
    }

    public static <T> void swap(List<T> array, int i, int j)
    {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

}
