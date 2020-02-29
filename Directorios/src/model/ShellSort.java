package model;

/**
 *
 * @author HikingCarrot7
 */
public class ShellSort
{

    public static <E extends Comparable<E>> void shellsort(E[] arreglo)
    {
        int intervalo = arreglo.length;
        boolean band;

        while (intervalo > 0)
        {
            intervalo /= 2;
            band = true;

            while (band)
            {
                band = false;
                int i = 0;
                while ((i + intervalo) <= arreglo.length - 1)
                {
                    if (arreglo[i].compareTo(arreglo[i + intervalo]) > 0)
                    {
                        E aux = arreglo[i];
                        arreglo[i] = arreglo[i + intervalo];
                        arreglo[i + intervalo] = aux;

                        band = true;
                    }

                    i++;
                }

            }

        }

    }

}
