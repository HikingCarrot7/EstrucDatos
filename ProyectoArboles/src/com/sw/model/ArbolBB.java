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
    public void insertar(int idx, E egresado)
    {
        insertar(raiz, idx, egresado);
    }

    @SuppressWarnings("unchecked")
    private void insertar(NodoABB<L> nodo, int idx, E egresado)
    {
        if (isEmpty())
        {
            raiz = new NodoABB<>((L) new LinkedList<Integer>());
            raiz.getDato().addFirst(idx);

        } else if (comparador.comparar(nodo.getDato(), egresado) > 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoABB<>((L) new LinkedList<Integer>()));
                nodo.getIzq().getDato().addFirst(idx);

            } else
                insertar(nodo.getIzq(), idx, egresado);

        else if (comparador.comparar(nodo.getDato(), egresado) < 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoABB<>((L) new LinkedList<Integer>()));
                nodo.getDer().getDato().addFirst(idx);

            } else
                insertar(nodo.getDer(), idx, egresado);

        else
            nodo.getDato().addFirst(idx);
    }

    @Override
    public L buscar(E egresado)
    {
        return buscar(raiz, egresado);
    }

    private L buscar(NodoABB<L> nodo, E egresado) throws ItemNotFoundException
    {
        if (comparador.comparar(nodo.getDato(), egresado) < 0)
            if (nodo.getIzq() == null)
                throw new ItemNotFoundException();
            else
                buscar(nodo.getIzq(), egresado);

        else if (comparador.comparar(nodo.getDato(), egresado) > 0)
            if (nodo.getDer() == null)
                throw new ItemNotFoundException();
            else
                buscar(nodo.getDer(), egresado);

        else
            return nodo.getDato();

        return null;
    }

    public void eliminar(E egresado) throws ItemNotFoundException
    {
        eliminar(raiz, egresado);
    }

    private NodoABB<L> eliminar(NodoABB<L> nodo, E egresado) throws ItemNotFoundException
    {
        if (nodo == null)
            throw new ItemNotFoundException();

        else if (comparador.comparar(nodo.getDato(), egresado) < 0)
            nodo.setIzq(eliminar(nodo.getIzq(), egresado));

        else if (comparador.comparar(nodo.getDato(), egresado) > 0)
            nodo.setDer(eliminar(nodo.getDer(), egresado));

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
