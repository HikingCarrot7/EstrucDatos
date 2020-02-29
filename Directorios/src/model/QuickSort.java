package model;

import java.util.List;

/**
 *
 * @author A15001169
 */
public class QuickSort
{

    public static <E extends Comparable<E>> void quicksort(List<E> array)
    {
        quicksortHelper(array, 0, array.size() - 1);
    }

    public static <E extends Comparable<E>> void quicksortHelper(E[] array, int left, int right)
    {
        int lo = left;
        int hi = right;

        if (lo >= right)
            return;

        E midPoint = array[(lo + hi) / 2];

        while (lo < hi)
        {
            while (lo < hi && array[lo].compareTo(midPoint) < 0)
                lo++;

            while (lo < hi && array[hi].compareTo(midPoint) > 0)
                hi--;

            if (lo < hi)
            {
                E temp = array[lo];
                array[lo] = array[hi];
                array[hi] = temp;
            }
        }

        if (hi < lo)
            lo = hi;

        quicksortHelper(array, left, lo);
        quicksortHelper(array, lo == left ? lo + 1 : lo, right);
    }

    public static <E extends Comparable<E>> void quicksortHelper(List<E> array, int left, int right)
    {
        int lo = left;
        int hi = right;

        if (lo >= right)
            return;

        E midPoint = array.get((lo + hi) / 2);

        while (lo < hi)
        {
            while (lo < hi && array.get(lo).compareTo(midPoint) < 0)
                lo++;

            while (lo < hi && array.get(hi).compareTo(midPoint) > 0)
                hi--;

            if (lo < hi)
            {
                E temp = array.get(lo);
                array.set(lo, array.get(hi));
                array.set(hi, temp);
            }
        }

        if (hi < lo)
            lo = hi;

        QuickSort.quicksortHelper(array, left, lo);
        QuickSort.quicksortHelper(array, lo == left ? lo + 1 : lo, right);
    }

}
