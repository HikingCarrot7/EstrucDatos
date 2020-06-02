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

    public void registrarUsuario(Usuario nuevoUsuario)
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();
        usuarios.add(nuevoUsuario);
        dao.saveObject(usuarios);
    }

    public Usuario getUsuario(String correo) throws UsuarioNoExistenteException
    {
        ArrayList<Usuario> usuarios = getTodosLosUsuarios();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public Usuario getUsuario(String correo, String password) throws UsuarioNoExistenteException
    {
        ArrayList<Usuario> usuarios = getTodosLosUsuarios();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public ArrayList<Usuario> getTodosLosUsuarios()
    {
        return dao.getSavedObject();
    }

    public void eliminarUsuario(Usuario usuario)
    {
        ArrayList<Usuario> usuarios = dao.getSavedObject();
        usuarios.remove(usuario);
        dao.saveObject(usuarios);
    }

}
