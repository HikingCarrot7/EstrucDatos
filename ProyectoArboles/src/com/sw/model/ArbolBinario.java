package com.sw.model;

import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <I>
 */
public abstract class ArbolBinario<L, I> implements Arbol<L, I>
{

    protected NodoBinario<L> raiz;

    public LinkedList<L> preorder()
    {
        LinkedList<L> elementos = new LinkedList<>();

        if (raiz != null)
            preorder(raiz, elementos);

        return elementos;
    }

    private void preorder(NodoBinario<L> nodo, LinkedList<L> lista)
    {
        lista.addFirst(nodo.getDato());

        if (nodo.getIzq() != null)
            preorder(nodo.getIzq(), lista);

        if (nodo.getDer() != null)
            preorder(nodo.getDer(), lista);
    }

    public LinkedList<L> inorder()
    {
        LinkedList<L> elementos = new LinkedList<>();

        if (raiz != null)
            inorder(raiz, elementos);

        return elementos;
    }

    private void inorder(NodoBinario<L> nodo, LinkedList<L> lista)
    {
        if (nodo.getIzq() != null)
            inorder(nodo.getIzq(), lista);

        lista.addFirst(nodo.getDato());

        if (nodo.getDer() != null)
            inorder(nodo.getDer(), lista);
    }

    public LinkedList<L> postorder()
    {
        LinkedList<L> elementos = new LinkedList<>();

        if (raiz != null)
            postorder(raiz, elementos);

        return elementos;
    }

    private void postorder(NodoBinario<L> nodo, LinkedList<L> lista)
    {
        if (nodo.getIzq() != null)
            postorder(nodo.getIzq(), lista);

        if (nodo.getDer() != null)
            postorder(nodo.getDer(), lista);

        lista.addFirst(nodo.getDato());
    }

    public NodoBinario<L> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NodoBinario<L> raiz)
    {
        this.raiz = raiz;
    }

    public boolean isEmpty()
    {
        return raiz == null;
    }

}
