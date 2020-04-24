package com.sw.model;

import com.sw.model.exceptions.ItemNotFoundException;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <I>
 */
public class ArbolBB<L extends LinkedList<Integer>, I> extends ArbolBinario<L, I>
{

    private final Comparador<L, I> comparador;

    public ArbolBB(Comparador<L, I> comparador)
    {
        this.comparador = comparador;
    }

    @Override
    public void insertar(int idx, I item)
    {
        insertar(raiz, idx, item);
    }

    @SuppressWarnings("unchecked")
    private void insertar(NodoBinario<L> nodo, int idx, I item)
    {
        if (isEmpty())
        {
            raiz = new NodoBinario<>((L) new LinkedList<Integer>());
            raiz.getDato().addLast(idx);

        } else if (comparador.comparar(nodo.getDato(), item) > 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoBinario<>((L) new LinkedList<Integer>()));
                nodo.getIzq().getDato().addLast(idx);

            } else
                insertar(nodo.getIzq(), idx, item);

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoBinario<>((L) new LinkedList<Integer>()));
                nodo.getDer().getDato().addLast(idx);

            } else
                insertar(nodo.getDer(), idx, item);

        else
            nodo.getDato().addLast(idx);
    }

    @Override
    public L buscar(I item)
    {
        return buscar(raiz, item);
    }

    private L buscar(NodoBinario<L> nodo, I item) throws ItemNotFoundException
    {
        if (comparador.comparar(nodo.getDato(), item) > 0)
            if (nodo.getIzq() == null)
                throw new ItemNotFoundException();
            else
                return buscar(nodo.getIzq(), item);

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            if (nodo.getDer() == null)
                throw new ItemNotFoundException();
            else
                return buscar(nodo.getDer(), item);

        else
            return nodo.getDato();
    }

    public boolean esHoja(NodoBinario<?> nodo)
    {
        return nodo.getIzq() == null && nodo.getDer() == null;
    }

    public boolean esInterno(NodoBinario<?> nodo)
    {
        return !esHoja(nodo);
    }

    public boolean esRaiz(NodoBinario<?> nodo)
    {
        return nodo == raiz;
    }

    public boolean isEmpty()
    {
        return raiz == null;
    }

}
