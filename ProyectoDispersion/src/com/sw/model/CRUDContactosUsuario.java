package com.sw.model;

import com.sw.model.arbolb.ArbolB;
import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOContactosUsuario;
import com.sw.model.exceptions.ArbolVacioException;
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

    public List<Usuario> getContactosUsuario(String correo) throws ArbolVacioException
    {
        String rutaContactos = crudRuta.getRuta(correo);
        DAO<ArbolB<Usuario>> daoContactosUsuario = new DAOContactosUsuario(rutaContactos);
        ArbolB<Usuario> contactos = daoContactosUsuario.getSavedObject();
        return contactos.enlistarElementos();
    }

}
