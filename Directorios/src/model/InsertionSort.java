package model;

import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class InsertionSort
{

    public static <E extends Comparable<E>> void insertionSort(E[] data) throws InterruptedException
    {
        for (int i = 1; i < data.length; i++)
        {
            E target = data[i];
            int j;

            for (j = i - 1; j >= 0 && data[j].compareTo(target) > 0; j--)
                data[j + 1] = data[j];

            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("El ordenamiento fue cancelado.");

            data[j + 1] = target;
        }

    }

    public static <E extends Comparable<E>> void insertionSort(List<E> data) throws InterruptedException
    {
        for (int i = 1; i < data.size(); i++)
        {
            E target = data.get(i);
            int j;

            for (j = i - 1; j >= 0 && data.get(j).compareTo(target) > 0; j--)
                data.set(j + 1, data.get(j));

            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("El ordenamiento fue cancelado.");

            data.set(j + 1, target);
        }

    }

}
