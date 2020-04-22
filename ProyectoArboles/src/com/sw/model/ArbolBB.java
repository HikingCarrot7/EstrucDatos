package com.sw.model;

import com.sw.controller.Operacion;
import com.sw.utils.LinkedList;

/**
 *
 * @author Nicolás
 */
public class ArbolBB extends ArbolBinario<LinkedList<Integer>>
{

    public void insertar(int idx, Egresado egresadoAnadir, Operacion operacion)
    {
        insertar(raiz, idx, egresadoAnadir, operacion);
    }

    private void insertar(NodoABB<LinkedList<Integer>> nodo, Integer idx, Egresado egresadoAnadir, Operacion operacion)
    {
        if (isEmpty())
        {
            raiz = new NodoABB<>(new LinkedList<>());
            raiz.getDato().addFirst(idx);

        } else if (operacion.operar(nodo.getDato(), egresadoAnadir) > 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoABB<>(new LinkedList<>()));
                nodo.getIzq().getDato().addFirst(idx);

            } else
                insertar(nodo.getIzq(), idx, egresadoAnadir, operacion);

        else if (operacion.operar(nodo.getDato(), egresadoAnadir) < 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoABB<>(new LinkedList<>()));
                nodo.getDer().getDato().addFirst(idx);

            } else
                insertar(nodo.getDer(), idx, egresadoAnadir, operacion);

        else
            nodo.getDato().addFirst(idx);
    }

//    public T buscar(T elemento)
//    {
//        return buscar(raiz, elemento);
//    }
//
//    private T buscar(NodoABB<T> nodo, T elemento) throws ItemNotFoundException
//    {
//        if (elemento.compareTo(nodo.getDato()) < 0)
//            if (nodo.getIzq() == null)
//                throw new ItemNotFoundException();
//            else
//                buscar(nodo.getIzq(), elemento);
//
//        else if (elemento.compareTo(nodo.getDato()) > 0)
//            if (nodo.getDer() == null)
//                throw new ItemNotFoundException();
//            else
//                buscar(nodo.getDer(), elemento);
//
//        else
//            return elemento;
//
//        return null;
//    }
//
//    public void eliminar(T elemento) throws ItemNotFoundException
//    {
//        eliminar(raiz, elemento);
//    }
//
//    private NodoABB<T> eliminar(NodoABB<T> nodo, T elemento) throws ItemNotFoundException
//    {
//        if (nodo == null)
//            throw new ItemNotFoundException();
//
//        else if (elemento.compareTo(nodo.getDato()) < 0)
//            nodo.setIzq(eliminar(nodo.getIzq(), elemento));
//
//        else if (elemento.compareTo(nodo.getDato()) > 0)
//            nodo.setDer(eliminar(nodo.getDer(), elemento));
//
//        else if (nodo.getDer() != null && nodo.getIzq() != null)
//        {
//            NodoABB<T> minimo = buscarMin(nodo.getDer());
//            nodo.setDato(minimo.getDato());
//            nodo.setDer(borrarMin(nodo.getDer()));
//
//        } else
//            nodo = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();
//
//        return nodo;
//    }
//
//    private NodoABB<T> buscarMin(NodoABB<T> nodo)
//    {
//        while (nodo.getIzq() != null)
//            nodo = nodo.getIzq();
//
//        return nodo;
//    }
//
//    private NodoABB<T> borrarMin(NodoABB<T> n)
//    {
//        if (n.getIzq() != null)
//        {
//            n.setIzq(borrarMin(n.getIzq()));
//            return n;
//
//        } else
//            return n.getDer();
//    }
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
