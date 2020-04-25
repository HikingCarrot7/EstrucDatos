package com.sw.model.arbolb;

import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public class Key<T extends Comparable<T>> implements Comparable<Key<T>>
{

    private T elemento;
    private LinkedList<Integer> indices;

    public Key(T elemento)
    {
        this.elemento = elemento;
        indices = new LinkedList<>();
    }

    public void setElemento(T elemento)
    {
        this.elemento = elemento;
    }

    public T getElemento()
    {
        return elemento;
    }

    public void setIndecesEgresados(LinkedList<Integer> indecesEgresados)
    {
        this.indices = indecesEgresados;
    }

    public LinkedList<Integer> getIndiceEgresados()
    {
        return indices;
    }

    @Override
    public int compareTo(Key<T> o)
    {
        return elemento.compareTo(o.getElemento());
    }

    @Override
    public String toString()
    {
        return "elemento = " + elemento + " -índice Egresado=" + indices;
    }

}
