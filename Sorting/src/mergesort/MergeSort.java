package mergesort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author A15001169
 */
public class MergeSort
{

    public static void main(String[] args)
    {
        String[] list = new String[]
        {
            "Javier", "Nicolás", "Guillermo", "Charly", "Eusebio", "Oscar", "Chomsky"
        };

        System.out.println(Arrays.toString(mergeSort(list)));

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .filter(x -> x >= 5)
                        .map(x -> x * 2)
                        .sorted()
                        .min(Comparator.comparing(Integer::valueOf))
                        .get());
    }

    public static <E extends Comparable<E>> E[] mergeSort(E[] data)
    {
        return mergeSortHelper(data, 0, data.length - 1);
    }

    protected static <E extends Comparable<E>> E[] mergeSortHelper(E[] data, int bottom, int top)
    {
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
