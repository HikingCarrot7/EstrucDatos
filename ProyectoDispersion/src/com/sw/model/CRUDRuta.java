package com.sw.model;

import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOHashtable;
import com.sw.model.hashtable.Hashtable;

/**
 *
 * @author HikingCarrot7
 */
public class CRUDRuta
{

    private static CRUDRuta instance;

    public static synchronized CRUDRuta getInstance()
    {
        if (instance == null)
            instance = new CRUDRuta();

        return instance;
    }

    private final DAO<Hashtable<String, String>> dao;

    private CRUDRuta()
    {
        dao = new DAOHashtable(DAO.RUTA_HASHTABLE_DATA);
    }

    public void anadirRuta(String correoUsuario, String ruta)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        hashtable.insertar(correoUsuario, ruta);
        dao.saveObject(hashtable);
    }

    public String getRuta(String correoUsuario)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        return hashtable.get(correoUsuario);
    }

    public void actualizarRuta(String correoUsuario, String nuevaRuta)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        hashtable.eliminar(correoUsuario);
        hashtable.insertar(correoUsuario, nuevaRuta);
        dao.saveObject(hashtable);
    }

    public void actualizarRuta(String correoUsuarioViejo, String correoUsuarioNuevo, String nuevaRuta)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        hashtable.eliminar(correoUsuarioViejo);
        hashtable.insertar(correoUsuarioNuevo, nuevaRuta);
        dao.saveObject(hashtable);
    }

    public void eliminarRuta(String correoUsuario)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        String rutaArchivo = hashtable.obtenerValue(correoUsuario);

        DAO.eliminarArchivo(rutaArchivo);

        hashtable.eliminar(correoUsuario);
        dao.saveObject(hashtable);
    }

}
