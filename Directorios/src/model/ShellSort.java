package model;

import java.util.List;
import test.FunnyStuff;

/**
 *
 * @author HikingCarrot7
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class ShellSort
{

    public static <E extends Comparable<E>> void shellsort(List<E> array) throws InterruptedException
    {
        int intervalo = array.size();
        boolean band;

        while (intervalo > 0)
        {
            intervalo /= 2;
            band = true;

            while (band)
            {
                band = false;
                int i = 0;

                while ((i + intervalo) <= array.size() - 1)
                {
                    if (Thread.currentThread().isInterrupted())
                        throw new InterruptedException("El ordenamiento fue cancelado.");

                    if (array.get(i).compareTo(array.get(i + intervalo)) > 0)
                    {
                        E temp = array.get(i);
                        array.set(i, array.get(i + intervalo));
                        array.set(i + intervalo, temp);

                        band = true;
                    }

                    i++;
                }

            }

        }

    }

}
