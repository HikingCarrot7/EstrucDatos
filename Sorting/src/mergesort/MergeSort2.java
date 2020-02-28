package mergesort;

import java.util.Arrays;

/**
 *
 * @author HikingCarrot7
 */
public class MergeSort2
{

    public static <E extends Comparable<E>> void mergesort(E[] array)
    {
        E[] temp = (E[]) new Comparable[array.length];

        mergesort(array, temp, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void mergesort(E[] array, E[] temp, int leftStart, int rightEnd)
    {
        if (leftStart >= rightEnd)
            return;

        int middle = (leftStart + rightEnd) / 2;

        mergesort(array, temp, leftStart, middle);
        mergesort(array, temp, middle + 1, rightEnd);
        mergeHalves(array, temp, leftStart, rightEnd);
    }

    public static <E extends Comparable<E>> void mergeHalves(E[] array, E[] temp, int leftStart, int rightEnd)
    {
        int leftIndex = leftStart;
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightIndex = leftEnd + 1;
        int tempIndex = leftStart;
        int size = rightEnd - leftStart + 1;

        while (leftIndex <= leftEnd && rightIndex <= rightEnd)
        {
            if (array[leftIndex].compareTo(array[rightIndex]) <= 0)
            {
                temp[tempIndex] = array[leftIndex];
                leftIndex++;

            } else
            {
                temp[tempIndex] = array[rightIndex];
                rightIndex++;
            }

            tempIndex++;
        }

        System.arraycopy(array, leftIndex, temp, tempIndex, leftEnd - leftIndex + 1);
        System.arraycopy(array, rightIndex, temp, tempIndex, rightEnd - rightIndex + 1);
        System.arraycopy(temp, leftStart, array, leftStart, size);
    }

    public static void main(String[] args)
    {
        Integer[] test = new Integer[]
        {
            5, 2, 34, 12, 52, 23, 2, 3, 3, 100, 50
        };

        String[] test2 = new String[]
        {
            "Nicolás", "Javier", "Eusebio"
        };

        MergeSort2.<String>mergesort(test2);
        System.out.println(Arrays.toString(test2));
    }

}
