package com.sw.controller;

import com.sw.model.CRUDRuta;
import com.sw.model.CRUDUser;
import com.sw.model.Usuario;
import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOHashtable;
import com.sw.model.dao.DAOUsuarios;
import com.sw.model.exceptions.InicializacionIncorrectaException;
import com.sw.model.hashtable.Hashtable;
import java.util.List;

/**
 *
 * @author HikingCarrot7
 */
public class LauncherManager
{

    public static void revisarArchivosSistema() throws InicializacionIncorrectaException
    {
        try
        {
            if (!DAO.existeArchivo(DAO.RUTA_HASHTABLE_DATA) && DAO.existeArchivo(DAO.RUTA_USUARIOS_REGISTRADOS)
                    || DAO.existeArchivo(DAO.RUTA_HASHTABLE_DATA) && !DAO.existeArchivo(DAO.RUTA_USUARIOS_REGISTRADOS))
                throw new InicializacionIncorrectaException();

            DAOHashtable daoHastable = new DAOHashtable(DAO.RUTA_HASHTABLE_DATA);
            DAOUsuarios daoUsuarios = new DAOUsuarios(DAO.RUTA_USUARIOS_REGISTRADOS);

            Hashtable<String, String> hashtable = daoHastable.getSavedObject();
            List<Usuario> usuariosRegistrados = daoUsuarios.getSavedObject();

            if (usuariosRegistrados.isEmpty() && !hashtable.isEmpty())
                throw new InicializacionIncorrectaException();

            usuariosRegistrados.forEach(u -> hashtable.obtenerValue(u.getCorreo()));

        } catch (InicializacionIncorrectaException | NullPointerException e)
        {
            DAO.eliminarArchivo(DAO.RUTA_HASHTABLE_DATA);
            DAO.eliminarArchivo(DAO.RUTA_USUARIOS_REGISTRADOS);
            CRUDUser.getInstance();
            CRUDRuta.getInstance();
            throw new InicializacionIncorrectaException();
        }

    }

}
