package com.sw.model;

import com.sw.model.arbolb.BTree;
import com.sw.model.dao.DAO;
import com.sw.model.dao.DAOContactosUsuario;
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

    private final DAO<Hashtable<String, String>> daoHashtable;

    private CRUDRuta()
    {
        daoHashtable = new DAOHashtable(DAO.RUTA_HASHTABLE_DATA);
    }

    public void anadirRuta(String correoUsuario, String ruta)
    {
        Hashtable<String, String> hashtable = daoHashtable.getSavedObject();
        hashtable.put(correoUsuario, ruta);
        daoHashtable.saveObject(hashtable);
    }

    public String getRuta(String correoUsuario)
    {
        Hashtable<String, String> hashtable = daoHashtable.getSavedObject();
        return hashtable.get(correoUsuario);
    }

    public void actualizarRuta(Usuario usuarioViejo, String correoUsuarioNuevo, String nuevaRuta)
    {
        Hashtable<String, String> hashtable = daoHashtable.getSavedObject();

        String rutaVieja = hashtable.get(usuarioViejo.getCorreo());
        BTree contactos = CRUDContactosUsuario.getInstance().getArbolContactosUsuario(usuarioViejo.getCorreo());
        DAO.eliminarArchivo(rutaVieja);

        DAOContactosUsuario daoContactosUser = new DAOContactosUsuario(nuevaRuta);
        daoContactosUser.saveObject(contactos);

        hashtable.remove(usuarioViejo.getCorreo());
        hashtable.put(correoUsuarioNuevo, nuevaRuta);
        daoHashtable.saveObject(hashtable);
    }

    public void eliminarRuta(String correoUsuario)
    {
        Hashtable<String, String> hashtable = daoHashtable.getSavedObject();
        String rutaArchivo = hashtable.get(correoUsuario);

        DAO.eliminarArchivo(rutaArchivo);

        hashtable.remove(correoUsuario);
        daoHashtable.saveObject(hashtable);
    }

}
