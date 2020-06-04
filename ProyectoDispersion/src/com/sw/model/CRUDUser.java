package com.sw.model;

import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOUsuarios;
import com.sw.model.exceptions.UsuarioNoExistenteException;
import java.util.List;

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

    private final DAO<List<Usuario>> daoContactosUsuario;

    private CRUDUser()
    {
        daoContactosUsuario = new DAOUsuarios(DAO.RUTA_USUARIOS_REGISTRADOS);
    }

    public void anadirUsuario(Usuario nuevoUsuario)
    {
        List<Usuario> usuarios = getTodosLosUsuarios();
        usuarios.add(nuevoUsuario);
        daoContactosUsuario.saveObject(usuarios);
    }

    public Usuario getUsuario(String correo) throws UsuarioNoExistenteException
    {
        List<Usuario> usuarios = getTodosLosUsuarios();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public Usuario getUsuario(String correo, String password) throws UsuarioNoExistenteException
    {
        List<Usuario> usuarios = getTodosLosUsuarios();

        for (Usuario usuario : usuarios)
            if (usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password))
                return usuario;

        throw new UsuarioNoExistenteException();
    }

    public List<Usuario> getTodosLosUsuarios()
    {
        return daoContactosUsuario.getSavedObject();
    }

    public void actualizarUsuario(Usuario usuarioViejosDatos, Usuario usuarioNuevosDatos)
    {
        List<Usuario> usuarios = getTodosLosUsuarios();

        for (Usuario user : usuarios)
            if (user.equals(usuarioViejosDatos))
            {
                user.setNombre(usuarioNuevosDatos.getNombre());
                user.setEdad(usuarioNuevosDatos.getEdad());
                user.setGenero(usuarioNuevosDatos.getGenero());
                user.setCorreo(usuarioNuevosDatos.getCorreo());
                user.setPassword(usuarioNuevosDatos.getPassword());
                break;
            }

        daoContactosUsuario.saveObject(usuarios);
    }

    public void actualizarUsuarios(List<Usuario> usuarios)
    {
        daoContactosUsuario.saveObject(usuarios);
    }

    public void eliminarUsuario(Usuario usuario)
    {
        List<Usuario> usuarios = getTodosLosUsuarios();
        usuarios.remove(usuario);
        daoContactosUsuario.saveObject(usuarios);
    }

}
