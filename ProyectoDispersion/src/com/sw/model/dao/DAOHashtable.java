package com.sw.model.dao;

import com.sw.model.hashtable.Hashtable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author HikingCarrot7
 */
@SuppressWarnings("unchecked")
public class DAOHashtable extends DAO<Hashtable<String, String>>
{

    public DAOHashtable(String ruta)
    {
        super(ruta);
    }

    @Override
    public Hashtable<String, String> getSavedObject()
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
        {
            return (Hashtable<String, String>) in.readObject();

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new Hashtable<>();
    }

}
