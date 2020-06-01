package com.sw.model.dao;

import com.sw.model.arbolb.ArbolB;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author HikingCarrot7
 */
public class DAOUsuario extends DAO<ArbolB<?>>
{

    public DAOUsuario(String ruta)
    {
        super(ruta);
    }

    @Override public ArbolB<?> getSavedObject()
    {
        try
        {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                return (ArbolB<?>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArbolB<>(3);
    }

}
