package controller;

import java.io.File;
import java.util.Date;
import javafx.collections.ObservableList;
import test.FunnyStuff;

/**
 *
 * @author HikingCarrot7
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class DirectoryManager
{

    public void obtenerTodosArchivos(ObservableList<Directorio> directorios, File file) throws InterruptedException
    {
        for (String direc : file.list())
        {
            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("La operación para la búsqueda de directorios fue cancelada.");

            File f = new File(file.getAbsolutePath(), direc);

            if (f.isDirectory())
                obtenerTodosArchivos(directorios, f);

            else
                directorios.add(new Directorio(f.getName(), f.getAbsolutePath(), new Date(f.lastModified()).toString()));
        }

    }

    public void obtenerArchivosDirectorio(ObservableList<Directorio> directorios, File file) throws InterruptedException
    {
        for (String direc : file.list())
        {
            if (Thread.currentThread().isInterrupted())
                throw new InterruptedException("La operación para la búsqueda de directorios fue cancelada.");

            File f = new File(file.getAbsolutePath(), direc);
            directorios.add(new Directorio(f.getName(), f.getAbsolutePath(), new Date(f.lastModified()).toString()));
        }
    }

}
