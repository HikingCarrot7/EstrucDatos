package quicksort;

/**
 *
 * @author A15001169
 */
public class QuickSort
{

    public static void main(String a[])
    {
        Integer array[] =
        {
            12, 9, 4, 99, 120, 1, 3, 10, 13
        };

        quicksort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quicksort(E[] array, int left, int right)
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

        quicksort(array, left, lo);
        quicksort(array, lo == left ? lo + 1 : lo, right);
    }

}
