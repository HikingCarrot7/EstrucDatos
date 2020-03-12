package controller;

import java.util.Arrays;
import javafx.collections.ObservableList;
import model.BubbleSort;
import model.DAODirectorios;
import model.InsertionSort;
import model.MergeSort;
import model.MezclaDirecta;
import model.QuickSort;
import model.ShellSort;
import test.FunnyStuff;

/**
 *
 * @author HikingCarrot7
 */
@FunnyStuff(descripcion = "Funny stuff :)")
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
        try
        {
            switch (clave)
            {
                case CLAVE_INSERCION:
                    InsertionSort.insertionSort(directorios);
                    break;

                case CLAVE_BURBUJA:
                    BubbleSort.bubbleSort(directorios);
                    break;

                case CLAVE_SHELL_SORT:
                    ShellSort.shellsort(directorios);
                    break;

                case CLAVE_MERGE_SORT:
                    Directorio[] arrayDirectorios = getArrayDirectorios(directorios);
                    directorios.clear();
                    directorios.setAll(Arrays.asList(MergeSort.mergeSort(arrayDirectorios)));
                    break;

                case CLAVE_QUICK_SORT:
                    QuickSort.quicksort(directorios);
                    break;

                case CLAVE_MEZCLA_DIRECTA:
                    DAODirectorios dao = new DAODirectorios();
                    dao.guardarDirectorios(directorios);
                    MezclaDirecta.sort(dao.getFile(), directorios.size());
                    directorios.clear();
                    directorios.addAll(dao.getDirectorios());
                    break;

                default:
                    throw new AssertionError();
            }

        } catch (InterruptedException ex)
        {
            Runtime.getRuntime().gc();
            directorios.clear();
            System.out.println(ex.getMessage());
        }

    }

    private static Directorio[] getArrayDirectorios(ObservableList<Directorio> directorios)
    {
        Directorio[] dir = new Directorio[directorios.size()];

        for (int i = 0; i < directorios.size(); i++)
            dir[i] = directorios.get(i);

        return dir;
    }

}
