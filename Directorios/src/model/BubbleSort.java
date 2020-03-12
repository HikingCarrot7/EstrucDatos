package model;

import java.util.List;
import test.FunnyStuff;

/**
 *
 * @author A15001169
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class BubbleSort
{

    public static <E extends Comparable<E>> void bubbleSort(List<E> array) throws InterruptedException
    {
        boolean swapped;
        int n = array.size();

        for (int i = 0; i < n - 1; i++)
        {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++)
                if (array.get(j).compareTo(array.get(j + 1)) > 0)
                {
                    swap(array, j, j + 1);
                    swapped = true;
                }

            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("El ordenamiento fue cancelado.");

            if (!swapped)
                break;
        }
    }

    public static <E> void swap(List<E> array, int i, int j)
    {
        E temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

}
