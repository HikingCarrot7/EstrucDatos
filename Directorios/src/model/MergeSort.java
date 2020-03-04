package model;

/**
 *
 * @author A15001169
 */
public class MergeSort
{

    public static <E extends Comparable<E>> E[] mergeSort(E[] data) throws InterruptedException
    {
        return mergeSortHelper(data, 0, data.length - 1);
    }

    protected static <E extends Comparable<E>> E[] mergeSortHelper(E[] data, int bottom, int top) throws InterruptedException
    {
        if (Thread.currentThread().isInterrupted())
            throw new InterruptedException("El ordenamiento fue cancelado.");

        if (bottom == top)
            return (E[]) new Comparable[]
            {
                data[bottom]
            };

        else
        {
            int midpoint = (top + bottom) / 2;
            return merge(mergeSortHelper(data, bottom, midpoint), mergeSortHelper(data, midpoint + 1, top));
        }

    }

    protected static <E extends Comparable<E>> E[] merge(E[] a, E[] b)
    {
        E[] result = (E[]) new Comparable[a.length + b.length];

        int i = 0;
        int j = 0;

        for (int k = 0; k < result.length; k++)
            if (j == b.length || (i < a.length && a[i].compareTo(b[j]) <= 0))
                result[k] = a[i++];
            else
                result[k] = b[j++];

        return result;
    }

}
