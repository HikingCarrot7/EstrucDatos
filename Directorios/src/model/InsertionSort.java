package model;

import java.util.List;
import test.FunnyStuff;

/**
 *
 * @author HikingCarrot7
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class InsertionSort
{

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
