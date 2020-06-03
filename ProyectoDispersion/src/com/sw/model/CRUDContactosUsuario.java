package com.sw.model;

import com.sw.model.arbolb.ArbolB;
import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOContactosUsuario;
import com.sw.model.exceptions.ArbolVacioException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class CRUDContactosUsuario
{

    private static CRUDContactosUsuario instance;

    public static synchronized CRUDContactosUsuario getInstance()
    {
        if (instance == null)
            instance = new CRUDContactosUsuario();

        return instance;
    }

    private final CRUDRuta crudRuta;

    private CRUDContactosUsuario()
    {
        crudRuta = CRUDRuta.getInstance();
    }

    public void anadirContactoAUsuario(String correoUsuario, Usuario contacto)
    {
        String rutaContactos = crudRuta.getRuta(correoUsuario);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> misContactos = daoContactosUsuario.getSavedObject();
        misContactos.add(contacto);
        daoContactosUsuario.saveObject(misContactos);
    }

    public void anadirContactosAUsuario(String correoUsuario, List<Usuario> contactos)
    {
        String rutaContactos = crudRuta.getRuta(correoUsuario);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> misContactos = daoContactosUsuario.getSavedObject();
        contactos.forEach(misContactos::add);
        daoContactosUsuario.saveObject(misContactos);
    }

    public List<Usuario> getContactosUsuario(String correoUsuario)
    {
        String rutaContactos = crudRuta.getRuta(correoUsuario);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> contactos = daoContactosUsuario.getSavedObject();

        try
        {
            return contactos.enlistarElementos();

        } catch (ArbolVacioException e)
        {
            return new ArrayList<>();
        }
    }

    public void eliminarContactoUsuario(String correoUsuario, Usuario contactoAEliminar)
    {
        String rutaContactos = crudRuta.getRuta(correoUsuario);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> contactos = daoContactosUsuario.getSavedObject();

        contactos.remove(contactoAEliminar);

        daoContactosUsuario.saveObject(contactos);
    }

    public void eliminarContactosUsuario(String correoUsuario, List<Usuario> contactosAEliminar)
    {
        String rutaContactos = crudRuta.getRuta(correoUsuario);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> contactos = daoContactosUsuario.getSavedObject();

        contactosAEliminar.forEach(contactos::remove);

        daoContactosUsuario.saveObject(contactos);
    }

}
