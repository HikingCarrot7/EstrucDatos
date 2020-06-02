package com.sw.model.dao;

import com.sw.model.Usuario;
import com.sw.model.arbolb.ArbolB;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author HikingCarrot7
 */
@SuppressWarnings("unchecked")
public class DAOContactosUsuario extends DAO<ArbolB<Usuario>>
{

    public DAOContactosUsuario(String ruta)
    {
        super(ruta);
    }

    @Override
    public ArbolB<Usuario> getSavedObject()
    {
        try
        {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                return (ArbolB<Usuario>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArbolB<>(3);
    }

}