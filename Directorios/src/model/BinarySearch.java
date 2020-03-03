package model;

import controller.Directorio;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class BinarySearch
{

    public static int binarySearch(List<Directorio> directorios, String dato)
    {
        int n = directorios.size();
        int centro, inf = 0, sup = n - 1;

        while (inf <= sup)
        {
            centro = (sup + inf) / 2;

            if (directorios.get(centro).getNombre().toLowerCase().equals(dato.toLowerCase()))
                return centro;

            else if (dato.toLowerCase().compareTo(directorios.get(centro).getNombre().toLowerCase()) < 0)
                sup = centro - 1;

            else
                inf = centro + 1;
        }

        return -1;
    }

}
