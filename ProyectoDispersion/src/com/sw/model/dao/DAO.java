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

    public static final String RUTA_HASHTABLE_DATA = "hashtable_data/hashtable_data.txt";

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
        try
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
            {
                out.writeObject(object);
            }

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public abstract E getSavedObject();

}