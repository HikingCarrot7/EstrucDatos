package com.sw.model.arbolbb;

import com.sw.util.Node;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class NodoBinario<E> extends Node<E>
{

    protected NodoBinario<E> izq;
    protected NodoBinario<E> der;

    public NodoBinario(E dato)
    {
        this(dato, null, null);
    }

    public NodoBinario(E dato, NodoBinario<E> izq, NodoBinario<E> der)
    {
        super(dato);
        this.izq = izq;
        this.der = der;
    }

    public NodoBinario<E> getIzq()
    {
        return izq;
    }

    public void setIzq(NodoBinario<E> izq)
    {
        this.izq = izq;
    }

    public NodoBinario<E> getDer()
    {
        return der;
    }

    public void setDer(NodoBinario<E> der)
    {
        this.der = der;
    }

}
