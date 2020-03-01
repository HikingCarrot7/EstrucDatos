package controller;

import java.io.File;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author HikingCarrot7
 */
public class DirectoryManager
{

    public void obtenerTodosArchivos(ObservableList<Directorio> directorios, File file) throws InterruptedException
    {
        for (String direc : file.list())
        {
            if (Thread.currentThread().isInterrupted())
            {
                System.out.println("El hilo ha sido interrumpido");
                throw new InterruptedException("La operación para la búsqueda de directorios fue cancelada.");
            }

            File f = new File(file.getAbsolutePath(), direc);

            if (f.isDirectory())
                obtenerTodosArchivos(directorios, f);

            else
                directorios.add(new Directorio(f.getName(), f.getAbsolutePath(), new Date(f.lastModified()).toString()));
        }

    }

    public void obtenerArchivosDirectorio(ObservableList<Directorio> directorios, File file)
    {
        for (String direc : file.list())
        {
            File f = new File(file.getAbsolutePath(), direc);
            directorios.add(new Directorio(f.getName(), f.getAbsolutePath(), new Date(f.lastModified()).toString()));
        }
    }

}
