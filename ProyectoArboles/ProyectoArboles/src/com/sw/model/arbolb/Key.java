package com.sw.model.arbolb;

import com.sw.model.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class Key<E extends Comparable<E>> implements Comparable<Key<E>>
{

    private E elemento;
    private LinkedList<Integer> indices;

    public Key(E elemento)
    {
        this.elemento = elemento;
        indices = new LinkedList<>();
    }

    public void setElemento(E elemento)
    {
        this.elemento = elemento;
    }

    public E getElemento()
    {
        return elemento;
    }

    public void setIndicesEgresados(LinkedList<Integer> indicesEgresados)
    {
        this.indices = indicesEgresados;
    }

    public LinkedList<Integer> getIndiceEgresados()
    {
        return indices;
    }

    @Override
    public String toString()
    {
        return "elemento = " + elemento + " -índice Egresado=" + indices;
    }

    @Override
    public int compareTo(Key<E> o)
    {
        return elemento.compareTo(o.getElemento());
    }

}
