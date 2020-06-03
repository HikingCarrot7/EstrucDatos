package com.sw.model.dao;

import com.sw.model.arbolb.BTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author HikingCarrot7
 */
@SuppressWarnings("unchecked")
public class DAOContactosUsuario extends DAO<BTree>
{

    public DAOContactosUsuario(String ruta)
    {
        super(ruta);
    }

    @Override
    public BTree getSavedObject()
    {
        try
        {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                return (BTree) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new BTree(3);
    }

}
