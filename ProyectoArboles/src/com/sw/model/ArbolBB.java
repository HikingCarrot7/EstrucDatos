package com.sw.model;

import com.sw.controller.Comparador;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <E>
 */
public class ArbolBB<L extends LinkedList<Integer>, E> extends ArbolBinario<L, E>
{

    private final Comparador<L, E> comparador;

    public ArbolBB(Comparador<L, E> comparador)
    {
        this.comparador = comparador;
    }

    @Override
    public void insertar(int idx, E item)
    {
        insertar(raiz, idx, item);
    }

    @SuppressWarnings("unchecked")
    private void insertar(NodoABB<L> nodo, int idx, E item)
    {
        if (isEmpty())
        {
            raiz = new NodoABB<>((L) new LinkedList<Integer>());
            raiz.getDato().addLast(idx);

        } else if (comparador.comparar(nodo.getDato(), item) > 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoABB<>((L) new LinkedList<Integer>()));
                nodo.getIzq().getDato().addLast(idx);

            } else
                insertar(nodo.getIzq(), idx, item);

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoABB<>((L) new LinkedList<Integer>()));
                nodo.getDer().getDato().addLast(idx);

            } else
                insertar(nodo.getDer(), idx, item);

        else
            nodo.getDato().addLast(idx);
    }

    @Override
    public L buscar(E item)
    {
        return buscar(raiz, item);
    }

    private L buscar(NodoABB<L> nodo, E item) throws ItemNotFoundException
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

    public void eliminar(E item) throws ItemNotFoundException
    {
        eliminar(raiz, item);
    }

    private NodoABB<L> eliminar(NodoABB<L> nodo, E item) throws ItemNotFoundException
    {
        if (nodo == null)
            throw new ItemNotFoundException();

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            nodo.setIzq(eliminar(nodo.getIzq(), item));

        else if (comparador.comparar(nodo.getDato(), item) > 0)
            nodo.setDer(eliminar(nodo.getDer(), item));

        else if (nodo.getDer() != null && nodo.getIzq() != null)
        {
            NodoABB<L> minimo = buscarMin(nodo.getDer());
            nodo.setDato(minimo.getDato());
            nodo.setDer(borrarMin(nodo.getDer()));

        } else
            nodo = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();

        return nodo;
    }

    private NodoABB<L> buscarMin(NodoABB<L> nodo)
    {
        while (nodo.getIzq() != null)
            nodo = nodo.getIzq();

        return nodo;
    }

    private NodoABB<L> borrarMin(NodoABB<L> nodo)
    {
        if (nodo.getIzq() != null)
        {
            nodo.setIzq(borrarMin(nodo.getIzq()));
            return nodo;

        } else
            return nodo.getDer();
    }

    public boolean esHoja(NodoABB<?> nodo)
    {
        return nodo.getIzq() == null && nodo.getDer() == null;
    }

    public boolean esInterno(NodoABB<?> nodo)
    {
        return !esHoja(nodo);
    }

    public boolean esRaiz(NodoABB<?> nodo)
    {
        return nodo == raiz;
    }

    public boolean isEmpty()
    {
        return raiz == null;
    }

}
