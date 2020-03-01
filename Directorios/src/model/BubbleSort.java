package model;

import java.util.List;

/**
 *
 * @author A15001169
 */
public class BubbleSort
{

    public static <T extends Comparable<T>> void bubbleSort(T[] array) throws InterruptedException
    {
        boolean isSorted = false;

        while (!isSorted)
        {
            int lastSorted = array.length - 1;
            isSorted = true;

            for (int i = 0; i < lastSorted; i++)
                if (array[i].compareTo(array[i + 1]) > 0)
                {
                    swap(array, i, i + 1);
                    isSorted = false;
                }

            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("El ordenamiento fue cancelado.");

            lastSorted--;
        }

    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> array) throws InterruptedException
    {
        boolean isSorted = false;

        while (!isSorted)
        {
            int lastSorted = array.size() - 1;
            isSorted = true;

            for (int i = 0; i < lastSorted; i++)
                if (array.get(i).compareTo(array.get(i + 1)) > 0)
                {
                    swap(array, i, i + 1);
                    isSorted = false;
                }

            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("El ordenamiento fue cancelado.");

            lastSorted--;
        }

    }

    public static <T> void swap(T[] array, int i, int j)
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void swap(List<T> array, int i, int j)
    {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

}
