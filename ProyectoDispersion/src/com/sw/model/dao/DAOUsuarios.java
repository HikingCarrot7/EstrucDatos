package com.sw.model.dao;

import com.sw.model.Usuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
@SuppressWarnings("unchecked")
public class DAOUsuarios extends DAO<List<Usuario>>
{

    public DAOUsuarios(String ruta)
    {
        super(ruta);
    }

    @Override
    public List<Usuario> getSavedObject()
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
        {
            return (List<Usuario>) in.readObject();

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

}
