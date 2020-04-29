package bubblesort;

import java.util.Arrays;

/**
 *
 * @author A15001169
 */
public class BubbleSort
{

    public static void main(String[] args)
    {
        Integer[] n = new Integer[]
        {
            56, 78, 12, 34, 89, 76, 34, 12, 67, 45
        };

        System.out.println("El número de comparaciones es: " + bubbleSort(n));
        System.out.println(Arrays.toString(n));
    }

    public static <T extends Comparable<T>> int bubbleSort(T[] array)
    {
        boolean isSorted = false;
        int comparaciones = 0;

        while (!isSorted)
        {
            int lastSorted = array.length - 1;
            isSorted = true;

            for (int i = 0; i < lastSorted; i++)
            {

                comparaciones++;
                if (array[i].compareTo(array[i + 1]) > 0)
                {
                    swap(array, i, i + 1);
                    isSorted = false;
                }

            }

            lastSorted--;
        }

        return comparaciones;
    }

    public static <T> void swap(T[] array, int i, int j)
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
