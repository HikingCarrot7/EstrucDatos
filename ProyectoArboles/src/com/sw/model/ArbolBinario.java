package com.sw.model;

import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <E>
 */
public abstract class ArbolBinario<L, E>
{

    protected NodoABB<L> raiz;

    public void preorder(LinkedList<L> lista)
    {
        if (raiz != null)
            preorder(raiz, lista);
    }

    private void preorder(NodoABB<L> nodo, LinkedList<L> lista)
    {
        lista.addFirst(nodo.getDato());

        if (nodo.getIzq() != null)
            preorder(nodo.getIzq(), lista);

        if (nodo.getDer() != null)
            preorder(nodo.getDer(), lista);
    }

    public void inorder(LinkedList<L> lista)
    {
        if (raiz != null)
            inorder(raiz, lista);
    }

    private void inorder(NodoABB<L> nodo, LinkedList<L> lista)
    {
        if (nodo.getIzq() != null)
            inorder(nodo.getIzq(), lista);

        lista.addFirst(nodo.getDato());

        if (nodo.getDer() != null)
            inorder(nodo.getDer(), lista);
    }

    public void postorder(LinkedList<L> lista)
    {
        if (raiz != null)
            postorder(raiz, lista);
    }

    private void postorder(NodoABB<L> nodo, LinkedList<L> lista)
    {
        if (nodo.getIzq() != null)
            postorder(nodo.getIzq(), lista);

        if (nodo.getDer() != null)
            postorder(nodo.getDer(), lista);

        lista.addFirst(nodo.getDato());
    }

    public abstract void insertar(int idx, E element);

    public abstract L buscar(E element);

    public NodoABB<L> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NodoABB<L> raiz)
    {
        this.raiz = raiz;
    }

}
