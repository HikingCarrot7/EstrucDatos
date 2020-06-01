package com.sw.model.arbolb;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot7
 * @param <T>
 */
public class Key<T extends Comparable<? super T>> implements Comparable<Key<T>>, Serializable
{

    private static final long serialVersionUID = 1L;

    private T elemento;

    public Key(T elemento)
    {
        this.elemento = elemento;
    }

    public T getElemento()
    {
        return elemento;
    }

    public void setElemento(T elemento)
    {
        this.elemento = elemento;
    }

    @Override
    public int compareTo(Key<T> o)
    {
        return elemento.compareTo(o.getElemento());
    }

    @Override
    public String toString()
    {
        return "Key{" + "elemento=" + elemento + '}';
    }

}
