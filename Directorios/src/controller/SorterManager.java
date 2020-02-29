package controller;

import javafx.collections.ObservableList;
import model.BubbleSort;
import model.InsertionSort;

/**
 *
 * @author HikingCarrot7
 */
public class SorterManager
{

    public static final int CLAVE_BURBUJA = 0;
    public static final int CLAVE_INSERCION = 1;
    public static final int CLAVE_SHELL_SORT = 2;
    public static final int CLAVE_MERGE_SORT = 3;
    public static final int CLAVE_QUICK_SORT = 4;
    public static final int CLAVE_MEZCLA_DIRECTA = 5;

    public static void ordenarPor(int clave, ObservableList<Directorio> directorios)
    {
        switch (clave)
        {
            case CLAVE_INSERCION:
                InsertionSort.insertionSort(directorios);
                break;

            case CLAVE_BURBUJA:
                BubbleSort.bubbleSort(directorios);
                break;

            default:
                throw new AssertionError();
        }

    }

}
