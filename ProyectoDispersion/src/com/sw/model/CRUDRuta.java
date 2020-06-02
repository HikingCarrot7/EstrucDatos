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

    public void guardarRuta(String correo, String ruta)
    {
        Hashtable<String, String> hashtable = dao.getSavedObject();
        hashtable.insertar(correo, ruta);
        dao.saveObject(hashtable);
    }

}
