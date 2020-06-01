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

    @Override
    public V put(K key, V value)
    {
        return super.put(key, value);
    }

    @Override
    public V get(Object key)
    {
        return super.get(key);
    }

}
