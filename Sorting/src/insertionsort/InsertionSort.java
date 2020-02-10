package insertionsort;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class InsertionSort
{

    public static <E extends Comparable<E>> void insertionSort(E[] data)
    {
        for (int i = 1; i < data.length; i++)
        {
            E target = data[i];
            int j;

            for (j = i - 1; j >= 0 && data[j].compareTo(target) > 0; j--)
                data[j + 1] = data[j];

            data[j + 1] = target;
        }

    }

    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> data)
    {
        for (int i = 1; i < data.size(); i++)
        {
            E target = data.get(i);
            int j;

            for (j = i - 1; j >= 0 && data.get(j).compareTo(target) > 0; j--)
                data.set(j + 1, data.get(j));

            data.set(j + 1, target);
        }

    }

}
