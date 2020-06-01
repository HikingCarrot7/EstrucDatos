package com.sw.controller;

import com.sw.model.Usuario;
import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOUsuarios;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class UserManager
{

    private static UserManager instance;

    public static synchronized UserManager getInstance()
    {
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    private UserManager()
    {

    }

    public Usuario getUsuario(String correo, String password) throws UsuarioNoExistenteException
    {
        DAO<ArrayList<Usuario>> dao = new DAOUsuarios(DAO.RUTA_USUARIOS_REGISTRADOS);
        ArrayList<Usuario> usuarios = dao.getSavedObject();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public void registrarNuevoUsuario(Usuario nuevoUsuario)
    {
        DAO<ArrayList<Usuario>> dao = new DAOUsuarios(DAO.RUTA_USUARIOS_REGISTRADOS);
        ArrayList<Usuario> usuarios = dao.getSavedObject();
        usuarios.add(nuevoUsuario);
        dao.saveObject(usuarios);
    }

}
