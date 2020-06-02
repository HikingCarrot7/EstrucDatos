package com.sw.model.hashtable;

import java.util.HashMap;

/**
 *
 * @author HikingCarrot7
 * @param <K>
 * @param <V>
 */
public class Hashtable<K, V> extends HashMap<K, V>
{

    private static final long serialVersionUID = 1L;

    public V insertar(K key, V value)
    {
        return super.put(key, value);
    }

    public V obtenerValue(Object key)
    {
        return super.get(key);
    }

    @Override
    public int size()
    {
        return super.size();
    }

}
