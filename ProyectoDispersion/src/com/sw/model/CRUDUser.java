package com.sw.model;

import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOUsuarios;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class CRUDUser
{

    private static CRUDUser instance;

    public static synchronized CRUDUser getInstance()
    {
        if (instance == null)
            instance = new CRUDUser();

        return instance;
    }

    private final DAO<ArrayList<Usuario>> dao;

    private CRUDUser()
    {
        dao = new DAOUsuarios(DAO.RUTA_USUARIOS_REGISTRADOS);
    }

    public boolean existeCorreoRegistrado(String correo)
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();

        return usuarios.stream().anyMatch(usuario -> (usuario.getCorreo().equals(correo)));
    }

    public Usuario getUsuario(String correo, String password) throws UsuarioNoExistenteException
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public ArrayList<Usuario> getTodosLosUsuarios()
    {
        return dao.getSavedObject();
    }

    public void registrarNuevoUsuario(Usuario nuevoUsuario)
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();
        usuarios.add(nuevoUsuario);
        dao.saveObject(usuarios);
    }

    public void eliminarUsuario(Usuario usuario)
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();
        usuarios.remove(usuario);
        dao.saveObject(usuarios);
    }

}
