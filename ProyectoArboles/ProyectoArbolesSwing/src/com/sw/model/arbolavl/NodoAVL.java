package com.sw.model.arbolavl;

import com.sw.model.arbolbb.NodoBinario;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class NodoAVL<E> extends NodoBinario<E>
{

    private int FE;
    private NodoAVL<E> padre;

    public NodoAVL(E dato)
    {
        super(dato);
    }

    public NodoAVL(E dato, NodoBinario<E> izq, NodoBinario<E> der)
    {
        super(dato, izq, der);
    }

    public NodoAVL(E dato, NodoBinario<E> izq, NodoBinario<E> der, NodoAVL<E> padre)
    {
        this(dato, izq, der);
        this.padre = padre;
    }

    public static int altura(NodoAVL<?> nodo)
    {
        if (nodo == null)
            return -1;

        return 1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer()));
    }

    @Override
    public NodoAVL<E> getIzq()
    {
        return (NodoAVL<E>) izq;
    }

    @Override
    public NodoAVL<E> getDer()
    {
        return (NodoAVL<E>) der;
    }

    public NodoAVL<E> getPadre()
    {
        return padre;
    }

    public void setPadre(NodoAVL<E> padre)
    {
        this.padre = padre;
    }

    public int getFE()
    {
        return FE;
    }

    public void setFE(int FE)
    {
        this.FE = FE;
    }

}
