package com.sw.model.arbolb;

import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class Key<E>
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

}
