package com.sw.model.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author HikingCarrot7
 * @param <E>
 */
public abstract class DAO<E>
{

    public static final String RUTA_HASHTABLE_DATA = "system_data/hashtable.txt";
    public static final String RUTA_USUARIOS_REGISTRADOS = "system_data/usuarios.txt";

    public static boolean esRutaValida(String ruta)
    {
        File file = new File(ruta);
        return file.exists();
    }

    public static void eliminarArchivo(String ruta)
    {
        File file = new File(ruta);
        file.delete();
    }

    public static String getParent(String ruta)
    {
        return new File(ruta).getParent();
    }

    protected final File file;

    public DAO(String ruta)
    {
        file = new File(ruta);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
    }

    public void saveObject(E object)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
        {
            out.writeObject(object);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public abstract E getSavedObject();

}
